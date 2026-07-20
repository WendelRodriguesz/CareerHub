package io.github.wendelrodriguesz.careerhub.app;

import io.github.wendelrodriguesz.careerhub.controller.ProfileController;
import io.github.wendelrodriguesz.careerhub.controller.ProjectController;
import io.github.wendelrodriguesz.careerhub.service.ProfileService;
import io.github.wendelrodriguesz.careerhub.service.ProjectService;
import io.github.wendelrodriguesz.careerhub.ui.ConsoleUI;

import java.util.Scanner;

public class CareerHubApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ProfileService profileService = new ProfileService();
        ProfileController profileController = new ProfileController(profileService);
        ProjectService projectService = new ProjectService();
        ProjectController projectController = new ProjectController((projectService));

        ConsoleUI consoleUI = new ConsoleUI(scanner, profileController, projectController);

        consoleUI.start();

        scanner.close();
    }
}
