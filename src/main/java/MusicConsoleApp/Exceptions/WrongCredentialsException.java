package MusicConsoleApp.Exceptions;

public class WrongCredentialsException extends Exception{
    public String getMessage(){
        return "Wrong credentials";
    }
}
