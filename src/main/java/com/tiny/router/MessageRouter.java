package com.tiny.router;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pss.model.MessageEnvelop;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class MessageRouter <T>{
    private final Map<String, Method> actionMethods = new HashMap<>();
    private final Map<String, T> actionObjects = new HashMap<>();
    private final ObjectMapper mapper = new ObjectMapper();

    public MessageRouter(List<T> actionHandlers){

        for(T actionHandler : actionHandlers){
            boolean isPayloadActionAnnotationPresent = false;
            Method[] methods = actionHandler.getClass().getMethods();
            for (Method method : methods) {
                if(method.isAnnotationPresent(PayloadAction.class)){
                    PayloadAction annotation = method.getAnnotation(PayloadAction.class);
                    this.actionMethods.put(annotation.value(), method);
                    this.actionObjects.put(annotation.value(), actionHandler);
                    isPayloadActionAnnotationPresent = true;
                    break;
                }
            }
            if(!isPayloadActionAnnotationPresent){
                throw new RuntimeException("PayloadAction annotation missing on method of " + actionHandler.getClass());
            }
        }
    }


    public void route(String message) throws InvocationTargetException, IllegalAccessException {
        MessageEnvelop<?> msgEnvelop = toMessageEnvelop(message);
        String action = msgEnvelop.getAction();
        Method method = actionMethods.get(action);
        Class<?> parameterType = method.getParameterTypes()[0];
        Object payload = toPayloadType(msgEnvelop.getPayload(), parameterType);
        method.invoke(actionObjects.get(action), payload);
    }


    private MessageEnvelop<?> toMessageEnvelop(String message){
        try {
            return mapper.readValue(message, new TypeReference<>(){});
        } catch (JsonProcessingException e) {
            log.info("Json processing exception {}", e.getMessage());
            throw new RuntimeException("Invalid message format ", e);
        }
    }

    private Object toPayloadType(Object payload, Class<?> payloadClass){
        try{
            return mapper.convertValue(payload, payloadClass);
        }catch(RuntimeException e){
            throw new RuntimeException("Fail to convert payload to "+ payloadClass.getTypeName(), e);
        }
    }


}
