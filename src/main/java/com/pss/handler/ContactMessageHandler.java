package com.pss.handler;

import com.pss.annotation.PayloadAction;
import com.pss.model.ActionType;
import com.pss.model.Contact;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContactMessageHandler implements MessageHandler {

    @PayloadAction(ActionType.UPDATE_CONTACT)
    public void updateContract(Contact contact) {
        log.info("Contact "+contact);
    }
}
