package com.pss.handler;

import com.pss.annotation.PayloadAction;
import com.pss.model.ActionType;
import com.pss.model.Address;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AddressMessageHandler implements MessageHandler {

    @PayloadAction(ActionType.UPDATE_ADDRESS)
    public void processMessage(Address address) {
        log.info("Address "+address);
    }
}
