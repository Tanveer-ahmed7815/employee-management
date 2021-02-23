package com.tyss.employeemanagement.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "experience_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceDetails {

	@Id
	@Column(name = "experience_details_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int experienceDetailsId;

	@Column(name = "designation")
	private String designation;

	@Column(name = "company")
	private String company;

	@Column(name = "start_date")
	@JsonFormat(pattern = "MM/YYYY")
	private Date startDate;

	@Column(name = "present_date")
	@JsonFormat(pattern = "DD/MM/YYYY")
	private Date presentDate;

	@Column(name = "city")
	private String city;

	@Column(name = "duration_notice_period")
	private int durationNoticePeriod;

	@Column(name = "personal_details_id")
	private int personalDetailsId;

	public ExperienceDetails(int experienceDetailsId, String designation, String company, Date startDate,
			Date presentDate, String city, int durationNoticePeriod) {
		super();
		this.experienceDetailsId = experienceDetailsId;
		this.designation = designation;
		this.company = company;
		this.startDate = startDate;
		this.presentDate = presentDate;
		this.city = city;
		this.durationNoticePeriod = durationNoticePeriod;
	}

}
