package com.jobaaj.mapping.entity;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name="section")
public class Section {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(name="section_id")
	private int sid;
	
	private String title;
	

	@Column(name="sec_type")
	private String sectionType;
	

	@Column(name="is_free")
	private int isFree;
	
	@Column(name="s_order")
	private int order;
	
	@ManyToOne
	@JoinColumn(name="course_id")
	private Course course;
	
	@OneToMany(mappedBy = "section",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Lesson> lessons = new ArrayList<>();
	
	
//	@OneToMany
//	private Lesson lesson;
//	
	
	
	
//	@OneToMany(mappedBy="category",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
//	private List<Course> course = new ArrayList<>();
//


}
