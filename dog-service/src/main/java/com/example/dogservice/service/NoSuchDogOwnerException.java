package com.example.dogservice.service;

public class NoSuchDogOwnerException extends RuntimeException {

	private final String name;

	NoSuchDogOwnerException(String name) {
		super("Owner not found: " + name);
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}
