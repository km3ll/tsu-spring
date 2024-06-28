package com.baeldung.ls.persistence.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.baeldung.ls.persistence.model.Project;

public interface IProjectRepository extends PagingAndSortingRepository<Project, Long> {

    Optional<Project> findByName(String name);

    List<Project> findByDateCreatedBetween(LocalDate start, LocalDate end);

}
