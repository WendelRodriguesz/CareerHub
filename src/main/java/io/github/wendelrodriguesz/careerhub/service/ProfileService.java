package io.github.wendelrodriguesz.careerhub.service;

import io.github.wendelrodriguesz.careerhub.exceptions.InvalidProfileDataException;
import io.github.wendelrodriguesz.careerhub.exceptions.ProfileAlreadyExistsException;
import io.github.wendelrodriguesz.careerhub.exceptions.ProfileNotFoundException;
import io.github.wendelrodriguesz.careerhub.model.Profile;

public class ProfileService {
    private Profile profile;

    public void createProfile(String name, String professionalTitle, String summary, String email, String city){
        if (profile != null) {
            throw new ProfileAlreadyExistsException("Já existe um perfil cadastrado.");
        }
        validateRequiredField(name, "Nome");
        validateRequiredField(professionalTitle, "Título profissional");
        validateRequiredField(summary, "Resumo");
        validateRequiredField(email, "E-mail");
        validateRequiredField(city, "Cidade");

        this.profile = new Profile(name,
                professionalTitle,
                summary,
                email,
                city
        );
//        return this.profile;
    }

    public void updateProfile(String name, String professionalTitle, String summary, String email, String city) {
        if (profile == null) {
            throw new ProfileNotFoundException("Nenhum perfil cadastrado para atualizar.");
        }else if(name.isBlank() && professionalTitle.isBlank() && summary.isBlank() && email.isBlank() && city.isBlank()){
            throw new InvalidProfileDataException("Nenhum dado informado para atualizar.");
        }
        if (!(name.isBlank())) {
            this.profile.setName(name);
        } if (!(professionalTitle.isBlank())) {
            this.profile.setProfessionalTitle(professionalTitle);
        } if (!(summary.isBlank())) {
            this.profile.setSummary(summary);
        } if (!(email.isBlank())) {
            this.profile.setEmail(email);
        } if (!(city.isBlank())) {
            this.profile.setCity(city);
        }

//        return this.profile;
    }

    public void deleteProfile() {
        if (profile == null) {
            throw new ProfileNotFoundException("Nenhum perfil cadastrado para apagar.");
        }
        this.profile = null;
    }

    public Profile getProfile() {
        if (profile == null) {
            throw new ProfileNotFoundException("Nenhum perfil cadastrado para visualizar.");
        }
        return profile;
    }

    private void validateRequiredField(
            String value,
            String fieldName
    ) {
        if (value == null || value.isBlank()) {
            throw new InvalidProfileDataException(
                    fieldName + " é obrigatório."
            );
        }
    }
}