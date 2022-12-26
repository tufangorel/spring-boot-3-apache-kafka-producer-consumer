package com.kafka.consumer.api.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ApplicationContextUtil {

    public static ApplicationRequestContextData getApplicationRequestContextData() {

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if( requestAttributes != null ) {

            HttpServletRequest httpServletRequest = ((ServletRequestAttributes)(requestAttributes)).getRequest();
            ApplicationRequestContextData applicationRequestContextData =
                    (ApplicationRequestContextData) httpServletRequest.getAttribute( ApplicationConstants.APPLICATION_REQUEST_CONTEXT_DATA_KEY );

            if( applicationRequestContextData == null )
                applicationRequestContextData = ThreadLocalRequestContext.get();

            return applicationRequestContextData;
        }
        else {
            return ThreadLocalRequestContext.get();
        }
    }
}
