package io.github.wendelrodriguesz.careerhub.ui;

import java.util.Scanner;

public class InputReader {
    private final Scanner scanner;

    public InputReader(Scanner scanner){
        this.scanner = scanner;
    }

    public String readInput(String label) {
        System.out.print(label + " ");
        return this.scanner.nextLine();
    }

    public long readLongId() {
        String input = this.readInput(
                "Digite o ID do projeto:"
        );

        try {
            return Long.parseLong(input);

        } catch (NumberFormatException exception) {
            throw new NumberFormatException(
                    "Digite um ID numérico válido."
            );
        }
    }
}
