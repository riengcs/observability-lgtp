package com.example.dogclient.web;

import com.example.dogclient.api.Api;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.filter.ServerHttpObservationFilter;

import java.util.List;

/**
 * Spring MVC controller for {@literal /owners}.
 */
@RestController
public class OwnerController {

	private final static Logger logger = LoggerFactory.getLogger(OwnerController.class);

	private final Api api;

	public OwnerController(Api api) {
		this.api = api;
	}

	@GetMapping("/owners/{name}/dogs")
	List<String> dogs(@PathVariable String name) {
		return this.api.ownedDogs(name);
	}

	@ExceptionHandler
	ProblemDetail onApiError(HttpServletRequest request, RestClientResponseException ex) {
		logger.error("Ooops!", ex);
		ServerHttpObservationFilter.findObservationContext(request)
			.ifPresent(context -> context.setError(ex));
		ProblemDetail details = ProblemDetail.forStatus(ex.getStatusCode());
		details.setProperty("downstream", ex.getResponseBodyAs(ProblemDetail.class));
		return details;
	}

}
