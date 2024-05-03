package com.pss.consumer;

import com.pss.handler.impl.AddressHandler;
import com.pss.handler.impl.ContactHandler;
import com.pss.handler.ProfileHandler;
import com.pss.model.ActionType;
import com.pss.model.Address;
import com.pss.model.Contact;
import com.tiny.router.MessageRouter;
import com.tiny.router.exception.DuplicateRouteException;
import com.tiny.router.exception.InvalidRoutingMethodSignatureException;
import com.tiny.router.exception.RouteEntryMissingException;
import com.tiny.router.model.MessageEnvelop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class ProfileConsumerTest {

    private ProfileConsumer profileConsumer;
    @Spy
    private AddressHandler addressMessageHandler = new AddressHandler();
    @Spy
    private ContactHandler contactMessageHandler = new ContactHandler();

    @BeforeEach
    public void setup() {
        try {
            MessageRouter<ProfileHandler> messageRouter = new MessageRouter<>(List.of(addressMessageHandler, contactMessageHandler));
            profileConsumer = new ProfileConsumer(messageRouter);
        } catch (DuplicateRouteException | InvalidRoutingMethodSignatureException | RouteEntryMissingException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void testAddressHandler() {

        String payload = """
                    {
                    "state": "Delhi",
                    "country": "India",
                    "areaCode": "110011",
                    "addressLine1": "chanakyapuri",
                    "addressLine2": "Nehru Park"
                  }
                """;
        MessageEnvelop<String> messageEnvelop = new MessageEnvelop<>(ActionType.UPDATE_ADDRESS, payload);
        profileConsumer.consume(messageEnvelop);
        verify(addressMessageHandler, times(1)).updateAddress(any(Address.class));
    }

    @Test
    public void testContactHandler() {
        String payload = """
                {
                 "mobileNo": "1111100000",
                 "email": "user@maildomain.com"
                }
                """;
        MessageEnvelop<String> messageEnvelop = new MessageEnvelop<>(ActionType.UPDATE_CONTACT, payload);
        profileConsumer.consume(messageEnvelop);
        verify(contactMessageHandler, times(1)).updateContract(any(Contact.class));
    }

}