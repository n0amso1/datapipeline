package com.example.project;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Consumer;

public class FixedEventWindowTest {

    @Test
    void it_should_pass_aggregated_events_when_full() {
        Consumer<List<Integer>> callback = System.out::println;
        FixedEventWindow fixedEventWindow = new FixedEventWindow(3, callback);
        fixedEventWindow.onReceive(4);
        fixedEventWindow.onReceive(2);
        fixedEventWindow.onReceive(1);
        // todo assert [4,2,1] without looking at output
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
