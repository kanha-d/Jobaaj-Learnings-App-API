package com.jobaaj.mapping.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name="rating")
public class Rating {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="rating_id")
	private int rid;
	
	private double rating;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User userId;

	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;
	

	@Column(name="date_added", length = 11, nullable = false)
	private int dateAdded;
	
	
	@Column(length = 1000)
	private String review;
	
	private int status;
	
	
	

}
