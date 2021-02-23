package com.tyss.employeemanagement.dto;

import java.sql.Blob;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "personal_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpereincePersonalDetails {

	@Id
	@Column(name = "personal_details_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int personalDetailsId;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;



	@Column(name = "current_city")
	private String currentCity;

	@Column(name = "resume")
	private Blob resume;

	@OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name = "personal_details_id", referencedColumnName = "personal_details_id")
	private List<ExperienceDetails> experienceDetails;

	@OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name = "personal_details_id", referencedColumnName = "personal_details_id")
	private List<EducationalDetails> educationalDetails;

	@OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name = "personal_details_id", referencedColumnName = "personal_details_id")
	private List<ProjectDetails> projectDetails;

	@OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name = "personal_details_id", referencedColumnName = "personal_details_id")
	private List<TechnicalDetails> technicalDetails;
}
