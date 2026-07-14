package io.github.wendelrodriguesz.careerhub.service;

import io.github.wendelrodriguesz.careerhub.model.Profile;

public class ProfileService {
    private Profile profile;
    public void managerProfile(){
        System.out.println("Ainda não implementado");
    }

    public void createProfile(String name, String professionalTitle, String summary, String email, String city){
        if (profile != null) {
            System.out.println("Já existe um perfil cadastrado.");
            return;
        }
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
            System.out.println("Nenhum perfil cadastrado para atualizar.");
            return;
        } if (!(name.isEmpty())) {
            this.profile.setName(name);
        } if (!(professionalTitle.isEmpty())) {
            this.profile.setProfessionalTitle(professionalTitle);
        } if (!(summary.isEmpty())) {
            this.profile.setSummary(summary);
        } if (!(email.isEmpty())) {
            this.profile.setEmail(email);
        } if (!(city.isEmpty())) {
            this.profile.setCity(city);
        }

//        return this.profile;
    }

    public void deleteProfile() {
        if (profile == null) {
            System.out.println("Nenhum perfil cadastrado para apagar.");
            return;
        }
        this.profile = null;
        System.out.println("Perfil apagado com sucesso.");
    }

    public String getPerfil() {
        if (profile == null) {
            return "Nenhum perfil cadastrado.";
        }
        return ("\n" +
                "\n---------- PERFIL ----------" +
                "\nNome: " + this.profile.getName() +
                "\nTítulo: " + this.profile.getProfessionalTitle() +
                "\nResumo: " + this.profile.getSummary() +
                "\nE-mail: " + this.profile.getEmail() +
                "\nCidade: " + this.profile.getCity());
    }
}
