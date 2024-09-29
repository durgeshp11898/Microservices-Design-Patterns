package com.saga.order.entity;



import com.saga.order.enumm.OrderStatus;
import com.saga.order.enumm.PaymentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PURCHASE_ORDER_TBL")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer userId;
	private Integer productId;
	private Integer price;

	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;
}