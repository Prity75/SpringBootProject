package com.project.mentorAllocation.service;

import java.util.List;

import com.project.mentorAllocation.dto.MentorDTO;
import com.project.mentorAllocation.dto.ProjectDTO;
import com.project.mentorAllocation.exception.ProjectAllocationException;

public interface ProjectAllocationService {
     public Integer allocateProject(ProjectDTO projectDTO) throws ProjectAllocationException;
     public List<MentorDTO> getMentors(Integer numberOfProjectsMentored) throws ProjectAllocationException;
     public void updateProjectMentor(Integer projectId, Integer mentorId) throws ProjectAllocationException;
     public void deleteProject(Integer projectId) throws ProjectAllocationException;
}
