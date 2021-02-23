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
@Table(name = "technical_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TechnicalDetails {

	@Id
	@Column(name = "technical_details_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int technicalDetailsId;

	@Column(name = "technical_skills")
	private String technicalSkills;

	@Column(name = "tools")
	private String tools;

	@Column(name = "ratings")
	private int ratings;

	@Column(name = "personal_details_id")
	private int personalDetailsId;

	public TechnicalDetails(String technicalSkills, String tools, int ratings, int personalDetailsId) {
		super();
		this.technicalSkills = technicalSkills;
		this.tools = tools;
		this.ratings = ratings;
		this.personalDetailsId = personalDetailsId;
	}
	
	
	
	

}
