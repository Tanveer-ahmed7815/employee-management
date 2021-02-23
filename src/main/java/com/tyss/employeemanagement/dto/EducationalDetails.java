package com.tyss.employeemanagement.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "educational_details")

public class EducationalDetails {

	@Id
	@Column(name = "educational_details_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int eductaionDetailsId;

	@Column(name = "highest_qualification")
	private String highestQualification;

	@Column(name = "courses")
	private String courses;

	@Column(name = "institution_name")
	private String institutionName;

	@Column(name = "passing_year")
	private int passingYear;

	@Column(name = "personal_details_id")
	private int personalDetailsId;

	public EducationalDetails(String highestQualification, String courses, String institutionName, int passingYear,
			int personalDetailsId) {
		super();
		this.highestQualification = highestQualification;
		this.courses = courses;
		this.institutionName = institutionName;
		this.passingYear = passingYear;
		this.personalDetailsId = personalDetailsId;
	}

}
