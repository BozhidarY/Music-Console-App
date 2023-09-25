package MusicConsoleApp.Controller.UserControllers;

import MusicConsoleApp.Controller.FileHandling.LoadSongs;
import MusicConsoleApp.Controller.SongData;
import MusicConsoleApp.Controller.UserDB;
import MusicConsoleApp.Models.Artist;
import MusicConsoleApp.Models.Constants;
import MusicConsoleApp.Models.Songs;
import MusicConsoleApp.View.ArtistView;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ArtistController {
    private Artist artist;
    LoadSongs loadSongs = new LoadSongs();
    Scanner scanner = new Scanner(System.in);


    public ArtistController(Artist artist){
        this.artist = artist;
    }

    public void addSongToJson(String songName) {
        SongData songData = loadSongs.loadFromFile(Constants.SONG_JSON_PATH);
        Songs newSong = new Songs(songName, artist);
        songData.getSongsList().add(newSong);
        loadSongs.saveSongs(Constants.SONG_JSON_PATH, songData);
    }

    public void printArtistSongs(Artist artist) {
        SongData songData = loadSongs.loadFromFile(Constants.SONG_JSON_PATH);
        for (Songs song : songData.getSongsList()) {
            if (song.getArtistName().equals(artist.getUsername())) {
                System.out.println("Your songs: ");
                System.out.println(song);
            }
        }
    }

    public List<Artist> showMostListened(UserDB userDB){
        List<Artist> artistList = userDB.getUsersList().stream()
                .filter(users -> users instanceof Artist)
                .map(users -> (Artist) users)
                .collect(Collectors.toList());

        Comparator<Artist> artistListenerComparator = Comparator.comparingLong(Artist::getTotalViews).reversed();
        artistList.sort(artistListenerComparator);
        return artistList;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}