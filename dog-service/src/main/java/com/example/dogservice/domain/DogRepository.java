package com.example.dogservice.domain;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.List;

/**
 * Spring Data repository for {@link Dog} entities.
 */
public interface DogRepository extends ListCrudRepository<Dog, Long>, ListPagingAndSortingRepository<Dog, Long> {

	List<Dog> findByOwner(Owner owner);

}
