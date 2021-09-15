package com.learnjava.parallelstreams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;

public class ArrayListSpliteratorExample {

    public List<Integer> multiplyEachValue(ArrayList<Integer> inputList, int multiplyValue, boolean isParallel) {

        startTimer();
        Stream<Integer> integerStream = inputList.stream(); // sequential by default

        if (isParallel)
            integerStream.parallel();

        List<Integer> resultList = integerStream
                .map(number -> number * multiplyValue)
                .collect(Collectors.toList());

        timeTaken();
        return resultList;
    }
}
