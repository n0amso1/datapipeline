package com.example.project;

import com.example.project.buildingblocks.FixedEventWindow;
import com.example.project.datapipeline.DataPipeline;
import com.example.project.datapipeline.DataPipelineBuilder;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class FixedEventWindowTest {

    @Test
    void it_should_pass_aggregated_events_when_full() {
        CapturingConsumer<List<Integer>> fixedEventWindowOutput = new CapturingConsumer<>();
        DataPipeline fixedEventWindow = DataPipelineBuilder.startingWith(FixedEventWindow.withSize(3))
                .andFinally(fixedEventWindowOutput);

        fixedEventWindow.onReceive(4);
        fixedEventWindow.onReceive(2);
        fixedEventWindow.onReceive(1);

        assertThat(fixedEventWindowOutput.getCapturedValue(),
                equalTo(Arrays.asList(4, 2, 1)));
    }

    @Test
    void it_should_clear_old_aggregations() {
        DataPipeline fixedEventWindow = DataPipelineBuilder.startingWith(FixedEventWindow.withSize(3))
                .andFinally(System.out::println);

        fixedEventWindow.onReceive(4);
        fixedEventWindow.onReceive(2);
        fixedEventWindow.onReceive(1);

        // new aggregations
        fixedEventWindow.onReceive(6);
        fixedEventWindow.onReceive(3);
        fixedEventWindow.onReceive(2);
        // todo assert [6,3,2] without looking at output
    }
}
