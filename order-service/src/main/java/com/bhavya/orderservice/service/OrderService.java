package com.bhavya.orderservice.service;

import com.bhavya.orderservice.dto.OrderLineItemsDto;
import com.bhavya.orderservice.dto.OrderRequest;
import com.bhavya.orderservice.model.Order;
import com.bhavya.orderservice.model.OrderLineItems;
import com.bhavya.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

  @Autowired
  private OrderRepository orderRepository;

  public void placeOrder(OrderRequest orderRequest) {
    Order order = new Order();
    order.setOrderNumber(UUID.randomUUID().toString());

    List<OrderLineItems> orderLineItems =
        orderRequest.getOrderLineItemsDtos()
                .stream()
                .map(this::mapToDto)
                .toList();

    order.setOrderLineItemsList(orderLineItems);
    orderRepository.save(order);
  }

  private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
    OrderLineItems orderLineItems = new OrderLineItems();
    orderLineItems.setPrice(orderLineItemsDto.getPrice());
    orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
    orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
    return orderLineItems;
  }
}
