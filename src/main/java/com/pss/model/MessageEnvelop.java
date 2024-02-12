package com.pss.model;

import lombok.Data;

@Data
public class MessageEnvelop<P> {
    String action;
    P payload;
}
