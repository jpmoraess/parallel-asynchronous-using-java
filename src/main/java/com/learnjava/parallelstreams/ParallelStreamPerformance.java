package com.learnjava.parallelstreams;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;

public class ParallelStreamPerformance {

    public int sumUsingIntStream(int count, boolean isParallel) {
        startTimer();
        IntStream intStream = IntStream.rangeClosed(0, count);

        if (isParallel)
            intStream.parallel();

        int sum = intStream
                .sum();

        timeTaken();

        return sum;
    }

    public int sumUsingList(List<Integer> inputList, boolean isParallel) {
        startTimer();
        Stream<Integer> inputStream = inputList.stream();

        if (isParallel)
            inputStream.parallel();

        int sum = inputStream
                .mapToInt(Integer::intValue) // unboxing
                .sum();

        timeTaken();

        return sum;
    }
}
