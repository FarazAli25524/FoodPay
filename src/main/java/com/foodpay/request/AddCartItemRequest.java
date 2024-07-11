package com.foodpay.request;

import lombok.Data;

import java.util.List;

@Data
public class AddCartItemRequest {
    private long foodId;
    private int quantity;
    private List<String> ingredient;
}
