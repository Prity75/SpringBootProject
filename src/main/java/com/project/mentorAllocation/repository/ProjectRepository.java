package com.project.mentorAllocation.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.mentorAllocation.entity.Project;

public interface ProjectRepository extends CrudRepository<Project, Integer> {

}
