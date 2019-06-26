package com.leiline.sql.start.batch;


import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.table.api.Types;
import org.apache.flink.table.api.java.BatchTableEnvironment;
import org.apache.flink.table.descriptors.Csv;
import org.apache.flink.table.descriptors.FileSystem;


public class JavaBatchWordCount {

    public static void main(String[] args) throws Exception {

//        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
//        BatchTableEnvironment tEnv = BatchTableEnvironment.create(env);
//
//        String path = JavaBatchWordCount.class.getClassLoader()
//               .getResource("wrods.txt").getPath();
//        tEnv.connect(new FileSystem().path(path))
//                .withFormat(new Csv().field("word", Types.STRING()));
    }
}
