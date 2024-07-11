package com.foodpay.request;

import lombok.Data;

@Data
public class UpdateCartItemRequest {
    private long cartItemId;
    private int quantity;

}
