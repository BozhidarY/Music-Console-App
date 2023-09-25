package MusicConsoleApp.Controller.UserControllers;

import MusicConsoleApp.Controller.FileHandling.LoadSongs;
import MusicConsoleApp.Controller.SongData;
import MusicConsoleApp.Controller.UserDB;
import MusicConsoleApp.Models.Artist;
import MusicConsoleApp.Models.Constants;
import MusicConsoleApp.View.ArtistView;

public class ArtistController {
    private Artist artist;
    private ArtistView artistView;
    LoadSongs loadSongs = new LoadSongs();
    SongData songData = loadSongs.loadFromFile(Constants.SONG_JSON_PATH);

    public ArtistController(Artist artist, ArtistView artistView){
        this.artist = artist;
        this.artistView = artistView;
    }

    public void openArtistCommunication(UserDB userDB) {
        artistView.showMostListened(userDB);
        artistView.addSongToJson(songData, artist);
    }
}
