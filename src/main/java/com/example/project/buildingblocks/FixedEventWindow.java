package com.example.project.buildingblocks;

import com.example.project.datapipeline.DataPipelineBuilder;
import com.example.project.datapipeline.StartingBuildingBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class FixedEventWindow implements StartingBuildingBlock {
    private final int mSize;
    private final List<Integer> mAggregatedEvents;
    private final Consumer<List<Integer>> mCallback;

    public static DataPipelineBuilder.Factory<StartingBuildingBlock> withSize(int size) {
        return (callback) -> new FixedEventWindow(size, (Consumer<List<Integer>>) callback);
    }

    FixedEventWindow(int size, Consumer<List<Integer>> callback) {
        mSize = size;
        mAggregatedEvents = new ArrayList<>(size);
        mCallback = callback;
    }

    @Override
    public void onReceive(int num) {
        mAggregatedEvents.add(num);
        if (mAggregatedEvents.size() == mSize) {
            mCallback.accept(new ArrayList<>(mAggregatedEvents));
            mAggregatedEvents.clear();
        }
    }
}
