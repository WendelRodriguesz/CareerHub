package io.github.wendelrodriguesz.careerhub.service;

import io.github.wendelrodriguesz.careerhub.model.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProjectServiceTest {

    private static final String VALID_TITLE = "CareerHub";
    private static final String VALID_DESCRIPTION =
            "Sistema para gerenciamento de portfólio profissional.";
    private static final String VALID_REPOSITORY_URL =
            "https://github.com/WendelRodriguesz/careerhub";

    private ProjectService projectService;

    @BeforeEach
    void setUp() {
        projectService = new ProjectService();
    }

    @Test
    @DisplayName("Deve cadastrar um projeto")
    void shouldAddProject() {
        // Act
        projectService.addProject(
                VALID_TITLE,
                VALID_DESCRIPTION,
                VALID_REPOSITORY_URL
        );

        // Assert
        List<Project> projects = projectService.getProjects();

        assertEquals(1, projects.size());

        Project project = projects.getFirst();

        assertNotNull(project);
        assertEquals(1L, project.getId());
        assertEquals(VALID_TITLE, project.getTitle());
        assertEquals(VALID_DESCRIPTION, project.getDescription());
        assertEquals(
                VALID_REPOSITORY_URL,
                project.getRepositoryUrl()
        );
    }

    @Test
    @DisplayName("Deve iniciar a lista de projetos vazia")
    void shouldStartWithEmptyProjectList() {
        List<Project> projects = projectService.getProjects();

        assertNotNull(projects);
        assertTrue(projects.isEmpty());
    }

    @Test
    @DisplayName("Deve listar todos os projetos cadastrados")
    void shouldListAllProjects() {
        projectService.addProject(
                "CareerHub",
                "Portfólio profissional",
                "https://github.com/user/careerhub"
        );

        projectService.addProject(
                "GymOps",
                "Sistema para gerenciamento de academias",
                "https://github.com/user/gymops"
        );

        List<Project> projects = projectService.getProjects();

        assertEquals(2, projects.size());
        assertEquals("CareerHub", projects.get(0).getTitle());
        assertEquals("GymOps", projects.get(1).getTitle());
    }

    @Test
    @DisplayName("Deve gerar identificadores sequenciais")
    void shouldGenerateSequentialIds() {
        projectService.addProject(
                "Projeto 1",
                "Descrição 1",
                "https://github.com/user/projeto-1"
        );

        projectService.addProject(
                "Projeto 2",
                "Descrição 2",
                "https://github.com/user/projeto-2"
        );

        projectService.addProject(
                "Projeto 3",
                "Descrição 3",
                "https://github.com/user/projeto-3"
        );

        List<Project> projects = projectService.getProjects();

        assertEquals(1L, projects.get(0).getId());
        assertEquals(2L, projects.get(1).getId());
        assertEquals(3L, projects.get(2).getId());
    }

    @Test
    @DisplayName("Deve buscar um projeto existente pelo ID")
    void shouldGetProjectById() {
        projectService.addProject(
                VALID_TITLE,
                VALID_DESCRIPTION,
                VALID_REPOSITORY_URL
        );

        Project project = projectService.getProjectById(1L);

        assertNotNull(project);
        assertEquals(1L, project.getId());
        assertEquals(VALID_TITLE, project.getTitle());
        assertEquals(VALID_DESCRIPTION, project.getDescription());
        assertEquals(
                VALID_REPOSITORY_URL,
                project.getRepositoryUrl()
        );
    }

    @Test
    @DisplayName("Deve retornar null quando o projeto não existir")
    void shouldReturnNullWhenProjectDoesNotExist() {
        Project project = projectService.getProjectById(999L);

        assertNull(project);
    }

    @Test
    @DisplayName("Deve atualizar um projeto existente pelo ID")
    void shouldUpdateProjectById() {
        projectService.addProject(
                VALID_TITLE,
                VALID_DESCRIPTION,
                VALID_REPOSITORY_URL
        );

        projectService.updateProjectById(
                1L,
                "CareerHub atualizado",
                "Descrição atualizada",
                "https://github.com/user/careerhub-updated"
        );

        Project updatedProject =
                projectService.getProjectById(1L);

        assertNotNull(updatedProject);
        assertEquals(
                "CareerHub atualizado",
                updatedProject.getTitle()
        );
        assertEquals(
                "Descrição atualizada",
                updatedProject.getDescription()
        );
        assertEquals(
                "https://github.com/user/careerhub-updated",
                updatedProject.getRepositoryUrl()
        );
    }

    @Test
    @DisplayName(
            "Não deve alterar os projetos quando o ID de atualização não existir"
    )
    void shouldNotUpdateProjectsWhenIdDoesNotExist() {
        projectService.addProject(
                VALID_TITLE,
                VALID_DESCRIPTION,
                VALID_REPOSITORY_URL
        );

        projectService.updateProjectById(
                999L,
                "Título inexistente",
                "Descrição inexistente",
                "https://github.com/user/inexistente"
        );

        Project existingProject =
                projectService.getProjectById(1L);

        assertNotNull(existingProject);
        assertEquals(VALID_TITLE, existingProject.getTitle());
        assertEquals(
                VALID_DESCRIPTION,
                existingProject.getDescription()
        );
        assertEquals(
                VALID_REPOSITORY_URL,
                existingProject.getRepositoryUrl()
        );
    }

    @Test
    @DisplayName("Deve excluir um projeto existente pelo ID")
    void shouldDeleteProjectById() {
        projectService.addProject(
                VALID_TITLE,
                VALID_DESCRIPTION,
                VALID_REPOSITORY_URL
        );

        projectService.deleteProjectByID(1L);

        assertTrue(projectService.getProjects().isEmpty());
        assertNull(projectService.getProjectById(1L));
    }

    @Test
    @DisplayName(
            "Não deve excluir outros projetos quando o ID não existir"
    )
    void shouldNotDeleteProjectsWhenIdDoesNotExist() {
        projectService.addProject(
                VALID_TITLE,
                VALID_DESCRIPTION,
                VALID_REPOSITORY_URL
        );

        projectService.deleteProjectByID(999L);

        List<Project> projects = projectService.getProjects();

        assertEquals(1, projects.size());
        assertFalse(projects.isEmpty());
        assertEquals(VALID_TITLE, projects.getFirst().getTitle());
    }

    @Test
    @DisplayName(
            "Não deve permitir alterar a lista interna diretamente"
    )
    void shouldReturnCopyOfProjectList() {
        projectService.addProject(
                VALID_TITLE,
                VALID_DESCRIPTION,
                VALID_REPOSITORY_URL
        );

        List<Project> returnedProjects =
                projectService.getProjects();

        returnedProjects.clear();

        List<Project> internalProjects =
                projectService.getProjects();

        assertEquals(1, internalProjects.size());
        assertFalse(internalProjects.isEmpty());
    }
}