package io.github.wendelrodriguesz.careerhub.service;

import io.github.wendelrodriguesz.careerhub.exceptions.InvalidProjectDataException;
import io.github.wendelrodriguesz.careerhub.exceptions.ProjectNotFoundException;
import io.github.wendelrodriguesz.careerhub.model.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectService {

    private final List<Project> projects = new ArrayList<>();
    private long nextId = 1;

    public void createProject(
            String title,
            String description,
            String repositoryUrl
    ) {
        validateRequiredField(title, "Título");
        validateRequiredField(description, "Descrição");
        validateRequiredField(repositoryUrl, "URL do repositório");

        Project project = new Project(
                nextId,
                title.trim(),
                description.trim(),
                repositoryUrl.trim()
        );

        projects.add(project);
        nextId++;
    }

    public List<Project> getProjects() {
        return List.copyOf(projects);
    }

    public Project getProjectById(long id) {
        for (Project project : projects) {
            if (project.getId() == id) {
                return project;
            }
        }

        throw new ProjectNotFoundException(
                "Nenhum projeto encontrado com o ID " + id + "."
        );
    }

    public void updateProjectById(
            long id,
            String title,
            String description,
            String repositoryUrl
    ) {
        Project project = getProjectById(id);

        if (
                isBlank(title)
                        && isBlank(description)
                        && isBlank(repositoryUrl)
        ) {
            throw new InvalidProjectDataException(
                    "Nenhum dado informado para atualizar."
            );
        }

        if (hasText(title)) {
            project.setTitle(title.trim());
        }

        if (hasText(description)) {
            project.setDescription(description.trim());
        }

        if (hasText(repositoryUrl)) {
            project.setRepositoryUrl(repositoryUrl.trim());
        }
    }

    public void deleteProjectById(long id) {
        Project project = getProjectById(id);
        projects.remove(project);
    }

    private void validateRequiredField(
            String value,
            String fieldName
    ) {
        if (isBlank(value)) {
            throw new InvalidProjectDataException(
                    fieldName + " é obrigatório."
            );
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

    private boolean hasText(String value) {
        return value != null && !value.isBlank();
    }
}