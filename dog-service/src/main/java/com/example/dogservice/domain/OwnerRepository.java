package com.example.dogservice.domain;

import org.springframework.data.repository.ListCrudRepository;

public interface OwnerRepository extends ListCrudRepository<Owner, Long> {

	Owner findByNameIgnoringCase(String name);

}
