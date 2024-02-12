package com.pss.handler;



public interface DemoMessageHandler<T> {
    Class<T> payloadClass();

    void processMessage(Object payload);
}
