package MusicConsoleApp.Controller.FileHandling;

import MusicConsoleApp.Controller.UserDB;
import MusicConsoleApp.Models.Users;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Scanner;


public class LoadSaveUsersToJson {
    Scanner scanner = new Scanner(System.in);
    String fileNameError;
    private static final Logger logger = (Logger) LogManager.getLogger(LoadSaveUsersToJson.class);
    Gson gson = new GsonBuilder()
            .registerTypeAdapter(Users.class, new UserDeserializer())
            .setPrettyPrinting()
            .create();

    public void saveUsers(String filePath, UserDB userDB) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            gson.toJson(userDB, fileWriter);
            logger.info("Users have beed succesfully saved in {}", filePath);
        } catch (IOException e) {
            logger.error("Failed to save in file {}", filePath);
            saveUsers(fileNameError, userDB);
        }
    }

    public UserDB loadUsers(String filePath) {
        try (FileReader fileReader = new FileReader(filePath)) {
            Type typeToken = new TypeToken<UserDB>() {}.getType();
            logger.info("Users have beed loaded from {}", filePath);
            return gson.fromJson(fileReader, typeToken);
        } catch (IOException e){
            System.out.println(e.getMessage());
            logger.error("Failed to load from file {}", filePath);
            fileNameError = scanner.nextLine();
            UserDB newUserDB;
            newUserDB = loadUsers(fileNameError);
            return newUserDB;
        }
    }

}
