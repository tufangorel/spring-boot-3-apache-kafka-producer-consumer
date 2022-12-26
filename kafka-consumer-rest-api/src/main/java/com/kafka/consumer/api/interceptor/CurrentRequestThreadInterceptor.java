package com.kafka.consumer.api.interceptor;


import com.kafka.consumer.api.util.ThreadLocalRequestContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;



@Component
public class CurrentRequestThreadInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalRequestContext.remove();
    }

}