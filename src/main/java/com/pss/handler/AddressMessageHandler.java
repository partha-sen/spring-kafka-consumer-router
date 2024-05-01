package com.pss.handler;


import com.pss.model.ActionType;
import com.pss.model.Address;
import com.tiny.router.annotation.RouteEntry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AddressMessageHandler implements MessageHandler {

    @RouteEntry(action=ActionType.UPDATE_ADDRESS)
    public void updateAddress(Address address) {
        log.info("Address "+address);
    }
}
