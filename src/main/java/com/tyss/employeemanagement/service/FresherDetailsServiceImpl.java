package com.tyss.employeemanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyss.employeemanagement.dao.FresherDetailsdao;
import com.tyss.employeemanagement.dto.FresherPersonalDetails;

@Service
public class FresherDetailsServiceImpl implements FresherDetailsService {

	@Autowired
	FresherDetailsdao fresherDetailsDao;

	@Override
	public FresherPersonalDetails fresherDetails(FresherPersonalDetails fresherPersonalDetails) {

		return fresherDetailsDao.fersherEmpdetails(fresherPersonalDetails);
	}

}
