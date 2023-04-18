package com.jobaaj.mapping.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "enrol")
public class Enrol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "enrol_id")
	private int eid;

	@Column(name = "s_id")
	private int sId;

	@Column(name = "course_id")
	private int courseId;

	@Column(name = "date_added", length = 11, nullable = false)
	private long dateAdded;

}
