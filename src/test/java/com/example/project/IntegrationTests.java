package com.example.project;

import com.example.project.buildingblocks.Filter;
import com.example.project.buildingblocks.FixedEventWindow;
import com.example.project.datapipeline.DataPipeline;
import com.example.project.datapipeline.DataPipelineBuilder;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class IntegrationTests {

    @Test
    void test_filter_with_fixedEventWindow() {
        CapturingConsumer<List<Integer>> pipelineResult = new CapturingConsumer<>();
        DataPipeline pipeline = DataPipelineBuilder.startingWith(Filter.by(i -> i > 0))
                .andThen(FixedEventWindow.withSize(3))
                .andFinally(pipelineResult);
        
        pipeline.onReceive(3);
        pipeline.onReceive(-7);
        pipeline.onReceive(2);
        pipeline.onReceive(6);

        assertThat(pipelineResult.getCapturedValue(), equalTo(Arrays.asList(3, 2, 6)));
    }
}
