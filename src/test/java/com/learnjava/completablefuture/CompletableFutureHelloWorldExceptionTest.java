package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompletableFutureHelloWorldExceptionTest {

    @Mock
    HelloWorldService helloWorldService = mock(HelloWorldService.class);

    @InjectMocks
    CompletableFutureHelloWorldException hwcfe;

    @Test
    void helloWorld_3_Async_Calls_Handle() {
        // given
        when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception exception ;(("));
        when(helloWorldService.world()).thenCallRealMethod();

        // when
        String result = hwcfe.helloWorld_3_Async_Calls_Handle();

        // then
        assertEquals(" WORLD! HI COMPLETABLEFUTURE!", result);

    }

    @Test
    void helloWorld_3_Async_Calls_Handle_2() {
        // given
        when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception exception ;(("));
        when(helloWorldService.world()).thenThrow(new RuntimeException("Exception exception ;(("));

        // when
        String result = hwcfe.helloWorld_3_Async_Calls_Handle();

        // then
        assertEquals(" HI COMPLETABLEFUTURE!", result);

    }

    @Test
    void helloWorld_3_Async_Calls_Handle_3() {
        // given
        when(helloWorldService.hello()).thenCallRealMethod();
        when(helloWorldService.world()).thenCallRealMethod();

        // when
        String result = hwcfe.helloWorld_3_Async_Calls_Handle();

        // then
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", result);

    }

    @Test
    void helloWorld_3_Async_Calls_Exceptionally() {
        // given
        when(helloWorldService.hello()).thenCallRealMethod();
        when(helloWorldService.world()).thenCallRealMethod();

        // when
        String result = hwcfe.helloWorld_3_Async_Calls_Exceptionally();

        // then
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", result);
    }

    @Test
    void helloWorld_3_Async_Calls_Exceptionally_2() {
        // given
        when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception exception ;(("));
        when(helloWorldService.world()).thenThrow(new RuntimeException("Exception exception ;(("));

        // when
        String result = hwcfe.helloWorld_3_Async_Calls_Exceptionally();

        // then
        assertEquals(" HI COMPLETABLEFUTURE!", result);

    }

    @Test
    void helloWorld_3_Async_Calls_WhenComplete() {
        // given
        when(helloWorldService.hello()).thenCallRealMethod();
        when(helloWorldService.world()).thenCallRealMethod();

        // when
        String result = hwcfe.helloWorld_3_Async_Calls_WhenComplete();

        // then
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", result);
    }

    @Test
    void helloWorld_3_Async_Calls_WhenComplete_2() {
        // given
        when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception exception ;(("));
        when(helloWorldService.world()).thenThrow(new RuntimeException("Exception exception ;(("));

        // when
        String result = hwcfe.helloWorld_3_Async_Calls_WhenComplete();

        // then
        assertEquals(" HI COMPLETABLEFUTURE!", result);

    }
}