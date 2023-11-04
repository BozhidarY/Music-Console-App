package MusicConsoleApp.DB.FileHandling;

import MusicConsoleApp.Models.*;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserDeserializer implements JsonDeserializer<Users> {
    @Override
    public Users deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        String username = jsonObject.get("username").getAsString();
        String password = jsonObject.get("password").getAsString();
        UserType userType = UserType.valueOf(jsonObject.get("userType").getAsString());

        Users users = null;

        if (userType == UserType.CLIENT) {
            Client client = new Client(username, password);

            JsonObject libraryJson = jsonObject.getAsJsonObject("library");
            Library library = new Library(client.getUsername() + " library");

            JsonArray playlistsArray = libraryJson.getAsJsonArray("libraryPlaylists");
            List<Playlist> playlistList = new ArrayList<>();

            if (playlistsArray != null) {
                for (JsonElement playlistElement : playlistsArray) {
                    JsonObject playlistJson = playlistElement.getAsJsonObject();

                    String playlistName = playlistJson.get("playlistName").getAsString();

                    JsonArray songPlaylistArray = playlistJson.getAsJsonArray("songPlaylist");
                    List<Songs> songPlaylist = new ArrayList<>();

                    if (songPlaylistArray != null) {
                        for (JsonElement songElement : songPlaylistArray) {
                            Songs song = new Gson().fromJson(songElement, Songs.class);
                            songPlaylist.add(song);
                        }
                    }
                    Playlist playlist = new Playlist(playlistName);

                    playlist.setSongPlaylist(songPlaylist);
                    playlistList.add(playlist);
                }
            }
            library.setLibraryList(playlistList);
            client.setLibrary(library);
            client.setUserType(userType);

            users = client;
        } else if (userType == UserType.ARTIST) {
            Artist artist = new Artist(username, password);

            artist.setTotalViews(jsonObject.get("totalViews").getAsLong());

            users = artist;
        }
        return users;
    }
}
