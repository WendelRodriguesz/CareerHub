package io.github.wendelrodriguesz.careerhub.ui;

import io.github.wendelrodriguesz.careerhub.controller.ProfileController;
import io.github.wendelrodriguesz.careerhub.controller.ProjectController;
import io.github.wendelrodriguesz.careerhub.exceptions.InvalidProfileDataException;
import io.github.wendelrodriguesz.careerhub.exceptions.ProfileAlreadyExistsException;
import io.github.wendelrodriguesz.careerhub.exceptions.ProfileNotFoundException;
import io.github.wendelrodriguesz.careerhub.model.Profile;

import java.util.Scanner;

public class ConsoleUI {
    private final Scanner scanner;
    private final ProfileController profileController;
    private final ProjectController projectController;
    private final InputReader inputReader;

    public ConsoleUI(Scanner scanner, ProfileController profileController, ProjectController projectController){
        this.scanner = scanner;
        this.profileController = profileController;
        this.projectController = projectController;
        this.inputReader = new InputReader(scanner);
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
                case "2" -> this.managerProjects();
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
            System.out.println("2. Visualizar dados do perfil.");
            System.out.println("3. Atualizar dados do perfil.");
            System.out.println("4. Apagar perfil.");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            String operacao = scanner.nextLine();

            switch (operacao) {
                case "1" -> this.createProfile();
                case "2" -> this.showProfile();
                case "3" -> this.updateProfile();
                case "4" -> this.deleteProfile();
                case "0" -> {
                    running = false;
                    System.out.println("Voltando ao menu principal...");
                    System.out.println("======================================");
                }
                default -> System.out.println("Opção inválida. Digite uma opção do menu.");
            }
        }
    }

    private void showProfile() {
        try {
            Profile profile = profileController.getProfile();

            System.out.println();
            System.out.println("---------- PERFIL ----------");
            System.out.println("Nome: " + profile.getName());
            System.out.println(
                    "Título: " + profile.getProfessionalTitle()
            );
            System.out.println("Resumo: " + profile.getSummary());
            System.out.println("E-mail: " + profile.getEmail());
            System.out.println("Cidade: " + profile.getCity());

        } catch (ProfileNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void createProfile() {
        String name = this.inputReader.readInput("Digite seu nome:");
        String professionalTitle = this.inputReader.readInput("Digite seu título profissional:");
        String summary = this.inputReader.readInput("Digite um resumo:");
        String email = this.inputReader.readInput("Digite seu e-mail:");
        String city = this.inputReader.readInput("Digite sua cidade:");

        try{
            profileController.createProfile(
                    name,
                    professionalTitle,
                    summary,
                    email,
                    city
            );

            System.out.println("Perfil cadastrado com sucesso.");
        } catch (ProfileAlreadyExistsException | InvalidProfileDataException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateProfile() {
        String name = this.inputReader.readInput("Digite seu nome:");
        String professionalTitle = this.inputReader.readInput("Digite seu título profissional:");
        String summary = this.inputReader.readInput("Digite um resumo:");
        String email = this.inputReader.readInput("Digite seu e-mail:");
        String city = this.inputReader.readInput("Digite sua cidade:");

        try{
            profileController.updateProfile(
                    name,
                    professionalTitle,
                    summary,
                    email,
                    city
            );
            System.out.println("Perfil atualizado com sucesso.");
        } catch (ProfileAlreadyExistsException | InvalidProfileDataException e){
            System.out.println(e.getMessage());
        }

    }

    private void deleteProfile(){
        String confirmation = this.inputReader.readInput("Tem certeza que deseja apagar o perfil? (s/n):");
        if (confirmation.equalsIgnoreCase("s")) {
            try{
                profileController.deleteProfile();
                System.out.println("Perfil deletado com sucesso!");
            } catch (ProfileNotFoundException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private  void managerProjects(){
        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("====== Gerenciamento de projetos ======");
            System.out.println("1. Cadastrar projeto.");
            System.out.println("2. Listar projetos.");
            System.out.println("3. Buscar projeto por ID");
            System.out.println("4. Atualizar dados do projeto por ID.");
            System.out.println("5. Apagar projeto por ID.");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            String operacao = scanner.nextLine();

            switch (operacao) {
                case "1" -> this.createProject();
                case "2" -> this.listProjects();
                case "3" -> this.showProjectById();
                case "4" -> this.updateProjectById();
                case "5" -> this.deleteProjectById();
                case "0" -> {
                    running = false;
                    System.out.println("Voltando ao menu principal...");
                    System.out.println("======================================");
                }
                default -> System.out.println("Opção inválida. Digite uma opção do menu.");
            }
        }
    }

    private void createProject(){
        String title = this.inputReader.readInput("Digite o título do projeto:");
        String description = this.inputReader.readInput("Digite a descrição do projeto:");
        String repositoryUrl = this.inputReader.readInput("Digite a URL do repositório:");

        try{
            this.projectController.createProject(
                    title,
                    description,
                    repositoryUrl
            );

            System.out.println("Projeto cadastrado com sucesso.");
        } catch (ProfileAlreadyExistsException | InvalidProfileDataException e) {
            System.out.println(e.getMessage());
        }
    }

    private void listProjects(){
        this.projectController.getProjects();
    }
    private void showProjectById(){
        Long id = Long.parseLong(this.inputReader.readInput("Digite o ID do projeto:"));
        this.projectController.getProjectById(id);
    }
    private void updateProjectById(){
        Long id = Long.parseLong(this.inputReader.readInput("Digite o ID do projeto:"));
        String title = this.inputReader.readInput("Digite o título do projeto:");
        String description = this.inputReader.readInput("Digite a descrição do projeto:");
        String repositoryUrl = this.inputReader.readInput("Digite a URL do repositório:");

        try{
            this.projectController.updateProjectById(
                    id,
                    title,
                    description,
                    repositoryUrl
            );

            System.out.println("Projeto atualizado com sucesso.");
        } catch (ProfileAlreadyExistsException | InvalidProfileDataException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteProjectById(){
        Long id = Long.parseLong(this.inputReader.readInput("Digite o ID do projeto:"));
        this.projectController.deleteProjectById(id);
        System.out.println("Projeto apagado com sucesso.");
    }
}
