package com.project.mentorAllocation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Mentor {
	@Id
   private Integer MentorId;
   private String mentorName;
   @Column(name="projects_mentored")
   private Integer numberOfProjectsMentored;
}
