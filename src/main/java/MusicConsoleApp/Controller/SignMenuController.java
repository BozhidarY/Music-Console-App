package MusicConsoleApp.Controller;


import MusicConsoleApp.DB.SongData;
import MusicConsoleApp.DB.UserDB;
import MusicConsoleApp.Exceptions.Validators;
import MusicConsoleApp.Models.Admin;
import MusicConsoleApp.Models.Users;
import at.favre.lib.crypto.bcrypt.BCrypt;

public class SignMenuController {
    Validators validators = new Validators();
    private UserDB userDB;
    private UserDB deletedUsers;
    private SongData songData;

    public SignMenuController(UserDB userDB, UserDB deletedUsers, SongData songData) {
        this.userDB = userDB;
        this.deletedUsers = deletedUsers;
        this.songData = songData;
    }

    public Users checkIfUserExists(String username, String password) {
        for (Users user : userDB.getUsersList()) {
            BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
            if (user.getUsername().equals(username) && result.verified) {
                return user;
            }
        }
        if (username.equals(Admin.getAdmin().getUsername()) && password.equals(Admin.getAdmin().getPassword())) {
            return Admin.getAdmin();
        }
        return null;
    }

    public boolean checkDublicateUser(String username) {
        for (Users user : userDB.getUsersList()) {
            if (user.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

    public boolean validateUserCredentials(String username, String password) {
        if (!validators.validateUsername(username) || !validators.validatePassword(password)) {
            return false;
        }
        return true;
    }

    public UserDB getUserDB() {
        return userDB;
    }

    public void setUserDB(UserDB userDB) {
        this.userDB = userDB;
    }

    public UserDB getDeletedUsers() {
        return deletedUsers;
    }

    public void setDeletedUsers(UserDB deletedUsers) {
        this.deletedUsers = deletedUsers;
    }

    public SongData getSongData() {
        return songData;
    }

    public void setSongData(SongData songData) {
        this.songData = songData;
    }
}
