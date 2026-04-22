package com.example.dogs.loadgen;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.Duration;

import static io.gatling.http.HeaderValues.ApplicationJson;
import static io.gatling.javaapi.core.CoreDsl.constantUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;

public class DogsSimulation extends Simulation {

	final Duration duration = Duration.ofMinutes(2);

	final int usersPerSec = 5;

	final HttpProtocolBuilder httpProtocol = http.baseUrl("http://localhost:8081")
		// .basicAuth("user", "password")
		.contentTypeHeader(ApplicationJson())
		.acceptHeader(ApplicationJson());

	final ChainBuilder dogsByOwner = exec(
		http("Dogs by Owner").get(session -> "/owners/%s/dogs".formatted(generateName()))
	);

	String generateName() {
		double random = Math.random();
		if (random < 0.45) {
			return "Tommy";
		} else if (random < 0.9) {
			return "Jonatan";
		}

		return "Qwerty";
	}

	{
		setUp(
			scenario("Dogs by Owner")
				.exec(dogsByOwner)
				.injectOpen(constantUsersPerSec(usersPerSec).during(duration))
		).protocols(httpProtocol);
	}

}
