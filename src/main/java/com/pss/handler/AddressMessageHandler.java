package com.pss.handler;

import com.pss.model.ActionType;
import com.pss.model.Address;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component(ActionType.UPDATE_ADDRESS)
public class AddressMessageHandler implements DemoMessageHandler<Address>{

    @Override
    public Class<Address> payloadClass() {
       return Address.class;
    }


    @Override
    public void processMessage(Object obj) {
        Address address = payloadClass().cast(obj);
        log.info("Address "+address);
    }
}
