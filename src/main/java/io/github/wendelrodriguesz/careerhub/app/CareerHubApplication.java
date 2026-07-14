package io.github.wendelrodriguesz.careerhub.app;

import io.github.wendelrodriguesz.careerhub.controller.ProfileController;
import io.github.wendelrodriguesz.careerhub.service.ProfileService;
import io.github.wendelrodriguesz.careerhub.ui.ConsoleUI;

import java.util.Scanner;

public class CareerHubApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ProfileService profileService = new ProfileService();
        ProfileController profileController = new ProfileController(profileService);

        ConsoleUI consoleUI = new ConsoleUI(scanner, profileController);

        consoleUI.start();

        scanner.close();
    }
}
