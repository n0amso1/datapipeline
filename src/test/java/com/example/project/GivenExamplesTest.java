package com.example.project;

import com.example.project.buildingblocks.Filter;
import com.example.project.buildingblocks.FixedEventWindow;
import com.example.project.buildingblocks.FoldMedian;
import com.example.project.buildingblocks.FoldSum;
import com.example.project.datapipeline.DataPipeline;
import com.example.project.datapipeline.DataPipelineBuilder;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GivenExamplesTest {

    private static DataPipeline createExamplePipeline(Consumer<Double> resultCallback) {
        return DataPipelineBuilder.startingWith(Filter.by(i -> i > 0))
                .andThen(FixedEventWindow.withSize(2))
                .andThen(FoldSum.create())
                .andThen(FixedEventWindow.withSize(3))
                .andThen(FoldMedian.create())
                .andFinally(resultCallback);
    }

    @Test
    void example_1() {
        CapturingConsumer<Double> pipelineResult = new CapturingConsumer<>();
        DataPipeline pipeline = createExamplePipeline(pipelineResult);

        pipeline.onReceive(1);
        pipeline.onReceive(2);
        pipeline.onReceive(-5);
        pipeline.onReceive(3);
        pipeline.onReceive(4);
        pipeline.onReceive(5);
        pipeline.onReceive(6);

        assertThat(pipelineResult.getCapturedValue(), is(7.0));
    }

    @Test
    void example_2() {
        CapturingConsumer<Double> pipelineResult = new CapturingConsumer<>();
        DataPipeline pipeline = createExamplePipeline(pipelineResult);

        pipeline.onReceive(10);
        pipeline.onReceive(11);
        pipeline.onReceive(12);
        pipeline.onReceive(13);
        pipeline.onReceive(14);
        pipeline.onReceive(15);

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
