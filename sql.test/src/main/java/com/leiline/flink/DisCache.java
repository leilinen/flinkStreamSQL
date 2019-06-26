package com.leiline.flink;

import org.apache.commons.io.FileUtils;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.configuration.Configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DisCache {

    public static void main(String[] args) throws Exception{
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        DataSource<String> data = env.fromElements("That last call is necessary to start the actual " +
                "Flink job. All operations, such as creating sources, transformations and sinks only build up a graph" +
                "of internal operations. Only when execute() is called is this graph of operations thrown on a cluster " +
                "or executed on your local machine.");

        env.registerCachedFile("/Users/lilin/Documents/code/flinkStreamSQL/sql.test/out/a.txt", "a.txt");
        DataSet<String> result = data.map(new RichMapFunction<String, String>() {
            private ArrayList<String> dataList = new ArrayList<String>();

            @Override
            public void open(Configuration parameters) throws Exception {
                super.open(parameters);
                File myfile = getRuntimeContext().getDistributedCache().getFile("a.txt");
                List<String> lines  = FileUtils.readLines(myfile);
                for (String line: lines) {
                    this.dataList.add(line);
                    System.err.println("分布式缓存为： " + line);
                }
            }

            @Override
            public String map(String value) throws Exception {
                System.err.println("使用datalist: " + dataList + "-------------" + value);
                return dataList + ": " + value;
            }
        });

        result.printToErr();
        env.execute();
    }
}
