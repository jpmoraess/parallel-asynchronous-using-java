package com.learnjava.parallelstreams;

import com.learnjava.util.DataSet;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParallelStreamPerformanceTest {

    ParallelStreamPerformance parallelStreamPerformance = new ParallelStreamPerformance();

    @Test
    void sumUsingIntStream() {
        // given

        // when
        int sum = parallelStreamPerformance.sumUsingIntStream(1000000, false);
        System.out.println("SUM: " + sum);

        // then
        assertEquals(1784293664, sum);
    }

    @Test
    void sumUsingIntStream_Parallel() {
        // given

        // when
        int sum = parallelStreamPerformance.sumUsingIntStream(1000000, true);
        System.out.println("SUM: " + sum);

        // then
        assertEquals(1784293664, sum);
    }

    @Test
    void sumUsingList() {
        // given
        int size = 1000000;
        ArrayList<Integer> inputList = DataSet.generateArrayList(size);

        // when
        int sum = parallelStreamPerformance.sumUsingList(inputList, false);
        System.out.println("SUM: " + sum);

        // then
        assertEquals(1784293664, sum);
    }

    @Test
    void sumUsingList_Parallel() {
        // given
        int size = 1000000;
        ArrayList<Integer> inputList = DataSet.generateArrayList(size);

        // when
        int sum = parallelStreamPerformance.sumUsingList(inputList, true);
        System.out.println("SUM: " + sum);

        // then
        assertEquals(1784293664, sum);
    }
}