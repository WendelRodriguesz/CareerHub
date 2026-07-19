package io.github.wendelrodriguesz.careerhub.controller;

import io.github.wendelrodriguesz.careerhub.service.ProjectService;

public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService){
        this.projectService = projectService;
    }

    public void createProject(String title, String description, String repositoryUrl){
        projectService.addProject(title, description, repositoryUrl);
    }

    public void getProjects(){
        projectService.getProjects();
    }

    public void getProjectById(Long id){
        projectService.showProjectById(id);
    }

    public void updateProjectById(Long id, String title, String description, String repositoryUrl){
        projectService.updateProjectById(id, title, description, repositoryUrl);

    }

    public void deleteProjectById(Long id){
        projectService.deleteProjectByID(id);
    }
}
