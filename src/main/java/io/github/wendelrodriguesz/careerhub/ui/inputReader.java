package io.github.wendelrodriguesz.careerhub.ui;

import java.util.Scanner;

public class inputReader {
    private final String label;
    private final Scanner scanner;

    public inputReader(String label, Scanner scanner){
        this.label = label;
        this.scanner = scanner;
    }

    public String readInput() {
        System.out.print(this.label);
        return this.scanner.nextLine();
    }
}
