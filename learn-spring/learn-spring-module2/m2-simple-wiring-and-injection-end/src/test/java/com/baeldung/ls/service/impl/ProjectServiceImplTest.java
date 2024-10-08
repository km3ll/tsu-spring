package com.baeldung.ls.service.impl;

import com.baeldung.ls.persistence.model.Project;
import com.baeldung.ls.persistence.repository.impl.ProjectRepositoryImpl;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;

public class ProjectServiceImplTest {

    private ProjectServiceImpl projectServiceImpl = new ProjectServiceImpl(new ProjectRepositoryImpl());

    @Test
    public void givenNewProject_thenSavedSuccess() {
        Project newProject = new Project("First Project", LocalDate.now());

        assertNotNull(projectServiceImpl.save(newProject));
    }
}
