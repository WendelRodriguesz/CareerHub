package io.github.wendelrodriguesz.careerhub.service;

import io.github.wendelrodriguesz.careerhub.exceptions.InvalidProjectDataException;
import io.github.wendelrodriguesz.careerhub.exceptions.ProjectNotFoundException;
import io.github.wendelrodriguesz.careerhub.model.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Testes do serviço de projetos")
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
    void shouldCreateProject() {
        projectService.createProject(
                VALID_TITLE,
                VALID_DESCRIPTION,
                VALID_REPOSITORY_URL
        );

        List<Project> projects = projectService.getProjects();

        assertEquals(1, projects.size());

        Project project = projects.getFirst();

        assertNotNull(project);
        assertEquals(1L, project.getId());
        assertEquals(VALID_TITLE, project.getTitle());
        assertEquals(
                VALID_DESCRIPTION,
                project.getDescription()
        );
        assertEquals(
                VALID_REPOSITORY_URL,
                project.getRepositoryUrl()
        );
    }

    @Test
    @DisplayName("Deve iniciar com uma lista de projetos vazia")
    void shouldStartWithEmptyProjectList() {
        List<Project> projects = projectService.getProjects();

        assertNotNull(projects);
        assertTrue(projects.isEmpty());
    }

    @Test
    @DisplayName("Deve listar todos os projetos cadastrados")
    void shouldListAllProjects() {
        projectService.createProject(
                "CareerHub",
                "Portfólio profissional",
                "https://github.com/user/careerhub"
        );

        projectService.createProject(
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
        projectService.createProject(
                "Projeto 1",
                "Descrição 1",
                "https://github.com/user/projeto-1"
        );

        projectService.createProject(
                "Projeto 2",
                "Descrição 2",
                "https://github.com/user/projeto-2"
        );

        projectService.createProject(
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
        projectService.createProject(
                VALID_TITLE,
                VALID_DESCRIPTION,
                VALID_REPOSITORY_URL
        );

        Project project = projectService.getProjectById(1L);

        assertNotNull(project);
        assertEquals(1L, project.getId());
        assertEquals(VALID_TITLE, project.getTitle());
        assertEquals(
                VALID_DESCRIPTION,
                project.getDescription()
        );
        assertEquals(
                VALID_REPOSITORY_URL,
                project.getRepositoryUrl()
        );
    }

    @Test
    @DisplayName("Deve lançar exceção quando o projeto não existir")
    void shouldThrowExceptionWhenProjectDoesNotExist() {
        ProjectNotFoundException exception = assertThrows(
                ProjectNotFoundException.class,
                () -> projectService.getProjectById(999L)
        );

        assertEquals(
                "Nenhum projeto encontrado com o ID 999.",
                exception.getMessage()
        );
    }

    @Test
    @DisplayName("Deve atualizar um projeto existente pelo ID")
    void shouldUpdateProjectById() {
        projectService.createProject(
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
    @DisplayName("Deve atualizar somente os campos informados")
    void shouldUpdateOnlyProvidedFields() {
        projectService.createProject(
                VALID_TITLE,
                VALID_DESCRIPTION,
                VALID_REPOSITORY_URL
        );

        projectService.updateProjectById(
                1L,
                "",
                "Nova descrição",
                ""
        );

        Project updatedProject =
                projectService.getProjectById(1L);

        assertEquals(
                VALID_TITLE,
                updatedProject.getTitle()
        );
        assertEquals(
                "Nova descrição",
                updatedProject.getDescription()
        );
        assertEquals(
                VALID_REPOSITORY_URL,
                updatedProject.getRepositoryUrl()
        );
    }

    @Test
    @DisplayName(
            "Deve lançar exceção ao atualizar um projeto inexistente"
    )
    void shouldThrowExceptionWhenUpdatingMissingProject() {
        ProjectNotFoundException exception = assertThrows(
                ProjectNotFoundException.class,
                () -> projectService.updateProjectById(
                        999L,
                        "Novo título",
                        "Nova descrição",
                        "https://github.com/user/inexistente"
                )
        );

        assertEquals(
                "Nenhum projeto encontrado com o ID 999.",
                exception.getMessage()
        );
    }

    @Test
    @DisplayName(
            "Deve lançar exceção quando nenhum dado for informado na atualização"
    )
    void shouldThrowExceptionWhenUpdatingWithoutData() {
        projectService.createProject(
                VALID_TITLE,
                VALID_DESCRIPTION,
                VALID_REPOSITORY_URL
        );

        InvalidProjectDataException exception = assertThrows(
                InvalidProjectDataException.class,
                () -> projectService.updateProjectById(
                        1L,
                        "",
                        "  ",
                        ""
                )
        );

        assertEquals(
                "Nenhum dado informado para atualizar.",
                exception.getMessage()
        );
    }

    @Test
    @DisplayName("Deve excluir um projeto existente pelo ID")
    void shouldDeleteProjectById() {
        projectService.createProject(
                VALID_TITLE,
                VALID_DESCRIPTION,
                VALID_REPOSITORY_URL
        );

        projectService.deleteProjectById(1L);

        assertTrue(projectService.getProjects().isEmpty());

        assertThrows(
                ProjectNotFoundException.class,
                () -> projectService.getProjectById(1L)
        );
    }

    @Test
    @DisplayName(
            "Deve lançar exceção ao excluir um projeto inexistente"
    )
    void shouldThrowExceptionWhenDeletingMissingProject() {
        ProjectNotFoundException exception = assertThrows(
                ProjectNotFoundException.class,
                () -> projectService.deleteProjectById(999L)
        );

        assertEquals(
                "Nenhum projeto encontrado com o ID 999.",
                exception.getMessage()
        );
    }

    @Test
    @DisplayName(
            "Não deve permitir alteração direta na lista de projetos"
    )
    void shouldReturnUnmodifiableProjectList() {
        projectService.createProject(
                VALID_TITLE,
                VALID_DESCRIPTION,
                VALID_REPOSITORY_URL
        );

        List<Project> projects =
                projectService.getProjects();

        assertThrows(
                UnsupportedOperationException.class,
                projects::clear
        );

        assertEquals(
                1,
                projectService.getProjects().size()
        );
    }

    @Test
    @DisplayName(
            "Deve lançar exceção quando o título estiver vazio"
    )
    void shouldRejectBlankTitle() {
        InvalidProjectDataException exception = assertThrows(
                InvalidProjectDataException.class,
                () -> projectService.createProject(
                        " ",
                        VALID_DESCRIPTION,
                        VALID_REPOSITORY_URL
                )
        );

        assertEquals(
                "Título é obrigatório.",
                exception.getMessage()
        );
    }

    @Test
    @DisplayName(
            "Deve lançar exceção quando a descrição estiver vazia"
    )
    void shouldRejectBlankDescription() {
        InvalidProjectDataException exception = assertThrows(
                InvalidProjectDataException.class,
                () -> projectService.createProject(
                        VALID_TITLE,
                        " ",
                        VALID_REPOSITORY_URL
                )
        );

        assertEquals(
                "Descrição é obrigatório.",
                exception.getMessage()
        );
    }

    @Test
    @DisplayName(
            "Deve lançar exceção quando a URL estiver vazia"
    )
    void shouldRejectBlankRepositoryUrl() {
        InvalidProjectDataException exception = assertThrows(
                InvalidProjectDataException.class,
                () -> projectService.createProject(
                        VALID_TITLE,
                        VALID_DESCRIPTION,
                        " "
                )
        );

        assertEquals(
                "URL do repositório é obrigatório.",
                exception.getMessage()
        );
    }
}