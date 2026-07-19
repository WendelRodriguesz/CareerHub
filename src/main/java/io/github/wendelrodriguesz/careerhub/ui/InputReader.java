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
}
