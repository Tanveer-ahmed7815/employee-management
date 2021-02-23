package com.tyss.employeemanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyss.employeemanagement.dao.ExperienceDetailsDao;
import com.tyss.employeemanagement.dto.ExpereincePersonalDetails;


@Service
public class ExperienceDetailsServiceImpl  implements ExperienceDetailsService {
	
	@Autowired
	ExperienceDetailsDao experienceDetailsDao;
	
	@Override
	public ExpereincePersonalDetails experienceDetails(ExpereincePersonalDetails experiencePersonalDetails) {
		
		
		return experienceDetailsDao.experienceEmpdetails(experiencePersonalDetails);
	}

}
