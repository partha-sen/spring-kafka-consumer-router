package com.pss.handler;

import com.pss.model.ActionType;
import com.pss.model.Contact;
import com.tiny.router.annotation.RouteEntry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ContactProfileMessageHandler implements ProfileMessageHandler {

    @RouteEntry(action=ActionType.UPDATE_CONTACT)
    public void updateContract(Contact contact) {
        log.info("Contact "+contact);
    }
}
