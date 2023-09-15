package com.bhavya.orderservice.controller;

import com.bhavya.orderservice.dto.OrderRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {
  @PostMapping
  public String placeOrder(@RequestBody OrderRequest orderRequest) {
    return "Order Places succesfully.";
  }
}
