package com.pss.consumer;

import com.pss.handler.InventoryHandler;
import com.tiny.router.MessageRouter;
import com.tiny.router.model.MessageEnvelop;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InventoryConsumer {
    private final MessageRouter<InventoryHandler> messageRouter;

    public InventoryConsumer(MessageRouter<InventoryHandler> messageRouter) {
        this.messageRouter = messageRouter;
    }

    @KafkaListener(topics = "route.inventory.topic", groupId = "group_id")
    public void consume(MessageEnvelop<String> message) {
        log.info("message route path {}", message.routePath());
        log.info("message payload {}", message.getPayload());
        try {
            messageRouter.route(message);
            log.info("message dispatch successfully");
        } catch (RuntimeException e) {
            log.info("message dispatch failed");
            log.error("Fail to route message ", e);
        }
    }

}
