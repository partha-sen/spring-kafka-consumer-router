package com.pss.config;

import com.pss.handler.ProfileMessageHandler;
import com.tiny.router.MessageRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RouterConfig {
    @Bean
    MessageRouter<ProfileMessageHandler> buildMessageRouter(@Autowired  List<ProfileMessageHandler> actionHandlers) throws Exception {
        return new MessageRouter<>(actionHandlers);
    }

}
