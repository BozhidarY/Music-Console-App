package MusicConsoleApp.View;

import MusicConsoleApp.Controller.AdminController;


import java.util.Scanner;

public class AdminView {
    Scanner scanner = new Scanner(System.in);
    private AdminController adminController;

    public AdminView(AdminController adminController) {
        this.adminController = adminController;
    }

    public void openAdminCommunication() {
        System.out.println("You re logged in as an " + adminController.getAdmin().getUserType());
        System.out.println("Do you want to delete or recover account");
        String choice = scanner.nextLine();
        switch (choice) {
            case "Recover" -> {
                recoverUser();
            }
            case "Delete" -> {
                deleteUser();
            }
        }
    }

    public void recoverUser() {
        System.out.println("What account you want to recover");
        String accountName = scanner.nextLine();
        if (!adminController.recoverAccount(accountName)) {
            System.out.println("No user with that name found. Do you want to try again?");
            String choice = scanner.nextLine();
            if (choice.equals("Y")) {
                recoverUser();
            }
        } else {
            System.out.println("Success");
        }
    }

    public void deleteUser() {
        System.out.println("Enter account username you want to remove");
        String accountName = scanner.nextLine();
        if (!adminController.deleteAccount(accountName)) {
            System.out.println("No user with that name found. Do you want to try again?");
            String choice = scanner.nextLine();
            if (choice.equals("Y")) {
                deleteUser();
            }
        } else {
            System.out.println("Success");
        }
    }


}
