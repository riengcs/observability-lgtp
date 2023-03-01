package com.example.dogclient.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface Api {

	@GetExchange("/dogs")
	DogsResponse dogs(@RequestParam(name = "aregood") boolean areGood);

	@GetExchange("/owners/{name}/dogs")
	List<String> ownedDogs(@PathVariable String name);

}
