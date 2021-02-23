package com.tyss.employeemanagement.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tyss.employeemanagement.dto.EducationalDetails;
import com.tyss.employeemanagement.dto.FresherPersonalDetails;
import com.tyss.employeemanagement.dto.ProjectDetails;
import com.tyss.employeemanagement.dto.TechnicalDetails;
import com.tyss.employeemanagement.dto.TechnologyProjectDetails;
import com.tyss.employeemanagement.utility.UtilityService;

@Repository
public class FresherDetailsDaoImpl implements FresherDetailsdao {

	@Autowired
	UtilityService utilityService;

	@PersistenceContext
	EntityManager em;

	@Transactional

	@Override
	public FresherPersonalDetails fersherEmpdetails(FresherPersonalDetails fresherPersonalDetails) {

		em = utilityService.dbConnector();
		List<EducationalDetails> educationalDetailsList = new ArrayList<>();
		for (EducationalDetails educationalDetails : fresherPersonalDetails.getEducationalDetails()) {

			String qualification = educationalDetails.getHighestQualification();
			String courses = educationalDetails.getCourses();
			String institution = educationalDetails.getInstitutionName();
			int passingYear = educationalDetails.getPassingYear();

			EducationalDetails educationalDto = new EducationalDetails(qualification, courses, institution, passingYear,
					fresherPersonalDetails.getPersonalDetailsId());
			educationalDetailsList.add(educationalDto);
			fresherPersonalDetails.setEducationalDetails(educationalDetailsList);
		}

		List<ProjectDetails> projectdetailsList = new ArrayList<>();
		for (ProjectDetails projectDetails : fresherPersonalDetails.getProjectDetails()) {

			String projectName = projectDetails.getProjectName();
			String projectSummary = projectDetails.getProjectSummary();
			String rolesAndResponsibility = projectDetails.getRolesAndResponsibility();
			List<TechnologyProjectDetails> technologyProjectList = new ArrayList<>();
			for (TechnologyProjectDetails technologyDetails : projectDetails.getTechnologyProjectDetails()) {

				String technologyUsed = technologyDetails.getTechnologyUsed();
				TechnologyProjectDetails technologyProjectDetails = new TechnologyProjectDetails(technologyUsed,
						projectDetails.getProjectDetailsId());
				technologyProjectList.add(technologyProjectDetails);
				projectDetails.setTechnologyProjectDetails(technologyProjectList);

			}
			ProjectDetails projectDetailsDto = new ProjectDetails(projectName, technologyProjectList, projectSummary,
					rolesAndResponsibility, fresherPersonalDetails.getPersonalDetailsId());
			projectdetailsList.add(projectDetailsDto);
			fresherPersonalDetails.setProjectDetails(projectdetailsList);

		}

		List<TechnicalDetails> technicalDetailsList = new ArrayList<>();
		for (TechnicalDetails technicalDetails : fresherPersonalDetails.getTechnicalDetails()) {

			String technicalSkills = technicalDetails.getTechnicalSkills();

			String tools = technicalDetails.getTools();
			int ratings = technicalDetails.getRatings();

			TechnicalDetails techDetailsDto = new TechnicalDetails(technicalSkills, tools, ratings,
					fresherPersonalDetails.getPersonalDetailsId());
			technicalDetailsList.add(techDetailsDto);
			fresherPersonalDetails.setTechnicalDetails(technicalDetailsList);

		}

		em.persist(fresherPersonalDetails);
		em.close();

		return fresherPersonalDetails;
	}

}
