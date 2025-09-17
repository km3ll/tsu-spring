package com.baeldung.ls.persistence.repository.impl;

import com.baeldung.ls.persistence.model.Project;
import com.baeldung.ls.persistence.repository.IProjectRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProjectRepositoryImplUnitTest {

    @Test
    void findById_givenDummyId_returnsEmpty() {
        assertFalse(new ProjectRepositoryImpl().findById(123L).isPresent());
    }

    @Test
    void findById_givenValidId_returnsNonEmpty() {

        Project project = new Project("Test Drive", LocalDate.now());
        IProjectRepository repository = new ProjectRepositoryImpl();
        repository.save(project);

        assertTrue(repository.findById(project.getId()).isPresent());

    }

}