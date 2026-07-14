package io.github.wendelrodriguesz.careerhub.ui;

import io.github.wendelrodriguesz.careerhub.controller.ProfileController;
import io.github.wendelrodriguesz.careerhub.ui.inputReader;

import java.util.Scanner;

public class ConsoleUI {
    private final Scanner scanner;
    private final ProfileController profileController;

    public ConsoleUI(Scanner scanner, ProfileController profileController){
        this.scanner = scanner;
        this.profileController = profileController;
    }

    public void start (){
        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("====== Bem-vindo ao Career Hub ======");
            System.out.println("1. Gerenciar perfil");
            System.out.println("2. Gerenciar projetos");
            System.out.println("3. Gerenciar experiências");
            System.out.println("4. Gerenciar formação");
            System.out.println("5. Gerenciar habilidades");
            System.out.println("6. Gerenciar links profissionais");
            System.out.println("7. Gerar prévia do portfólio");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            String operacao = scanner.nextLine();

            switch (operacao) {
                case "1" -> this.managerProfile();
                case "2" -> System.out.println("Gerenciamento de projetos ainda não implementado.");
                case "3" -> System.out.println("Gerenciamento de experiências ainda não implementado.");
                case "4" -> System.out.println("Gerenciamento de formação ainda não implementado.");
                case "5" -> System.out.println("Gerenciamento de habilidades ainda não implementado.");
                case "6" -> System.out.println("Gerenciamento de links profissionais ainda não implementado.");
                case "7" -> System.out.println("Gerenciamento de prévia do portfólio ainda não implementado.");
                case "0" -> {
                    running = false;
                    System.out.println("Encerrando o sistema...");
                    System.out.println("Até logo!");
                    System.out.println("======================================");
                }
                default -> System.out.println("Opção inválida. Digite uma opção do menu.");
            }
        }
    }

    private  void managerProfile(){
        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("====== Gerenciamento de perfil ======");
            System.out.println("1. Cadastrar perfil.");
            System.out.println("2. Visualisar dados do perfil.");
            System.out.println("3. Atualizar dados do perfil.");
            System.out.println("4. Apagar perfil.");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            String operacao = scanner.nextLine();

            switch (operacao) {
                case "1" -> this.createProfile();
                case "2" -> System.out.println(this.profileController.getPerfil());
                case "3" -> this.updateProfile();
                case "4" -> this.deleteProfile();
                case "0" -> {
                    running = false;
                    System.out.println("Encerrando o sistema...");
                    System.out.println("Até logo!");
                    System.out.println("======================================");
                }
                default -> System.out.println("Opção inválida. Digite uma opção do menu.");
            }
        }
    }

    private void createProfile() {
        String name = new inputReader("Digite seu nome:", scanner).readInput();
        String professionalTitle = new inputReader("Digite seu título profissional:", scanner).readInput();
        String summary = new inputReader("Digite um resumo:", scanner).readInput();
        String email = new inputReader("Digite seu e-mail:", scanner).readInput();
        String city = new inputReader("Digite sua cidade:", scanner).readInput();

        profileController.createProfile(
                name,
                professionalTitle,
                summary,
                email,
                city
        );

        System.out.println("Perfil cadastrado com sucesso.");
    }

    private void updateProfile() {
        String name = new inputReader("Digite seu nome:", scanner).readInput();
        String professionalTitle = new inputReader("Digite seu título profissional:", scanner).readInput();
        String summary = new inputReader("Digite um resumo:", scanner).readInput();
        String email = new inputReader("Digite seu e-mail:", scanner).readInput();
        String city = new inputReader("Digite sua cidade:", scanner).readInput();

        profileController.updateProfile(
                name,
                professionalTitle,
                summary,
                email,
                city
        );

        System.out.println("Perfil atualizado com sucesso.");
    }

    private void deleteProfile(){
        String confirmation = new inputReader("Tem certeza que deseja apagar o perfil? (s/n):", scanner).readInput();
        if (confirmation.equalsIgnoreCase("s")) {
            profileController.deleteProfile();
        }
    }
}
