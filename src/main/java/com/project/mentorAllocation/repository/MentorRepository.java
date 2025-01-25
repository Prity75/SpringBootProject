package com.project.mentorAllocation.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.project.mentorAllocation.entity.Mentor;

public interface MentorRepository extends CrudRepository <Mentor,Integer> {
      List<Mentor> findByNumberOfProjectsMentored(Integer projects);
      //this will fetch list of all mentors from database who has 2,3,1,0 i.e. projects number of projects mentoring
}
