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
@Table(name="lesson")
public class Lesson {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(name="lesson_id")
	private int lid;
	
	private String title;
	private String duration;
	
	
	@Column(name="course_id")
	private int courseId;
	
	@ManyToOne
	@JoinColumn(name="section_id")
	private Section section;
	
	
	@Column(name="video_type")
	private String videoType;

	@Column(name="video_url")
	private String videoUrl;

	@Column(name="lesson_type")
	private String lessonType;
	
	private String attachment;
	
	private String summary;
	
	@Column(name="is_free")
	private int isFree;
	
	@Column(name="l_order")
	private int order;

	
	@Column(name="date_added", length = 11, nullable = false)
	private int dateAdded;
	
	
//	@OneToMany(mappedBy="category",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
//	private List<Course> course = new ArrayList<>();
//


}
