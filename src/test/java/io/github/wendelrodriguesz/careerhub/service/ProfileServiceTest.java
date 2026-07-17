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
    private static final String VALID_NAME = "John Doe";
    private static final String VALID_PROFESSIONAL_TITLE = "Software Engineer";
    private static final String VALID_SUMMARY = "Experienced developer with a passion for creating innovative solutions.";
    private static final String VALID_EMAIL = "john.doe@example.com";
    private static final String VALID_CITY = "New York";

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
                VALID_NAME,
                VALID_PROFESSIONAL_TITLE,
                VALID_SUMMARY,
                VALID_EMAIL,
                VALID_CITY
        );

        Profile profile = profileService.getProfile();

        assertNotNull(profile);

        assertEquals(VALID_NAME, profile.getName());
        assertEquals(VALID_PROFESSIONAL_TITLE, profile.getProfessionalTitle());
        assertEquals(VALID_SUMMARY, profile.getSummary());
        assertEquals(VALID_EMAIL, profile.getEmail());
        assertEquals("New York", profile.getCity());
    }

    // Divida técnica: Depois testar para cado campo.
    @Test
    @DisplayName("Deve lançar uma exceção ao tentar criar um perfil com algum campo vazio")
    void shouldThrowExceptionWhenCreatingProfileWithEmptyFields() {
        InvalidProfileDataException exception = assertThrows( InvalidProfileDataException.class,
                () -> profileService.createProfile(VALID_NAME, VALID_PROFESSIONAL_TITLE, VALID_SUMMARY, " ", VALID_CITY)
        );

        assertEquals("E-mail é obrigatório.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar uma exceção ao tentar criar um segundo perfil")
    void shouldThrowExceptionWhenCreatingSecondProfile() {
        profileService.createProfile(
                VALID_NAME,
                VALID_PROFESSIONAL_TITLE,
                VALID_SUMMARY,
                VALID_EMAIL,
                VALID_CITY
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
                VALID_NAME,
                VALID_PROFESSIONAL_TITLE,
                VALID_SUMMARY,
                VALID_EMAIL,
                VALID_CITY
        );

        Profile profile = profileService.getProfile();

        assertNotNull(profile);

        assertEquals(VALID_NAME, profile.getName());
        assertEquals(VALID_PROFESSIONAL_TITLE, profile.getProfessionalTitle());
        assertEquals(VALID_SUMMARY, profile.getSummary());
        assertEquals(VALID_EMAIL, profile.getEmail());
        assertEquals(VALID_CITY, profile.getCity());
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
                VALID_NAME,
                VALID_PROFESSIONAL_TITLE,
                VALID_SUMMARY,
                VALID_EMAIL,
                VALID_CITY
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
                VALID_NAME,
                VALID_PROFESSIONAL_TITLE,
                VALID_SUMMARY,
                VALID_EMAIL,
                VALID_CITY
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

        assertEquals(VALID_NAME, profile.getName());
        assertEquals(VALID_PROFESSIONAL_TITLE, profile.getProfessionalTitle());
        assertEquals("Updated summary.", profile.getSummary());
        assertEquals(VALID_EMAIL, profile.getEmail());
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
                VALID_NAME,
                VALID_PROFESSIONAL_TITLE,
                VALID_SUMMARY,
                VALID_EMAIL,
                VALID_CITY
        );

        Profile profile = profileService.getProfile();

        assertNotNull(profile);

        InvalidProfileDataException exception = assertThrows(InvalidProfileDataException.class,
                () ->   profileService.updateProfile("","","  ","","")
        );

        assertEquals("Nenhum dado informado para atualizar.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar uma exceção ao tentar excluir um perfil inexistente")
    void shouldThrowExceptionWhenDeletingExistingProfile(){
        ProfileNotFoundException exception = assertThrows(ProfileNotFoundException.class,
                () -> profileService.deleteProfile()
        );
        assertEquals("Nenhum perfil cadastrado para apagar.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve excluir um perfil existente")
    void shouldDeleteExistingProfile(){
        profileService.createProfile(
                VALID_NAME,
                VALID_PROFESSIONAL_TITLE,
                VALID_SUMMARY,
                VALID_EMAIL,
                VALID_CITY
        );

        Profile profile = profileService.getProfile();

        assertNotNull(profile);

        profileService.deleteProfile();

        ProfileNotFoundException exception = assertThrows(ProfileNotFoundException.class,
                () -> profileService.deleteProfile()
        );
        assertEquals("Nenhum perfil cadastrado para apagar.", exception.getMessage());
    }
}