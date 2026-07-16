package io.github.wendelrodriguesz.careerhub.service;

import io.github.wendelrodriguesz.careerhub.exceptions.InvalidProfileDataException;
import io.github.wendelrodriguesz.careerhub.exceptions.ProfileAlreadyExistsException;
import io.github.wendelrodriguesz.careerhub.exceptions.ProfileNotFoundException;
import io.github.wendelrodriguesz.careerhub.model.Profile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProfileServiceTest {
    private ProfileService profileService;

    // Executa sempre antes de cada teste
    @BeforeEach
    void setUp() {
        profileService = new ProfileService();
    }

    @Test
    @DisplayName("Deve criar um novo perfil")
    void shouldCreateProfile() {
        profileService.createProfile(
                "John Doe",
                "Software Engineer",
                "Experienced developer with a passion for creating innovative solutions.",
                "john.doe@example.com",
                "New York"
        );

        Profile profile = profileService.getProfile();

        assertNotNull(profile);

        assertEquals("John Doe", profile.getName());
        assertEquals("Software Engineer", profile.getProfessionalTitle());
        assertEquals("Experienced developer with a passion for creating innovative solutions.", profile.getSummary());
        assertEquals("john.doe@example.com", profile.getEmail());
        assertEquals("New York", profile.getCity());
    }

    // Divida técnica: Depois testar para cado campo.
    @Test
    @DisplayName("Deve lançar uma exceção ao tentar criar um perfil com algum campo vazio")
    void shouldThrowExceptionWhenCreatingProfileWithEmptyFields() {
        InvalidProfileDataException exception = assertThrows( InvalidProfileDataException.class,
                () -> profileService.createProfile(
                        "John Doe",
                        "Software Engineer",
                        "Experienced developer with a passion for creating innovative solutions.",
                        " ",
                        "Cidade Nova")
        );

        assertEquals("E-mail é obrigatório.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar uma exceção ao tentar criar um segundo perfil")
    void shouldThrowExceptionWhenCreatingSecondProfile() {
        profileService.createProfile(
                "John Doe",
                "Software Engineer",
                "Experienced developer with a passion for creating innovative solutions.",
                "john.doe@example.com",
                "New York"
        );

        ProfileAlreadyExistsException exception = assertThrows(
                ProfileAlreadyExistsException.class,
                () -> profileService.createProfile(
                        "Outro usuário",
                        "Desenvolvedor Java",
                        "Outro resumo",
                        "outro@email.com",
                        "Fortaleza, CE"
                )
        );

        assertEquals(
                "Já existe um perfil cadastrado.",
                exception.getMessage()
        );
    }

    @Test
    @DisplayName("Deve retornar o perfil existente")
    void shouldReturnExistingProfile(){
        profileService.createProfile(
            "John Doe",
            "Software Engineer",
            "Experienced developer with a passion for creating innovative solutions.",
            "john.doe@example.com",
            "New York"
        );

        Profile profile = profileService.getProfile();

        assertNotNull(profile);

        assertEquals("John Doe", profile.getName());
        assertEquals("Software Engineer", profile.getProfessionalTitle());
        assertEquals("Experienced developer with a passion for creating innovative solutions.", profile.getSummary());
        assertEquals("john.doe@example.com", profile.getEmail());
        assertEquals("New York", profile.getCity());
    }

    @Test
    @DisplayName("Deve lançar uma exceção quando o perfil não existir")
    void shouldThrowExceptionWhenProfileDoesNotExist() {
        ProfileNotFoundException exception = assertThrows(
            ProfileNotFoundException.class,
            () -> profileService.getProfile()
        );

        assertEquals("Nenhum perfil cadastrado para visualizar.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve atualizar um perfil existente")
    void shouldUpdateExistingProfile(){
        profileService.createProfile(
                "John Doe",
                "Software Engineer",
                "Experienced developer with a passion for creating innovative solutions.",
                "john.doe@example.com",
                "New York"
        );

        profileService.updateProfile(
                "Jane Doe",
                "Senior Software Engineer",
                "Updated summary.",
                "jane.doe@example.com",
                "San Francisco"
        );

        Profile profile = profileService.getProfile();

        assertNotNull(profile);

        assertEquals("Jane Doe", profile.getName());
        assertEquals("Senior Software Engineer", profile.getProfessionalTitle());
        assertEquals("Updated summary.", profile.getSummary());
        assertEquals("jane.doe@example.com", profile.getEmail());
        assertEquals("San Francisco", profile.getCity());
    }

    @Test
    @DisplayName("Deve atualizar apenas os campos válidos de um perfil existente")
    void shouldUpdateOnlyValidFieldsExistingProfile(){
        profileService.createProfile(
                "John Doe",
                "Software Engineer",
                "Experienced developer with a passion for creating innovative solutions.",
                "john.doe@example.com",
                "New York"
        );

        profileService.updateProfile(
                "",
                "",
                "Updated summary.",
                "",
                "San Francisco"
        );

        Profile profile = profileService.getProfile();

        assertNotNull(profile);

        assertEquals("John Doe", profile.getName());
        assertEquals("Software Engineer", profile.getProfessionalTitle());
        assertEquals("Updated summary.", profile.getSummary());
        assertEquals("john.doe@example.com", profile.getEmail());
        assertEquals("San Francisco", profile.getCity());
    }

    @Test
    @DisplayName("Deve lançar uma exceção ao tentar atualizar um perfil inexistente")
    void shouldThrowExceptionWhenUpdatingProfileDoesNotExist() {
        ProfileNotFoundException exception = assertThrows( ProfileNotFoundException.class,
                () -> profileService.updateProfile(
                        "Jane Doe",
                        "Senior Software Engineer",
                        "Updated summary.",
                        "teset@email.com",
                        "Cidade 1"
                    )
        );

        assertEquals("Nenhum perfil cadastrado para atualizar.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar uma exceção ao tentar atualizar um perfil com todos os campos vazios")
    void shouldThrowExceptionWhenUpdatingProfileWithEmptyFields() {
        profileService.createProfile(
                "John Doe",
                "Software Engineer",
                "Experienced developer with a passion for creating innovative solutions.",
                "john.doe@example.com",
                "New York"
        );

        Profile profile = profileService.getProfile();

        assertNotNull(profile);

        InvalidProfileDataException exception = assertThrows(InvalidProfileDataException.class,
                () ->   profileService.updateProfile("","","  ","","")
        );

        assertEquals("Nenhum dado informado para atualizar.", exception.getMessage());
    }

}