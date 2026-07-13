package io.github.wendelrodriguesz.careerhub.app;

import io.github.wendelrodriguesz.careerhub.model.Profile;

import java.util.Scanner;

public class CareerHubApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Profile profile = new Profile(
                "Wendel Rodrigues",
                "Desenvolvedor Full Stack",
                "Estudante de Engenharia de Software e desenvolvedor de aplicações web.",
                "wendeldev2010@gmail.com",
                "Quixadá, CE"
        );
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
                case "1" -> System.out.println(profile.getInfos());
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
                    System.out.print("2d");
                }
                default -> System.out.println("Opção inválida. Digite uma opção do menu.");
            }
        }
        scanner.close();
    }
}
