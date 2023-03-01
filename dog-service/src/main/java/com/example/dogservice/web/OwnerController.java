package com.example.dogservice.web;

import com.example.dogservice.service.NoSuchDogOwnerException;
import com.example.dogservice.service.OwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Spring MVC controller for {@literal /owners}.
 */
@RestController
public class OwnerController {

	private final OwnerService ownerService;

	public OwnerController(OwnerService dogService) {
		this.ownerService = dogService;
	}

	@GetMapping("/owners/{name}/dogs")
	List<String> dog(@PathVariable String name) {
		return this.ownerService.getOwnedDogNames(name);
	}

	@ExceptionHandler
	ProblemDetail onNoSuchDogOwner(NoSuchDogOwnerException ex) {
		ProblemDetail details = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
		details.setProperty("owner", ex.getName());
		return details;
	}

}
