package com.pss.consumer;

import com.pss.handler.AddressMessageHandler;
import com.pss.handler.ContactMessageHandler;
import com.pss.model.Address;
import com.pss.model.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class DemoConsumerTest {

    private DemoConsumer demoConsumer;
    @Spy
    private AddressMessageHandler addressMessageHandler = new AddressMessageHandler();
    @Spy
    private ContactMessageHandler contactMessageHandler = new ContactMessageHandler();

    @BeforeEach
    public void setup() {
        demoConsumer = new DemoConsumer(List.of(addressMessageHandler, contactMessageHandler));
    }


    @Test
    public void testAddressHandler() throws InvocationTargetException, IllegalAccessException {
        String payload = """
                {
                  "action": "Update_Address",
                  "payload": {
                    "state": "Delhi",
                    "country": "India",
                    "areaCode": "110011",
                    "addressLine1": "chanakyapuri",
                    "addressLine2": "Nehru Park"
                  }
                }
                """;
        demoConsumer.consume(payload);
        verify(addressMessageHandler, times(1)).processMessage(any(Address.class));
    }

    @Test
    public void testContactHandler() throws InvocationTargetException, IllegalAccessException {
        String payload = """
                {
                  "action": "Update_Contact",
                  "payload": {
                    "mobileNo": "1111100000",
                    "email": "user@maildomain.com"
                  }
                }
                """;
        demoConsumer.consume(payload);
        verify(contactMessageHandler, times(1)).processMessage(any(Contact.class));
    }

}