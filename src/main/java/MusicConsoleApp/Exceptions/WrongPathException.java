package MusicConsoleApp.Exceptions;

public class WrongPathException extends RuntimeException {
    public String getMessage(){
        return "Wrong File Path. Enter a new one";
    }
}
