package io.github.wendelrodriguesz.careerhub.service;

import io.github.wendelrodriguesz.careerhub.model.Profile;

public class ProfileService {
    private Profile profile;
    public void gerenciarPerfil(){
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
