package com.project.mentorAllocation.dto;

import java.time.LocalDate;

import com.project.mentorAllocation.entity.Mentor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {
	private Integer projectId;
	   @NotNull(message="{project.projectname.absent}")
	   private String projectName;
	   @NotNull(message="{project.ideaowner.absent}")
	   private Integer ideaOwner;
	   @NotNull(message="{project.releasedate.absent}")
	   private LocalDate releaseDate;
	   @NotNull(message="{project.mentor.absent}")
	   @Valid
	   private MentorDTO mentorDTO;
}
