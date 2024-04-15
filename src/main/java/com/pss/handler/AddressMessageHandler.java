package com.pss.handler;

import com.pss.annotation.PayloadAction;
import com.pss.model.ActionType;
import com.pss.model.Address;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AddressMessageHandler implements MessageHandler {

    @PayloadAction(ActionType.UPDATE_ADDRESS)
    public void updateAddress(Address address) {
        log.info("Address "+address);
    }
}
