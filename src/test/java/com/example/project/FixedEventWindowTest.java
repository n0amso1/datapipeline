package com.example.project;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Consumer;

public class FixedEventWindowTest {

    @Test
    void it_should_pass_aggregated_events_when_full() {
        Consumer<List<Integer>> callback = System.out::println;
        BuildingBlock block = new FixedEventWindow(3, callback);
        block.onReceive(4);
        block.onReceive(2);
        block.onReceive(1);
        // todo assert [4,2,1] without looking at output
    }

    @Test
    void it_should_clear_old_aggregations() {
        Consumer<List<Integer>> callback = System.out::println;
        BuildingBlock block = new FixedEventWindow(3, callback);
        block.onReceive(4);
        block.onReceive(2);
        block.onReceive(1);

        // new aggregations
        block.onReceive(6);
        block.onReceive(3);
        block.onReceive(2);
        // todo assert [6,3,2] without looking at output
    }
}
