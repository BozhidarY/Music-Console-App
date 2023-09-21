package MusicConsoleApp.Models;

import MusicConsoleApp.Exceptions.WrongCredentialsException;

//@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Users {

    private String username;
    private String password;
    private UserType userType;

    public Users(String username, String password) {
        this.username = username;
        this.password = password;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws WrongCredentialsException {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "Users{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                '}';
    }
}