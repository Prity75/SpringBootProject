package com.project.mentorAllocation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mentorAllocation.dto.MentorDTO;
import com.project.mentorAllocation.dto.ProjectDTO;
import com.project.mentorAllocation.entity.Mentor;
import com.project.mentorAllocation.entity.Project;
import com.project.mentorAllocation.exception.ProjectAllocationException;
import com.project.mentorAllocation.repository.MentorRepository;
import com.project.mentorAllocation.repository.ProjectRepository;

import jakarta.transaction.Transactional;
@Service(value="projectService")
@Transactional
public class ProjectAllocationServiceImpl implements ProjectAllocationService{
    @Autowired
	private ProjectRepository projectRepository;
    @Autowired
    private MentorRepository mentorRepository;
	@Override
	public Integer allocateProject(ProjectDTO project) throws ProjectAllocationException {
		Optional<Mentor> optional=mentorRepository.findById(project.getMentorDTO().getMentorId());
		Mentor mentor = optional.orElseThrow(()->new ProjectAllocationException("Service.MENTOR_NOT_FOUND"));
		if(mentor.getNumberOfProjectsMentored() >= 3) {
			throw new ProjectAllocationException("Service.CANNOT_ALLOCATE_PROJECT");
		}
		Project projectEntity = new Project();
		projectEntity.setProjectId(project.getProjectId());
		projectEntity.setIdeaOwner(project.getIdeaOwner());
		projectEntity.setProjectName(project.getProjectName());
		projectEntity.setReleaseDate(project.getReleaseDate());
//		Mentor mentorDTO=projectDTO.getMentor();
//		Mentor mentor = new Mentor();
//		mentor.setMentorId(mentorDTO.getMentorId());

//		mentor.setMentorName(mentorDTO.getMentorName());

//		mentor.setNumberOfProjectsMentored(mentorDTO.getNumberOfProjectsMentored());
//		project.setMentor(mentor);
//		projectRepository.save(project);
//		return project.getProjectId();
		
		
		///****************************
		
//		Mentor mentor = new Mentor();
//		mentor.setMentorId(projectDTO.getMentor().getMentorId());
//		mentor.setMentorName(projectDTO.getMentor().getMentorName());
//		mentor.setNumberOfProjectsMentored(projectDTO.getMentor().getNumberOfProjectsMentored());
//		
//		project.setMentor(mentor);
//		projectRepository.save(project);
//		return project.getProjectId();
		
		projectEntity.setMentor(mentor);
		mentor.setNumberOfProjectsMentored(mentor.getNumberOfProjectsMentored()+1);
		Project savedProject = projectRepository.save(projectEntity);
		return savedProject.getProjectId();
	}
	@Override
	public List<MentorDTO> getMentors(Integer numberOfProjectsMentored) throws ProjectAllocationException {
		List<Mentor> mentors = mentorRepository.findByNumberOfProjectsMentored(numberOfProjectsMentored);
		if(mentors.isEmpty()) {
			throw new ProjectAllocationException("Service.MENTOR_NOT_FOUND");
		}
		List<MentorDTO> mentorDTOs=new ArrayList<>();
		for(Mentor mentor: mentors) {
			MentorDTO mentorDTO = new MentorDTO();
			mentorDTO.setMentorId(mentor.getMentorId());
			mentorDTO.setMentorName(mentor.getMentorName());
			mentorDTO.setNumberOfProjectsMentored(mentor.getNumberOfProjectsMentored());
			mentorDTOs.add(mentorDTO);
		}
		return mentorDTOs;
	}
	@Override
	public void updateProjectMentor(Integer projectId, Integer mentorId) throws ProjectAllocationException {
		Optional<Mentor> optional1 = mentorRepository.findById(mentorId);
		Mentor mentor = optional1.orElseThrow(()-> new ProjectAllocationException("Service.MENTOR_NOT_FOUND"));
		Optional<Project> optional2 = projectRepository.findById(projectId);
		Project project = optional2.orElseThrow(()->  new ProjectAllocationException("Service.PROJECT_NOT_FOUND"));
		project.setMentor(mentor);
		mentor.setNumberOfProjectsMentored(mentor.getNumberOfProjectsMentored()+1);
		
	}
	@Override
	public void deleteProject(Integer projectId) throws ProjectAllocationException {
		Optional<Project> optional = projectRepository.findById(projectId);
		Project project = optional.orElseThrow(()->new ProjectAllocationException("Service.PROJECT_NOT_FOUND"));
		if(project.getMentor()==null) {
			projectRepository.delete(project);
		}
		Mentor mentor=project.getMentor();
		mentor.setNumberOfProjectsMentored(mentor.getNumberOfProjectsMentored()-1);
		project.setIdeaOwner(null); // removes the association between the projectand the mentor
		projectRepository.delete(project);
	}

	

}
