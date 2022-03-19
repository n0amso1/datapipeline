package com.example.project;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GivenExampleTest {

    @Test
    void test() {
        CapturingConsumer<Double> pipelineResult = new CapturingConsumer<>();
        FoldMedian median = new FoldMedian(pipelineResult);
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

        assertThat(pipelineResult.getCapturedValue(), is(7.0));
    }
}
