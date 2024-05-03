package com.pss.config;

import com.pss.handler.InventoryHandler;
import com.pss.handler.ProfileHandler;
import com.tiny.router.MessageRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RouterConfig {
    @Bean
    MessageRouter<ProfileHandler> profileMessageRouter(@Autowired  List<ProfileHandler> handlers) throws Exception {
        return new MessageRouter<>(handlers);
    }

    @Bean
    MessageRouter<InventoryHandler> inventoryMessageRouter(@Autowired  List<InventoryHandler> handlers) throws Exception {
        return new MessageRouter<>(handlers);
    }

}
