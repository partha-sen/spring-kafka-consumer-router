package com.pss.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    String state;
    String country;
    String areaCode;
    String addressLine1;
    String addressLine2;
}
