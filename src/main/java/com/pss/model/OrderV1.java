package com.pss.model;


import lombok.Data;

import java.util.Date;

@Data
public class OrderV1 {
    Long productId;
    Integer quality;
    Date date;

}
