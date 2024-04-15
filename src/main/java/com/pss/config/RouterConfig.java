package com.pss.config;

import com.pss.handler.MessageHandler;
import com.pss.router.MessageRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RouterConfig {
    @Bean(name = "messageRouter")
    MessageRouter<MessageHandler> buildMessageRouter(@Autowired  List<MessageHandler> actionHandlers){
        return new MessageRouter<>(actionHandlers);
    }

}
