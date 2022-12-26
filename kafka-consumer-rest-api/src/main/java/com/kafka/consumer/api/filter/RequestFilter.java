package com.kafka.consumer.api.filter;


import com.kafka.consumer.api.util.ApplicationConstants;
import com.kafka.consumer.api.util.ApplicationRequestContextData;
import com.kafka.consumer.api.util.ThreadLocalRequestContext;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.io.IOException;
import java.util.UUID;

@Component
@Order(2)
public class RequestFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // Read HTTP Request parameters sent by client
        try{
            LOGGER.info("Before filterChain.doFilter");

            ApplicationRequestContextData applicationRequestContextData = new ApplicationRequestContextData();
            HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
            String requestId = httpServletRequest.getHeader(ApplicationConstants.REQUEST_ID_HEADER);
            String correlationId = httpServletRequest.getHeader(ApplicationConstants.CORRELATION_ID_HEADER);

            if( requestId == null || requestId.isEmpty() )
                requestId = UUID.randomUUID().toString();

            if( correlationId == null || correlationId.isEmpty() )
                correlationId = requestId;

            applicationRequestContextData.setRequestId(requestId);
            applicationRequestContextData.setCorrelationId(correlationId);

            httpServletRequest.setAttribute( ApplicationConstants.APPLICATION_REQUEST_CONTEXT_DATA_KEY, applicationRequestContextData );
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            RequestContextHolder.setRequestAttributes(requestAttributes, true);
            ThreadLocalRequestContext.set(applicationRequestContextData);

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