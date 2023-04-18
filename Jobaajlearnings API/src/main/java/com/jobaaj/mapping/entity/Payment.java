package com.jobaaj.mapping.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name="payment")
public class Payment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pay_id")
	private int payId;
	
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="payment_type")
	private int paymentType;
	
	@Column(name="amount")
	private float amount;
	
	@Column(name="date_added")
	private Long dateAdded;
	
	@Column(name="status")
	private String status;
	
	@Column(name="transaction_id")
	private String transactionId;
	
	@Column(name="coupon_id")
	private String couponId;


}
