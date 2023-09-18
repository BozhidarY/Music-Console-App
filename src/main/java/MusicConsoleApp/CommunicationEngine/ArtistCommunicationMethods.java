package MusicConsoleApp.CommunicationEngine;

import MusicConsoleApp.Songs.LoadSongs;
import MusicConsoleApp.Songs.SongData;
import MusicConsoleApp.Songs.Songs;
import MusicConsoleApp.Users.Artist;

import java.util.Scanner;

public class ArtistCommunicationMethods {
    Scanner scanner = new Scanner(System.in);
    LoadSongs loadSongs = new LoadSongs();

    public void addSongToJson(SongData songData, Artist artist) {
        songData = loadSongs.loadFromFile(Songs.SONG_JSON_PATH);
        System.out.println("As an artist you add songs to your profile");
        System.out.println("Type the name of the song you want to add");
        String songName = scanner.nextLine();
        Songs newSong = new Songs(songName, artist);
        songData.getSongsList().add(newSong);
        loadSongs.saveSongs(Songs.SONG_JSON_PATH, songData);
    }

    public void printArtistSongs(SongData songData, Artist artist) {
        songData = loadSongs.loadFromFile(Songs.SONG_JSON_PATH);
        for (Songs song : songData.getSongsList()) {
            if (song.getArtist().getUsername().equals(artist.getUsername())) {
                System.out.println("Your songs: ");
                System.out.println(song);
            }
        }
    }
}
