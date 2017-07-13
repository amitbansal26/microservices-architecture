package com.ref.arch.elk.logging.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import net.logstash.logback.marker.Markers;

@Component
public class RequestLoggingFilter implements Filter {

	private final Logger LOGGER = LoggerFactory.getLogger(RequestLoggingFilter.class);

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        String url = request.getServletPath();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        chain.doFilter(req, res);
        stopWatch.stop();

        Map<String, Object> markers = new HashMap<>();
        markers.put("res.status", response.getStatus());
        markers.put("req.executionTime", stopWatch.getTotalTimeMillis());
        LOGGER.info(Markers.appendEntries(markers), "Execution time for {} {} is {} ms", url, request.getMethod(), stopWatch.getTotalTimeMillis());
    }

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }
	
	
}
