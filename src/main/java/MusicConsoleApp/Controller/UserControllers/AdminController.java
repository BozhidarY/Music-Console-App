package MusicConsoleApp.Controller.UserControllers;

import MusicConsoleApp.Controller.UserDB;
import MusicConsoleApp.Models.Admin;
import MusicConsoleApp.View.AdminView;

import java.util.Scanner;

public class AdminController {
    private Admin admin;
    private AdminView adminView;
    Scanner scanner = new Scanner(System.in);

    public AdminController(Admin admin, AdminView adminView){
        this.admin = admin;
        this.adminView = adminView;
    }

    public void openAdminCommunication(UserDB userDB) {
        System.out.println("Do you want to delete or recover account");
        String choice = scanner.nextLine();
        switch (choice) {
            case "Recover" -> adminView.recoverAccount(admin, userDB);
            case "Delete" -> adminView.deleteAccount(admin, userDB);
        }
    }
}
