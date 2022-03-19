package com.example.project;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class FixedEventWindowTest {

    @Test
    void it_should_pass_aggregated_events_when_full() {
        CapturingConsumer<List<Integer>> fixedEventWindowOutput = new CapturingConsumer<>();
        FixedEventWindow fixedEventWindow = new FixedEventWindow(3, fixedEventWindowOutput);

        fixedEventWindow.onReceive(4);
        fixedEventWindow.onReceive(2);
        fixedEventWindow.onReceive(1);

        assertThat(fixedEventWindowOutput.getCapturedValue(),
                equalTo(Arrays.asList(4, 2, 1)));
    }

    @Test
    void it_should_clear_old_aggregations() {
        Consumer<List<Integer>> callback = System.out::println;
        FixedEventWindow fixedEventWindow = new FixedEventWindow(3, callback);
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
