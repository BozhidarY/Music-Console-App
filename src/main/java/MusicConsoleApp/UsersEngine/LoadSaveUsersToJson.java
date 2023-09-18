package MusicConsoleApp.UsersEngine;

import MusicConsoleApp.Users.UserDB;
import MusicConsoleApp.Users.Users;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;


public class LoadSaveUsersToJson {
    Gson gson = new GsonBuilder()
            .registerTypeAdapter(Users.class, new UserDeserializer())
            .setPrettyPrinting()
            .create();
//    ObjectMapper objectMapper = new ObjectMapper();
//
//     public void saveUsers(String fileName, UserDB userDB){
//         try{
//
//             objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
//             ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
//
//             String json = objectWriter.writeValueAsString(userDB.getUsersList());
//
//             FileWriter fileWriter = new FileWriter(fileName);
//             fileWriter.write(json);
//             fileWriter.close();
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }
//     public void loadUsers(String fileName, UserDB userDB){
//         try {
//             File file = new File(fileName);
//             if (file.exists()) {
//                 List<Users> existingUsers = objectMapper.readValue(file, new TypeReference<List<Users>>() {});
//
//                 existingUsers.addAll(userDB.getUsersList());
//
//                 objectMapper.writeValue(file, existingUsers);
//
//             } else {
//                 System.out.println("Error");
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }


    public void saveUsers(String fileName, UserDB userDB) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            gson.toJson(userDB, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public UserDB loadUsers(String fileName) {
        try (FileReader fileReader = new FileReader(fileName)) {
            Type typeToken = new TypeToken<UserDB>() {
            }.getType();
            return gson.fromJson(fileReader, typeToken);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
