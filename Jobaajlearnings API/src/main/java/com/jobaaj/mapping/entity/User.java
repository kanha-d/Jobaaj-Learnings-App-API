package com.jobaaj.mapping.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
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
@Table(name="users")
public class User implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int uid;
	@Column(name="full_name")
	private String name;
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
	@JoinTable(name = "enrol_course",
		joinColumns =  @JoinColumn(name="user_id"),
		inverseJoinColumns = @JoinColumn(name="course_id")
	)
	private List<Course> user_courses = new ArrayList<>();
	
	
}
