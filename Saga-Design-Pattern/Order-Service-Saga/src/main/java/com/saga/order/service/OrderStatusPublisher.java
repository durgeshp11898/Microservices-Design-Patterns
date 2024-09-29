package com.saga.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saga.order.dto.OrderRequestDto;
import com.saga.order.enumm.OrderStatus;
import com.saga.order.event.OrderEvent;

import reactor.core.publisher.Sinks;

@Service
public class OrderStatusPublisher {

	@Autowired
	private Sinks.Many<OrderEvent> orderSinks;


	public void publishOrderEvent(OrderRequestDto orderRequestDto, OrderStatus orderStatus){
		OrderEvent orderEvent=new OrderEvent(orderRequestDto,orderStatus);
		orderSinks.tryEmitNext(orderEvent);
	}
}
