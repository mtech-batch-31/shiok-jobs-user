package com.mtech.sjmsuser.config;

import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.times;

public class LoggingFilterTest {
    private final LoggingFilter loggingFilter = Mockito.spy(new LoggingFilter());

    @Test
    public void testDoFilter() throws IOException, ServletException {

        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        MockFilterChain chain = new MockFilterChain();
        loggingFilter.doFilter(req, res, chain);
        Mockito.verify(loggingFilter, times(1)).doFilter(Mockito.eq(req), Mockito.eq(res), Mockito.eq(chain));
    }
}
