package com.tyss.employeemanagement.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tyss.employeemanagement.dto.EducationalDetails;
import com.tyss.employeemanagement.dto.ExpereincePersonalDetails;
import com.tyss.employeemanagement.dto.ExperienceDetails;
import com.tyss.employeemanagement.dto.ProjectDetails;
import com.tyss.employeemanagement.dto.TechnicalDetails;
import com.tyss.employeemanagement.dto.TechnologyProjectDetails;
import com.tyss.employeemanagement.utility.UtilityService;

@Repository
public class ExperienceDetailsDaoImpl implements ExperienceDetailsDao {

	@Autowired
	UtilityService utilityService;

	@PersistenceContext
	EntityManager em;

//	@SuppressWarnings("unused")
	@Transactional
	@Override
	public ExpereincePersonalDetails experienceEmpdetails(ExpereincePersonalDetails experiencePersonalDetails) {
		em = utilityService.dbConnector();
		List<EducationalDetails> educationalDetailsList = new ArrayList<>();
		for (EducationalDetails educationalDetails : experiencePersonalDetails.getEducationalDetails()) {

			String qualification = educationalDetails.getHighestQualification();
			String courses = educationalDetails.getCourses();
			String institution = educationalDetails.getInstitutionName();
			int passingYear = educationalDetails.getPassingYear();

			EducationalDetails educationalDto = new EducationalDetails(qualification, courses, institution, passingYear,
					experiencePersonalDetails.getPersonalDetailsId());
			educationalDetailsList.add(educationalDto);
			experiencePersonalDetails.setEducationalDetails(educationalDetailsList);
		}

		List<ExperienceDetails> experienceDetailsList = new ArrayList<>();
		for (ExperienceDetails experienceDetails : experiencePersonalDetails.getExperienceDetails()) {
			int experienceDetailsId = experienceDetails.getExperienceDetailsId();

			String designation = experienceDetails.getDesignation();
			String company = experienceDetails.getCompany();
			DateFormat start = new SimpleDateFormat("MM/YYYY");
			DateFormat end = new SimpleDateFormat("dd/MM/YYYY");

			Date startDate = null;
			Date presentDate = null;

			try {
				startDate = start.parse(start.format(experienceDetails.getStartDate()));
				presentDate = end.parse(end.format(experienceDetails.getPresentDate()));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			String City = experienceDetails.getCity();
			int durationPeriod = experienceDetails.getDurationNoticePeriod();

			ExperienceDetails experienceDetailsDto = new ExperienceDetails(experienceDetailsId, designation, company,
					startDate, presentDate, City, durationPeriod);
			experiencePersonalDetails.setExperienceDetails(experienceDetailsList);
			experienceDetailsList.add(experienceDetailsDto);
			experiencePersonalDetails.setExperienceDetails(experienceDetailsList);

		}

		List<ProjectDetails> projectdetailsList = new ArrayList<>();
		for (ProjectDetails projectDetails : experiencePersonalDetails.getProjectDetails()) {

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
					rolesAndResponsibility, experiencePersonalDetails.getPersonalDetailsId());
			projectdetailsList.add(projectDetailsDto);
			experiencePersonalDetails.setProjectDetails(projectdetailsList);

		}

		List<TechnicalDetails> technicalDetailsList = new ArrayList<>();
		for (TechnicalDetails technicalDetails : experiencePersonalDetails.getTechnicalDetails()) {

			String technicalSkills = technicalDetails.getTechnicalSkills();

			String tools = technicalDetails.getTools();
			int ratings = technicalDetails.getRatings();

			TechnicalDetails techDetailsDto = new TechnicalDetails(technicalSkills, tools, ratings,
					experiencePersonalDetails.getPersonalDetailsId());
			technicalDetailsList.add(techDetailsDto);
			experiencePersonalDetails.setTechnicalDetails(technicalDetailsList);

		}

		em.persist(experiencePersonalDetails);

		em.close();
		return experiencePersonalDetails;
	}

}
