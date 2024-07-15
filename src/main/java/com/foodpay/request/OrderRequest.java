package com.foodpay.request;

import com.foodpay.model.Address;
import lombok.Data;

@Data
public class OrderRequest {
    private long restaurantId;
    private Address deliveryAddress;
}
