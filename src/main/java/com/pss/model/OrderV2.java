package com.pss.model;


import lombok.Data;

import java.util.Date;

@Data
public class OrderV2 {
    Long productId;
    Integer quality;
    Integer userId;
    Date date;
}
