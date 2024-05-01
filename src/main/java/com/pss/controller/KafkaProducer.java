package com.pss.controller;


import com.pss.model.Address;
import com.pss.model.Contact;
import com.tiny.router.model.MessageEnvelop;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send")
public class KafkaProducer {

    private static final String PROFILE_TOPIC = "route.profile.topic";

    private final KafkaTemplate<String, MessageEnvelop<?>> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, MessageEnvelop<?>> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/address")
    public void postAddress(@RequestBody MessageEnvelop<Address> msgEnvelop){
        kafkaTemplate.send(PROFILE_TOPIC, msgEnvelop);
    }

    @PostMapping("/contact")
    public void postContact(@RequestBody MessageEnvelop<Contact> msgEnvelop){
        kafkaTemplate.send(PROFILE_TOPIC, msgEnvelop);
    }

}
