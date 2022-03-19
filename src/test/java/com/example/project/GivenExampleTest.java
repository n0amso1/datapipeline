package com.example.project;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

public class GivenExampleTest {

    @Test
    void test() {
        Consumer<Integer> stdout = System.out::println;
        FoldMedian median = new FoldMedian(stdout);
        FixedEventWindow windowSized3 = new FixedEventWindow(3, median::onReceive);
        FoldSum foldSum = new FoldSum(windowSized3::onReceive);
        FixedEventWindow windowSized2 = new FixedEventWindow(2, foldSum::onReceive);
        Filter pipelineStart = new Filter(i -> i > 0, windowSized2::onReceive);

        pipelineStart.onReceive(1);
        pipelineStart.onReceive(2);
        pipelineStart.onReceive(-5);
        pipelineStart.onReceive(3);
        pipelineStart.onReceive(4);
        pipelineStart.onReceive(5);
        pipelineStart.onReceive(6);
        // todo assert result is 7 without looking at stdout
    }
}
