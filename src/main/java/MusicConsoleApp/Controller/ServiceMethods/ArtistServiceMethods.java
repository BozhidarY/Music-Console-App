package MusicConsoleApp.Controller.ServiceMethods;

import MusicConsoleApp.Controller.FileHandling.LoadSongs;
import MusicConsoleApp.Controller.SongData;
import MusicConsoleApp.Models.Constants;
import MusicConsoleApp.Models.Songs;
import MusicConsoleApp.Models.Artist;

import java.util.Scanner;

public class ArtistServiceMethods {
    Scanner scanner = new Scanner(System.in);
    LoadSongs loadSongs = new LoadSongs();

    public void addSongToJson(SongData songData, Artist artist) {
        songData = loadSongs.loadFromFile(Constants.SONG_JSON_PATH);
        System.out.println("As an artist you add songs to your profile");
        System.out.println("Type the name of the song you want to add");
        String songName = scanner.nextLine();
        Songs newSong = new Songs(songName, artist);
        songData.getSongsList().add(newSong);
        loadSongs.saveSongs(Constants.SONG_JSON_PATH, songData);
    }

    public void printArtistSongs(SongData songData, Artist artist) {
        songData = loadSongs.loadFromFile(Constants.SONG_JSON_PATH);
        for (Songs song : songData.getSongsList()) {
            if (song.getArtist().getUsername().equals(artist.getUsername())) {
                System.out.println("Your songs: ");
                System.out.println(song);
            }
        }
    }
}
