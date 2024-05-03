package com.pss.model;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Product {
    Long id;
    String category;
    String name;
    String manufacturer;
}
