package com.example.project;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GivenExamplesTest {

    private static Filter createExamplePipeline(Consumer<Double> resultCallback) {
        FoldMedian median = new FoldMedian(resultCallback);
        FixedEventWindow windowSized3 = new FixedEventWindow(3, median::onReceive);
        FoldSum foldSum = new FoldSum(windowSized3::onReceive);
        FixedEventWindow windowSized2 = new FixedEventWindow(2, foldSum::onReceive);
        return new Filter(i -> i > 0, windowSized2::onReceive);
    }

    @Test
    void example_1() {
        CapturingConsumer<Double> pipelineResult = new CapturingConsumer<>();
        Filter pipelineStart = createExamplePipeline(pipelineResult);

        pipelineStart.onReceive(1);
        pipelineStart.onReceive(2);
        pipelineStart.onReceive(-5);
        pipelineStart.onReceive(3);
        pipelineStart.onReceive(4);
        pipelineStart.onReceive(5);
        pipelineStart.onReceive(6);

        assertThat(pipelineResult.getCapturedValue(), is(7.0));
    }

    @Test
    void example_2() {
        CapturingConsumer<Double> pipelineResult = new CapturingConsumer<>();
        Filter pipelineStart = createExamplePipeline(pipelineResult);

        pipelineStart.onReceive(10);
        pipelineStart.onReceive(11);
        pipelineStart.onReceive(12);
        pipelineStart.onReceive(13);
        pipelineStart.onReceive(14);
        pipelineStart.onReceive(15);

        assertThat(pipelineResult.getCapturedValue(), is(25.0));
    }

    /*
    Explanation for example 2:
    > 10
    > 11
    [10,11]->10+11=21->[21,
    > 12
    > 13
    [12,13]->12+13=25->[21,25
    > 14
    > 15
    [14,15]->14+15=29->[21,25,29]->25
     */
}
