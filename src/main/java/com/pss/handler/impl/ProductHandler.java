package com.pss.handler.impl;

import com.pss.handler.InventoryHandler;
import com.pss.model.ActionType;
import com.pss.model.Product;
import com.tiny.router.annotation.RouteEntry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class ProductHandler implements InventoryHandler {

    @RouteEntry(action = ActionType.REGISTER_PRODUCT)
    public void registerProduct(Product product){
     log.info("Product " + product);
    }
}
