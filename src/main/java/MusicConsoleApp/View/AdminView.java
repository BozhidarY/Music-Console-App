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
                recoverUser(userDB);
            }
            case "Delete" -> {
                deleteUser(userDB);
            }
        }
    }

    public void recoverUser(UserDB userDB){
        System.out.println("What account you want to recover");
        String accountName = scanner.nextLine();
        if(!adminController.recoverAccount(accountName, userDB )){
            System.out.println("No user with that name found. Do you want to try again?");
            String choice = scanner.nextLine();
            if(choice.equals("Y")){
                recoverUser(userDB);
            }
        }
        else{
            System.out.println("Success");
        }
    }
    public void deleteUser(UserDB userDB){
        System.out.println("Enter account username you want to remove");
        String accountName = scanner.nextLine();
        if(!adminController.deleteAccount(accountName, userDB)){
            System.out.println("No user with that name found. Do you want to try again?");
            String choice = scanner.nextLine();
            if(choice.equals("Y")){
                deleteUser(userDB);
            }
        }
        else {
            System.out.println("Success");
        }
    }


}
