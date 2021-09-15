package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
import static org.junit.Assert.assertEquals;

class CompletableFutureHelloWorldTest {

    HelloWorldService hws = new HelloWorldService();
    CompletableFutureHelloWorld cfhw = new CompletableFutureHelloWorld(hws);

    @Test
    void helloWorld() {
        // given

        // when
        CompletableFuture<String> completableFuture = cfhw.helloWorld();

        // then
        completableFuture
                .thenAccept(s -> {
                    assertEquals("HELLO WORLD", s);
                })
                .join();
    }

    @Test
    void helloWorld_withSize() {
        cfhw.helloWorld_withSize()
                .thenAccept(s -> {
                    assertEquals("11 - HELLO WORLD", s);
                }).join();
    }

    @Test
    void helloWorld_Multiple_Async_Calls() {
        // given

        // when
        String helloWorld = cfhw.helloWorld_Multiple_Async_Calls();

        // then
        assertEquals("HELLO WORLD!", helloWorld);
    }

    @Test
    void helloWorld_3_Async_Calls() {
        // given

        // when
        String helloWorld = cfhw.helloWorld_3_Async_Calls();

        // then
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", helloWorld);
    }

    @Test
    void helloWorld_3_Async_Calls_Log() {
        // given

        // when
        String helloWorld = cfhw.helloWorld_3_Async_Calls_Log();

        // then
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", helloWorld);
    }

    @Test
    void helloWorld_3_Async_Calls_Log_Async() {
        // given

        // when
        String helloWorld = cfhw.helloWorld_3_Async_Calls_Log_Async();

        // then
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", helloWorld);
    }

    @Test
    void helloWorld_3_Async_Calls_Custom_ThreadPool() {
        // given

        // when
        String helloWorld = cfhw.helloWorld_3_Async_Calls_Custom_ThreadPool();

        // then
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", helloWorld);
    }

    @Test
    void helloWorld_3_Async_Calls_Custom_ThreadPool_Async() {
        // given

        // when
        String helloWorld = cfhw.helloWorld_3_Async_Calls_Custom_ThreadPool_Async();

        // then
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", helloWorld);
    }

    @Test
    void helloWorld_4_Async_Calls() {
        // given

        // when
        String helloWorld = cfhw.helloWorld_4_Async_Calls();

        // then
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE! BYE!", helloWorld);
    }

    @Test
    void helloWorld_thenCompose() {
        // given
        startTimer();

        // when
        CompletableFuture<String> completableFuture = cfhw.helloWorld_thenCompose();

        // then
        completableFuture
                .thenAccept(s -> {
                    assertEquals("HELLO WORLD!", s);
                })
                .join();

        timeTaken();
    }

    @Test
    void anyOf() {
        // given

        // when
        String helloWorld = cfhw.anyOf();

        // then
        assertEquals("hello world", helloWorld);
    }
}