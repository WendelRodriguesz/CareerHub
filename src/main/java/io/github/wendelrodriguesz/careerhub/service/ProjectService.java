package io.github.wendelrodriguesz.careerhub.service;

import io.github.wendelrodriguesz.careerhub.model.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectService {
    private final List<Project> projects = new ArrayList<>();
    private long nextId = 1;

    public List<Project> getProjects() {
            for (Project project : projects){
                showProjectById(project.getId());
            }
        return new ArrayList<>(projects);
    }

    public Project getProjectById(long id) {
        for (Project project : projects) {
            if (project.getId() == id) {
                return project;
            }
        }
        return null;
    }

    public void showProjectById(long id){
        Project project = getProjectById(id);
        if(project != null){
            System.out.println("============");
            System.out.println("ID: " + project.getId());
            System.out.println("Titulo: " + project.getTitle());
            System.out.println("Descrição: " + project.getDescription());
            System.out.println("URL do repositório: " + project.getRepositoryUrl());
        }
    }

    public void addProject(String title, String description, String repositoryUrl) {
        Project project = new Project(nextId++, title, description, repositoryUrl);
        projects.add(project);
    }

    public void updateProjectById(long id, String title, String description, String repositoryUrl) {
        Project project = getProjectById(id);
        if (project != null) {
            project.setTitle(title);
            project.setDescription(description);
            project.setRepositoryUrl(repositoryUrl);
        }
    }

    public void deleteProjectByID(long id){
        Project project = getProjectById(id);
        if (project != null) {
            projects.remove(project);
        }
    }
}
