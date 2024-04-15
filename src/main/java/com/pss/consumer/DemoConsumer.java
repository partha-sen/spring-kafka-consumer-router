package com.pss.consumer;

import com.pss.handler.MessageHandler;
import com.pss.router.MessageRouter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

@Component
@Slf4j
public class DemoConsumer {
    private final MessageRouter<MessageHandler> messageRouter;

    public DemoConsumer(MessageRouter<MessageHandler> messageRouter) {
        this.messageRouter = messageRouter;
    }

    @KafkaListener(topics = "route.demo.topic", groupId = "group_id")
    public void consume(String message) throws InvocationTargetException, IllegalAccessException {
        log.info(message);
        messageRouter.route(message);
    }

}
