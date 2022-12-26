package com.kafka.producer.api.util;

public class ThreadLocalRequestContext {

    private static final ThreadLocal<ApplicationRequestContextData> APPLICATION_REQUEST_CONTEXT_DATA_THREAD_LOCAL =
            ThreadLocal.withInitial( ApplicationRequestContextData::new );

    public static void set( ApplicationRequestContextData applicationRequestContextData ) {
        APPLICATION_REQUEST_CONTEXT_DATA_THREAD_LOCAL.set( applicationRequestContextData );
    }

    public static ApplicationRequestContextData get() {
        return APPLICATION_REQUEST_CONTEXT_DATA_THREAD_LOCAL.get();
    }

    public static void remove(){
        APPLICATION_REQUEST_CONTEXT_DATA_THREAD_LOCAL.remove();
    }
}
