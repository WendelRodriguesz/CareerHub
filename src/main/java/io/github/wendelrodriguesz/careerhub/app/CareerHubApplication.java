package io.github.wendelrodriguesz.careerhub.app;

import io.github.wendelrodriguesz.careerhub.controller.ProfileController;
import io.github.wendelrodriguesz.careerhub.model.Profile;
import io.github.wendelrodriguesz.careerhub.service.ProfileService;
import io.github.wendelrodriguesz.careerhub.ui.ConsoleUI;

import java.util.Scanner;

public class CareerHubApplication {

    public static void main(String[] args) {
//        Profile profile = new Profile(
//                "Wendel Rodrigues",
//                "Desenvolvedor Full Stack",
//                "Estudante de Engenharia de Software e desenvolvedor de aplicações web.",
//                "wendeldev2010@gmail.com",
//                "Quixadá, CE"
//        );
        Scanner scanner = new Scanner(System.in);

        ProfileService profileService = new ProfileService();
        ProfileController profileController =
                new ProfileController(profileService);

        ConsoleUI consoleUI =
                new ConsoleUI(scanner, profileController);

        consoleUI.start();

        scanner.close();
    }
}
