package com.baeldung.ls.persistence.repository;

import com.baeldung.ls.persistence.model.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IProjectRepository extends PagingAndSortingRepository<Project, Long>, CrudRepository<Project, Long> {
}
