package com.pss.consumer;

import com.pss.handler.ProfileMessageHandler;
import com.tiny.router.MessageRouter;
import com.tiny.router.model.MessageEnvelop;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProfileConsumer {
    private final MessageRouter<ProfileMessageHandler> messageRouter;

    public ProfileConsumer(MessageRouter<ProfileMessageHandler> messageRouter) {
        this.messageRouter = messageRouter;
    }

    @KafkaListener(topics = "route.profile.topic", groupId = "group_id")
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
