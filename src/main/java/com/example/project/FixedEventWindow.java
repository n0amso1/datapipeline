package com.example.project;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class FixedEventWindow {
    private final int mSize;
    private final List<Integer> mAggregatedEvents;
    private final Consumer<List<Integer>> mCallback;

    public FixedEventWindow(int size, Consumer<List<Integer>> callback) {
        mSize = size;
        mAggregatedEvents = new ArrayList<>(size);
        mCallback = callback;
    }

    public void onReceive(int num) {
        mAggregatedEvents.add(num);
        if (mAggregatedEvents.size() == mSize) {
            mCallback.accept(new ArrayList<>(mAggregatedEvents));
            mAggregatedEvents.clear();
        }
    }
}
