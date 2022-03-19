package com.example.project.buildingblocks;

import com.example.project.datapipeline.DataPipelineBuilder;
import com.example.project.datapipeline.StartingBuildingBlock;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class Filter implements StartingBuildingBlock {

    private final Predicate<Integer> mPredicate;
    private final Consumer<Integer> mCallback;

    public static DataPipelineBuilder.Factory<StartingBuildingBlock> by(Predicate<Integer> predicate) {
        return (callback) -> new Filter(predicate, (Consumer<Integer>) callback);
    }

    Filter(Predicate<Integer> predicate, Consumer<Integer> callback) {
        mPredicate = predicate;
        mCallback = callback;
    }

    @Override
    public void onReceive(int num) {
        if (mPredicate.test(num)) {
            mCallback.accept(num);
        }
    }
}
