package MusicConsoleApp.Songs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LoadSongs {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public SongData loadFromFile(String filePath) {
        try (FileReader fileReader = new FileReader(filePath)) {
            SongData songData = gson.fromJson(fileReader, SongData.class);
            return songData;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveSongs(String fileName, SongData songData) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            gson.toJson(songData, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
