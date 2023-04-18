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
@Table(name="coupon")
public class Coupon {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="coupon_id")
	private int couponId;
	
	private String code;
	@Column(name="discount_percentage")
	private int discountValue;
	
	@Column(name="course_used")
	private String courseUsed;
	
	@Column(name="coupon_limit")
	private int courseLimit;
	
	@Column(name="used_limit")
	private int usedLimit;
	
	@Column(name="expiry_date")
	private Date expDate;
	
	@Column(name="created_at", length = 11, nullable = false)
	private int createdAt;
	
	
//	@OneToMany(mappedBy="category",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
//	private List<Course> course = new ArrayList<>();
//


}
