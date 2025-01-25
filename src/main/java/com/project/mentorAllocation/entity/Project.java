package com.project.mentorAllocation.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Project {
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Integer projectId;
   private String projectName;
   private Integer ideaOwner;
   private LocalDate releaseDate;
   
   @ManyToOne(cascade = CascadeType.ALL)
   @JoinColumn(name="mentor_id")
   private Mentor mentor;
}
