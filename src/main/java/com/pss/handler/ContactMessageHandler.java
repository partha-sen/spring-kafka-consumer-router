package com.pss.handler;

import com.pss.model.ActionType;
import com.pss.model.Contact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component(ActionType.UPDATE_CONTACT)
public class ContactMessageHandler implements DemoMessageHandler<Contact>{

    @Override
    public Class<Contact> payloadClass() {
        return Contact.class;
    }
    public void processMessage(Object obj) {
        Contact contact = payloadClass().cast(obj);
        log.info("Contact "+contact);
    }
}
