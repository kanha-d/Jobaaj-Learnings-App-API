package com.jobaaj.mapping.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name="student")
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="s_id")
	private int sid;
	
	private String name;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	private String gender;
	private String biography;
	private int role_id;
	private int date_added;
	private String contact;
	@Column(nullable = true)
	private String verification_code;
	private int status;
	private String login_type;
	private String oauth_provider;
	private String dob;
	private String image;
	private String skills;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "enrol",
		joinColumns =  @JoinColumn(name="s_id"),
		inverseJoinColumns = @JoinColumn(name="course_id")
	)
	private List<Course> courses = new ArrayList<>();
//


}
