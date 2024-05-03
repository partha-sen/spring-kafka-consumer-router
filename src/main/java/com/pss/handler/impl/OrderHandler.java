package com.pss.handler.impl;

import com.pss.handler.InventoryHandler;
import com.pss.model.ActionType;
import com.pss.model.OrderV1;
import com.pss.model.OrderV2;
import com.tiny.router.annotation.RouteEntry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class OrderHandler implements InventoryHandler {

    @RouteEntry(action = ActionType.PLACE_ORDER, version = "v1")
    public void placeOrderV1(OrderV1 orderV1){
     log.info("Order "+ orderV1);
    }

    @RouteEntry(action = ActionType.PLACE_ORDER, version = "v2")
    public void placeOrderV2(OrderV2 orderV2){
        log.info("Order "+ orderV2);
    }
}
