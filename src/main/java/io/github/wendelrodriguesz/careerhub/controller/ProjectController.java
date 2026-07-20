package io.github.wendelrodriguesz.careerhub.controller;

import io.github.wendelrodriguesz.careerhub.model.Project;
import io.github.wendelrodriguesz.careerhub.service.ProjectService;

import java.util.List;

public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    public void createProject(
            String title,
            String description,
            String repositoryUrl
    ) {
        projectService.createProject(
                title,
                description,
                repositoryUrl
        );
    }

    public List<Project> getProjects() {
        return projectService.getProjects();
    }

    public Project getProjectById(long id) {
        return projectService.getProjectById(id);
    }

    public void updateProjectById(
            long id,
            String title,
            String description,
            String repositoryUrl
    ) {
        projectService.updateProjectById(
                id,
                title,
                description,
                repositoryUrl
        );
    }

    public void deleteProjectById(long id) {
        projectService.deleteProjectById(id);
    }
}