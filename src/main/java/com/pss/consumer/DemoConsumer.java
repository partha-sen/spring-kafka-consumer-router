package com.pss.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pss.handler.DemoMessageHandler;
import com.pss.model.MessageEnvelop;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class DemoConsumer {
    private final Map<String, DemoMessageHandler<?>> actionHandlers;
    private final ObjectMapper mapper = new ObjectMapper();

    public DemoConsumer(Map<String, DemoMessageHandler<?>> actionHandlers) {
        this.actionHandlers = actionHandlers;
    }

    @KafkaListener(topics = "route.demo.topic", groupId = "group_id")
    public void consume(String message) {

        log.info(message);

        MessageEnvelop<?> msgEnvelop = toMessageEnvelop(message);
        String action = msgEnvelop.getAction();

        DemoMessageHandler<?> msgHandler = actionHandlers.get(action);
        Object payload = toPayloadType(msgEnvelop.getPayload(), msgHandler.payloadClass());

        msgHandler.processMessage(payload);


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
