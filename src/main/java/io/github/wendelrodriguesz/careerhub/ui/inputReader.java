package io.github.wendelrodriguesz.careerhub.ui;

import java.util.Scanner;

public class inputReader {
    private String label;
    private Scanner scanner;

    public inputReader(String label, Scanner scanner){
        this.label = label;
        this.scanner = scanner;
    }

    public String readInput() {
        System.out.print("Digite seu " + this.label + ": ");
        return this.scanner.nextLine();
    }
}
