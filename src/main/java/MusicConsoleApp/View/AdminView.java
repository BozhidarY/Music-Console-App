package MusicConsoleApp.View;

import MusicConsoleApp.Controller.FileHandling.LoadSaveUsersToJson;
import MusicConsoleApp.Controller.UserControllers.AdminController;
import MusicConsoleApp.Controller.UserDB;
import MusicConsoleApp.Models.Admin;
import MusicConsoleApp.Models.Constants;
import MusicConsoleApp.Models.Users;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminView {
    Scanner scanner = new Scanner(System.in);
    LoadSaveUsersToJson loadSaveUsersToJson = new LoadSaveUsersToJson();
    private AdminController adminController;

    public AdminView(AdminController adminController){
        this.adminController = adminController;
    }

    public void openAdminCommunication(UserDB userDB) {
        System.out.println("You re logged in as an " + adminController.getAdmin().getUserType());
        System.out.println("Do you want to delete or recover account");
        String choice = scanner.nextLine();
        switch (choice) {
            case "Recover" -> {
                System.out.println("What account you want to recover");
                String accountName = scanner.nextLine();
                adminController.recoverAccount(userDB, accountName);
            }
            case "Delete" -> {
                System.out.println("Enter account username you want to remove");
                String accountName = scanner.nextLine();
                adminController.deleteAccount(userDB, accountName);
            }
        }
    }


}
