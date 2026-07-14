package io.github.wendelrodriguesz.careerhub.controller;

import io.github.wendelrodriguesz.careerhub.service.ProfileService;

public class ProfileController {
    private ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    public void managerProfile() {
        profileService.managerProfile();
    }

    public void createProfile(String name, String professionalTitle, String summary, String email, String city) {
        profileService.createProfile(name, professionalTitle, summary, email, city);
    }

    public void updateProfile(String name, String professionalTitle, String summary, String email, String city) {
        profileService.updateProfile(name, professionalTitle, summary, email, city);
    }

    public void deleteProfile(){
        profileService.deleteProfile();
    }

    public String getPerfil(){
        return profileService.getPerfil();
    }
}
