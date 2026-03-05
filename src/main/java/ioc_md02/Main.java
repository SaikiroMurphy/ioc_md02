package ioc_md02;

import java.util.Scanner;

import ioc_md02.presentation.AdminView;

public class Main {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        AdminView.getInstance().showStartMenu(scanner);
        scanner.close();
    }
}
