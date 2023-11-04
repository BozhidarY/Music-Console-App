package MusicConsoleApp.View;

import MusicConsoleApp.Controller.AdminController;


import java.util.Scanner;

public class AdminView {
    Scanner scanner = new Scanner(System.in);
    public AdminController adminController;

    public AdminView(AdminController adminController) {
        this.adminController = adminController;
    }

    public void openAdminCommunicationMenu() {
        System.out.println("You re logged in as an " + adminController.getAdmin().getUserType());
        System.out.println("Do you want to delete or recover account(Recover/Delete)");
        String choice = scanner.nextLine();
        switch (choice) {
            case "Recover" -> {
                recoverUserAccountDialog();
            }
            case "Delete" -> {
                deleteUserAccountDialog();
            }
        }
    }

    public void recoverUserAccountDialog() {
        System.out.println("What account you want to recover");
        String accountName = scanner.nextLine();
        if (!adminController.recoverUserAccount(accountName)) {
            System.out.println("No user with that name found. Do you want to try again?");
            String choice = scanner.nextLine();
            if (choice.equals("Y")) {
                recoverUserAccountDialog();
            }
        } else {
            System.out.println("Success");
        }
    }

    public void deleteUserAccountDialog() {
        System.out.println("Enter account username you want to remove");
        String accountName = scanner.nextLine();
        if (!adminController.deleteUserAccount(accountName)) {
            System.out.println("No user with that name found. Do you want to try again?");
            String choice = scanner.nextLine();
            if (choice.equals("Y")) {
                deleteUserAccountDialog();
            }
        } else {
            System.out.println("Success");
        }
    }


}
