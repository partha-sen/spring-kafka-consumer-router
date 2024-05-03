package com.pss.handler.impl;

import com.pss.handler.ProfileHandler;
import com.pss.model.ActionType;
import com.pss.model.Contact;
import com.tiny.router.annotation.RouteEntry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ContactHandler implements ProfileHandler {

    @RouteEntry(action=ActionType.UPDATE_CONTACT)
    public void updateContract(Contact contact) {
        log.info("Contact "+contact);
    }
}
