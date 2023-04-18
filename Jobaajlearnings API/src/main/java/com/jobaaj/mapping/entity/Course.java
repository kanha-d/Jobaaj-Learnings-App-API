package com.jobaaj.mapping.entity;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name="course")
public class Course {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "course_id")
	private int cid;
	private String title;

	@Column(name="short_description",length = 1000)
	private String shortDesc;
	@Column(length = 1000)
	private String description;
	@Column(length = 1000)
	private String outcomes;
	private String language;
	private Integer sub_category_id;
	private String section;
	@Column(length = 1000)
	private String requirements;
	private double price;
	@Column(name="discounted_price")
	private double discountedPrice;
	private String level;
	@Column(name="thumbnail")
	private String thumb;
	@Column(name="video_url")
	private String videoUrl;
	private String skills;
	@Column(name="last_modified")
	private Integer lastModified;
	@Column(name="course_type")
	private String courseType;
	@Column(name="is_top_course")
	private int isTopCourse;
	@Column(name="is_admin")
	private String isAdmin;
	private int status;
	@Column(name="course_overview_provider")
	private String courseOverviewProvider;
	@Column(name="meta_keywords")
	private String metaKeyword;
	@Column(name="meta_description")
	private String metaDesc;
	@Column(name="is_free_course")
	private String isFreeCourse;
	@Column(name="multi_instructor")
	private String multiInstrcutor;
	@Column(name="enable_drip_content")
	private String enableDripContent;
	@Column(name="applied_coupon")
	private String appliedCoupon;
	@Column(name="ref_by_c")
	private String refByC;
	@Column(name="ref_to_c")
	private String refToC;
	private String tags;
	
	@Column(name="date_added", length = 11, nullable = false)
	private Integer dateAdded;
	
		
	@OneToMany(mappedBy = "course",fetch=FetchType.LAZY)
	private List<Rating> rating = new ArrayList<>();
	
	
	@OneToMany(mappedBy = "course",fetch=FetchType.LAZY)
	private List<Section> sections = new ArrayList<>();
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name = "cat_id")
	private Category category;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name = "trainer_id")
	private Trainer trainer;


	@ManyToMany(mappedBy="courses")
	private List<Student> students = new ArrayList<>();
	

//	@ManyToOne
//	private Rating rating;
	

}
