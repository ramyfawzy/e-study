package com.home.estudy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.home.estudy.entity.Tutorial;
import com.home.estudy.service.StudentService;
import com.home.estudy.service.TutorialService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Tutorial", description = "the Tutorial API")
@RestController
@RequestMapping("/api")
public class TutorialController {

	@Autowired
	TutorialService tutorialService;

	@Autowired
	StudentService studentService;

	@Operation(summary = "Find Tutorials by title", description = "Tutorials search by %title%", operationId = "getAllTutorials")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
			@ApiResponse(responseCode = "204", description = "No tutorials found") })
	@GetMapping(name = "/tutorials", path = "/tutorials", produces = { "application/json", "application/xml" })
	public ResponseEntity<List<Tutorial>> getAllTutorials(
			@Parameter(description = "Title of the tutorial for search.", example = "Hyderabad", required = false) @RequestParam(required = false) String title) {
		try {
			List<Tutorial> tutorials = new ArrayList<Tutorial>();

			if (title == null)
				tutorialService.findAllTutorials().forEach(tutorials::add);
			else
				tutorialService.findByTitleContaining(title).forEach(tutorials::add);

			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Operation(summary = "Find Tutorial by id", description = "Tutorials search by %id%", operationId = "getTutorialById")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
			@ApiResponse(responseCode = "404", description = "Tutorial not found"),
			@ApiResponse(responseCode = "204", description = "No tutorials found") })
	@GetMapping(name = "/tutorials/{id}", path = "/tutorials/{id}", produces = { "application/json",
			"application/xml" })
	public ResponseEntity<Tutorial> getTutorialById(
			@Parameter(description = "Id of the tutorial for search.", example = "5", required = true) @PathVariable("id") long id) {
		Optional<Tutorial> tutorialData = tutorialService.findById(id);

		if (tutorialData.isPresent()) {
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Operation(summary = "Add a new Tutorial", description = "Creates new tutorial", operationId = "createTutorial")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Tutorial created", content = @Content(schema = @Schema(implementation = Tutorial.class))),
			@ApiResponse(responseCode = "400", description = "Invalid input"),
			@ApiResponse(responseCode = "500", description = "Tutorial creation failed, Internal Server Error"),
			@ApiResponse(responseCode = "409", description = "Tutorial already exists") })
	@PostMapping(name = "/tutorials", path = "/tutorials", produces = { "application/json", "application/xml" })
	public ResponseEntity<Tutorial> createTutorial(
			@Parameter(description = "Tutorial to add. Cannot null or empty.", required = true, schema = @Schema(implementation = Tutorial.class)) @Valid @RequestBody Tutorial tutorial) {
		try {
			Tutorial _tutorial = tutorialService
					.save(new Tutorial(tutorial.getDescription(), tutorial.isPublished(), tutorial.getTitle()));
			return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Operation(summary = "Update an existing tutorial", description = "Update an existing tutorial", operationId = "updateTutorial")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
			@ApiResponse(responseCode = "204", description = "Tutorial not found"),
			@ApiResponse(responseCode = "404", description = "No tutorials found"),
			@ApiResponse(responseCode = "405", description = "Validation exception") })
	@PutMapping(value = "/tutorials/{id}", name = "/tutorials/{id}", path = "/tutorials/{id}", consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<Tutorial> updateTutorial(
			@Parameter(description = "Id of the tutorial to be update. Cannot be empty.", required = true) @PathVariable("id") long id,
			@Parameter(description = "Tutorial to update. Cannot null or empty.", required = true, schema = @Schema(implementation = Tutorial.class)) @Valid @RequestBody Tutorial tutorial) {
		Optional<Tutorial> tutorialData = tutorialService.findById(id);

		if (tutorialData.isPresent()) {
			Tutorial _tutorial = tutorialData.get();
			_tutorial.setTitle(tutorial.getTitle());
			_tutorial.setDescription(tutorial.getDescription());
			_tutorial.setPublished(tutorial.isPublished());
			return new ResponseEntity<>(tutorialService.save(_tutorial), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Operation(summary = "Deletes a tutorial", description = "Delete an existing tutorial")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Tutorial not found"),
			@ApiResponse(responseCode = "204", description = "No tutorials found") })
	@DeleteMapping(value = "/tutorials/{id}", path = "/tutorials/{id}", name = "/tutorials/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(
			@Parameter(description = "Id of the tutorial to be deleted. Cannot be empty.", required = true) @PathVariable("id") long id) {
		try {
			tutorialService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Operation(summary = "Deletes all tutorials", description = "Deletes all tutorials")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "No tutorials found"),
			@ApiResponse(responseCode = "204", description = "No tutorials found") })
	@DeleteMapping(value = "/tutorials", path = "/tutorials", name = "/tutorials")
	public ResponseEntity<HttpStatus> deleteAllTutorials() {
		try {
			tutorialService.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Operation(summary = "Find Tutorial by published status", description = "Tutorials search by %published%", operationId = "getTutorialByPublishedStatus")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
			@ApiResponse(responseCode = "204", description = "No tutorials found") })
	@GetMapping(name = "/tutorials/published/{published}", path = "/tutorials/published/{published}", produces = {
			"application/json", "application/xml" })
	public ResponseEntity<List<Tutorial>> findByPublished(
			@Parameter(description = "Published status of the tutorials for search.", example = "false", required = true) @PathVariable("published") String published) {
		try {
			List<Tutorial> tutorials = tutorialService.findByPublished(Boolean.parseBoolean(published));

			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(name = "/tutorials/students/{id}", path = "/tutorials/students/{id}", produces = { "application/json",
			"application/xml" })
	public ResponseEntity<Tutorial> getTutorialByIdFetchStudents(
			@Parameter(description = "Id of the tutorial for search.", example = "5", required = true) @PathVariable("id") long id) {
		Optional<Tutorial> tutorialData = tutorialService.findByIdFetchStudents(id);

		if (tutorialData.isPresent()) {
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Operation(summary = "Add a student to an existing tutorial", description = "Add student to an existing tutorial", operationId = "addStudentToTutorial")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
			@ApiResponse(responseCode = "204", description = "Tutorial not found"),
			@ApiResponse(responseCode = "404", description = "No tutorials found"),
			@ApiResponse(responseCode = "405", description = "Validation exception") })
	@PutMapping(value = "/tutorials/{id}/student/{studentId}", name = "/tutorials/{id}/student/{studentId}", path = "/tutorials/{id}/student/{studentId}", produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Tutorial> addStudentToTutorial(
			@Parameter(description = "Id of the tutorial to add student to. Cannot null or empty.", required = true) @PathVariable("id") long id,
			@Parameter(description = "Id of the student to be added. Cannot null or empty.", required = true) @PathVariable("studentId") long studentId) {
		Optional<Tutorial> updatedTutorial = tutorialService.addStudentToTutorial(id, studentId);
		if (updatedTutorial.isEmpty())
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(updatedTutorial.get(), HttpStatus.OK);
	}

}