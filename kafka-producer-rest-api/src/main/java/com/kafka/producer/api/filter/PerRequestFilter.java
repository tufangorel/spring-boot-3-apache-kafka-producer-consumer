package com.kafka.producer.api.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;


@Component
@Order(1)
public class PerRequestFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(PerRequestFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            LOGGER.info("Before filterChain.doFilter");
            filterChain.doFilter(request,response);
            LOGGER.info("After filterChain.doFilter");
        }
        catch( Exception ex ) {
            LOGGER.error(ex.getMessage());
            throw ex;
        }
    }
}
