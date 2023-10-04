package MusicConsoleApp.Controller.UserControllers;

import MusicConsoleApp.Controller.FileHandling.LoadSaveUsersToJson;
import MusicConsoleApp.Controller.UserDB;
import MusicConsoleApp.Models.Admin;
import MusicConsoleApp.Models.Constants;
import MusicConsoleApp.Models.Users;
import MusicConsoleApp.View.AdminView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminController {
    private Admin admin;
    LoadSaveUsersToJson loadSaveUsersToJson = new LoadSaveUsersToJson();

    public AdminController(Admin admin){
        this.admin = admin;
    }


    public boolean dublicationCheck(String userName, UserDB userDB){
        for(Users user: userDB.getUsersList()){
            if(user.getUsername().equals(userName)){
                return false;
            }
        }
        return true;
    }

    public boolean deleteAccount(String userName, UserDB userDB) {
        UserDB userDB1 = loadSaveUsersToJson.loadUsers(Constants.DELETEDUSERS_JSON_PATH);
        List<Users> removedAccounts = new ArrayList<>();
        for(Users user: userDB.getUsersList()){
            if(user.getUsername().equals(userName)){
                if(!dublicationCheck(userName, userDB1)){
                    return false;
                }
                else{
                    userDB1.getUsersList().add(user);
                    removedAccounts.add(user);
                }
            }
        }
        if(removedAccounts.isEmpty()){
            return false;
        }
        else{
            userDB.getUsersList().removeAll(removedAccounts);
            loadSaveUsersToJson.saveUsers(Constants.DELETEDUSERS_JSON_PATH, userDB1);
            return true;
        }

    }

    public boolean recoverAccount(String nameAcc, UserDB userDB) {
        UserDB userDB1 = loadSaveUsersToJson.loadUsers(Constants.DELETEDUSERS_JSON_PATH);
        for (Users user : userDB1.getUsersList()) {
            if (nameAcc.equals(user.getUsername()) && dublicationCheck(nameAcc, userDB)) {
                userDB.getUsersList().add(user);
                userDB1.getUsersList().remove(user);
                loadSaveUsersToJson.saveUsers(Constants.DELETEDUSERS_JSON_PATH, userDB1);
                return true;
            }
        }
        return false;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
