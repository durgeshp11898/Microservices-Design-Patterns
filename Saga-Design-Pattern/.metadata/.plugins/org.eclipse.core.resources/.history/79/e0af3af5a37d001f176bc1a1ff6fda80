package com.durgesh.learn.entity.service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.durgesh.learn.dto.OrderRequestDto;
import com.durgesh.learn.dto.PaymentRequestDto;
import com.durgesh.learn.entity.UserBalance;
import com.durgesh.learn.entity.UserTransaction;
import com.durgesh.learn.enumm.PaymentStatus;
import com.durgesh.learn.event.OrderEvent;
import com.durgesh.learn.event.PaymentEvent;
import com.durgesh.learn.repository.UserBalanceRepository;
import com.durgesh.learn.repository.UserTransactionRepository;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Service
public class PaymentService {

	@Autowired
	private UserBalanceRepository userBalanceRepository;
	@Autowired
	private UserTransactionRepository userTransactionRepository;

	private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

	
	@PostConstruct
	public void initUserBalanceInDB() {
		userBalanceRepository.saveAll(Stream.of(new UserBalance(101, 5000),
				new UserBalance(102, 3000),
				new UserBalance(103, 4200),
				new UserBalance(104, 20000),
				new UserBalance(105, 999)).collect(Collectors.toList()));
	}

	/**
	 * // get the user id
	 * // check the balance availability
	 * // if balance sufficient -> Payment completed and deduct amount from DB
	 * // if payment not sufficient -> cancel order event and update the amount in DB
	 **/
	@Transactional
	public PaymentEvent newOrderEvent(OrderEvent orderEvent) {
		OrderRequestDto orderRequestDto = orderEvent.getOrderRequestDto();

		logger.info("newOrderEvent: "+orderRequestDto.toString());
		
		PaymentRequestDto paymentRequestDto = new PaymentRequestDto(orderRequestDto.getOrderId(),
				orderRequestDto.getUserId(), orderRequestDto.getAmount());

		return userBalanceRepository.findById(orderRequestDto.getUserId())
				.filter(ub -> ub.getPrice() > orderRequestDto.getAmount())
				.map(ub -> {
					ub.setPrice(ub.getPrice() - orderRequestDto.getAmount());
					userTransactionRepository.save(new UserTransaction(orderRequestDto.getOrderId(), orderRequestDto.getUserId(), orderRequestDto.getAmount()));
					return new PaymentEvent(paymentRequestDto, PaymentStatus.PAYMENT_COMPLETED);
				}).orElse(new PaymentEvent(paymentRequestDto, PaymentStatus.PAYMENT_FAILED));

	}

	@Transactional
	public void cancelOrderEvent(OrderEvent orderEvent) {

		userTransactionRepository.findById(orderEvent.getOrderRequestDto().getOrderId())
		.ifPresent(ut->{
			userTransactionRepository.delete(ut);
			userTransactionRepository.findById(ut.getUserId())
			.ifPresent(ub->ub.setAmount(ub.getAmount()+ut.getAmount()));
		});
	}
}
