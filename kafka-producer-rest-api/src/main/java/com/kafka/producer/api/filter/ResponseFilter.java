package com.kafka.producer.api.filter;


import com.kafka.producer.api.util.ApplicationConstants;
import com.kafka.producer.api.util.ApplicationContextUtil;
import com.kafka.producer.api.util.ApplicationRequestContextData;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order( Ordered.LOWEST_PRECEDENCE )
public class ResponseFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
            LOGGER.info("Before filterChain.doFilter");

            HttpServletResponse httpServletResponse = (HttpServletResponse)servletResponse;
            ApplicationRequestContextData applicationRequestContextData = ApplicationContextUtil.getApplicationRequestContextData();
            httpServletResponse.setHeader(ApplicationConstants.REQUEST_ID_HEADER, applicationRequestContextData.getRequestId());
            httpServletResponse.setHeader(ApplicationConstants.CORRELATION_ID_HEADER, applicationRequestContextData.getCorrelationId() );

            filterChain.doFilter(servletRequest,servletResponse);
            LOGGER.info("After filterChain.doFilter");
        }
        catch( Exception ex ) {
            LOGGER.error(ex.getMessage());
            throw ex;
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
