package com.example.dogservice.service;

import com.example.dogservice.domain.Dog;
import com.example.dogservice.domain.DogRepository;
import com.example.dogservice.domain.Owner;
import com.example.dogservice.domain.OwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {

	private final OwnerRepository ownerRepository;

	private final DogRepository dogRepository;

	public OwnerService(OwnerRepository ownerRepository, DogRepository dogRepository) {
		this.ownerRepository = ownerRepository;
		this.dogRepository = dogRepository;
	}

	public List<String> getOwnedDogNames(String ownerName) {
		Owner owner = this.ownerRepository.findByNameIgnoringCase(ownerName);
		if (owner == null) {
			throw new NoSuchDogOwnerException(ownerName);
		}
		return this.dogRepository.findByOwner(owner).stream().map(Dog::getName).toList();
	}

}
