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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="technology_details")
public class TechnologyProjectDetails {

	@Id
	@Column(name = "technology_details_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int technologyDetailsId;

	@Column(name = "technology_used")
	private String technologyUsed;
	
	@Column(name="project_details_id")
	private int projectDetailsId;

	public TechnologyProjectDetails(String technologyUsed, int projectDetailsId) {
		super();
		this.technologyUsed = technologyUsed;
		this.projectDetailsId = projectDetailsId;
	}
	
	
}
