package com.pss.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Address {
    String state;
    String country;
    String areaCode;
    String addressLine1;
    String addressLine2;
}
