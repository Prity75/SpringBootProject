package com.project.mentorAllocation.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.mentorAllocation.dto.MentorDTO;
import com.project.mentorAllocation.dto.ProjectDTO;
import com.project.mentorAllocation.exception.ProjectAllocationException;
import com.project.mentorAllocation.service.ProjectAllocationService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@RestController
@Validated
@RequestMapping("/api")
public class ProjectAllocationAPI {
	@Autowired
	private ProjectAllocationService projectAllocationService;
	@Autowired
	private Environment environment;
	
	@PostMapping("/project")
	public ResponseEntity<String> allocateProject (@RequestBody @Valid ProjectDTO project) throws ProjectAllocationException{
		Integer projectId = projectAllocationService.allocateProject(project);
		String message=environment.getProperty("API.ALLOCATION_SUCCESS") + ":" + projectId;
		return new ResponseEntity<>(message,HttpStatus.CREATED);
	}
	
	@GetMapping("/mentor/{numberOfProjectsMentored}")
	public ResponseEntity<List<MentorDTO>> getMentors(@PathVariable Integer numberOfProjectsMentored) throws ProjectAllocationException{
		List<MentorDTO> mentors=projectAllocationService.getMentors(numberOfProjectsMentored);
		return new ResponseEntity<>(mentors, HttpStatus.OK);
	}
	@PutMapping("/project/{projectId}/{mentorId}")
	public ResponseEntity<String> updateProjectMentor(@PathVariable Integer projectId,
			        @Min(value=1000, message="{mentor.mentorid.invalid}")
	                @Max(value=9999, message="{mentor.mentorid.invalid}")
	                @PathVariable Integer mentorId) throws ProjectAllocationException
	{
		projectAllocationService.updateProjectMentor(projectId, mentorId);
		String message = environment.getProperty("API.PROJECT_UPDATE_SUCCESS");
		return new ResponseEntity<>(message, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/project/{projectId}")
	public ResponseEntity<String> deleteProject(@PathVariable Integer projectId) throws ProjectAllocationException{
		projectAllocationService.deleteProject(projectId);
		String message = environment.getProperty("API.PROJECT_DELETE_SUCCESS");
		return new ResponseEntity<>(message, HttpStatus.OK);
		
	}

}





//http://localhost:8765/api/project

//{
//    "ideaOwner":"10009",
//    "projectName":"Android Art App",
//    "releaseDate":"2018-09-27",
//    "mentorDTO":{"mentorId": 1009}
//}

// http://localhost:8765/api/mentor/3

// http://localhost:8765/api/project/13/1000

// http://localhost:8765/api/project/9
