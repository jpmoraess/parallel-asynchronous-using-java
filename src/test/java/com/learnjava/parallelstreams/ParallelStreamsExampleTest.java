package com.learnjava.parallelstreams;

import com.learnjava.util.DataSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class ParallelStreamsExampleTest {

    ParallelStreamsExample parallelStreamsExample = new ParallelStreamsExample();

    @Test
    void stringTransform() {
        // given
        List<String> inputList = DataSet.namesList();

        // when
        startTimer();
        List<String> resultList = parallelStreamsExample.stringTransform(inputList);
        timeTaken();

        //then
        assertEquals(4, resultList.size());
        resultList.forEach(name -> assertTrue(name.contains("-")));
    }

    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    void stringTransform1(boolean isParallel) {
        // given
        List<String> inputList = DataSet.namesList();

        // when
        //startTimer();
        List<String> resultList = parallelStreamsExample.stringTransform1(inputList, isParallel);
        //timeTaken();

        //then
        assertEquals(4, resultList.size());
        resultList.forEach(name -> assertTrue(name.contains("-")));
    }

    @Test
    void stringTransformToLowerCase() {
        // given
        List<String> inputList = DataSet.namesList();

        // when
        startTimer();
        List<String> resultList = parallelStreamsExample.stringTransformToLowerCase(inputList);
        timeTaken();

        //then
        assertEquals(4, resultList.size());
        resultList.forEach(name -> assertTrue(name == name.toLowerCase()));
    }
}