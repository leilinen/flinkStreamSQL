package com.leiline.flink;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

public class WordCount {

    public static void main(String[] args)  throws Exception{

        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        DataSet<String> text = env.fromElements("That last call is necessary to start the actual " +
                "Flink job. All operations, such as creating sources, transformations and sinks only build up a graph" +
                "of internal operations. Only when execute() is called is this graph of operations thrown on a cluster " +
                "or executed on your local machine.");

        DataSet<Tuple2<String, Long>> result = text.flatMap(new LineSplitter())
                .groupBy(0)
                .sum(1);

        //result.writeAsText("/Users/lilin/Documents/code/flinkStreamSQL/sql.test/out");
        result.print();
        env.execute();

    }

    public static class LineSplitter implements FlatMapFunction<String, Tuple2<String, Long>> {
        @Override
        public void flatMap(String line, Collector<Tuple2<String, Long>> collector) throws Exception {
            String[] words = line.split(" ");
            for (String word: words) {
                collector.collect(new Tuple2<>(word, 1L));
            }
        }
    }

}
