package MusicConsoleApp.View;

import MusicConsoleApp.Controller.ClientController;

import MusicConsoleApp.DB.FileHandling.LoadSaveSongs;
import MusicConsoleApp.DB.SongDB;
import MusicConsoleApp.Models.Constants;
import MusicConsoleApp.Models.Playlist;
import MusicConsoleApp.Models.Songs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ClientView {
    Scanner scanner = new Scanner(System.in);
    LoadSaveSongs loadSaveSongs = new LoadSaveSongs();
    SongDB songDB = loadSaveSongs.loadSongFromJson(Constants.SONG_JSON_PATH);
    SongRemainingTime songRemainingTime = new SongRemainingTime();
    private ClientController clientController;

    public ClientView(ClientController clientController) {
        this.clientController = clientController;
    }

    public void openClientCommunication() {

        System.out.println("Welcome " + clientController.getClient().getUsername() + ". There re 3 modes to operate in this app.\nThe first mode is that" +
                " you can listen to music. \nSecond one - change and add playlists to your library and \nthe third" +
                " one - you can import a library from another account to yours. Inbox - check your favourite artists.");
        System.out.println("List of commands: Listen/Edit/Import/Exit");

        String choice = scanner.nextLine();
        while (!choice.equals("Exit")) {
            switch (choice.toUpperCase()) {
                case "LISTEN" -> {
                    System.out.println("Search for a song/ Listen to a random one/Listen to a song from a playlist of yours");
                    System.out.println("List of commands: Search/Random/Playlist");
                    String listenChoice = scanner.nextLine();
                    switch (listenChoice.toUpperCase()) {
                        case "SEARCH" -> {
                            List<Songs> filteredSongs = searchForSongDialog();
                            if (filteredSongs != null) {
                                listenChoosenSongDialog(filteredSongs);
                            }
                        }
                        case "RANDOM" -> {
                            Songs song = clientController.getRandomSong();
                            System.out.println("Now playing: " + song);
                            visualizeSongRemainingTime(song);
                        }
                        case "PLAYLIST" -> {
                            Playlist playlist = checkIfPlaylistExistsDialog();
                            if (playlist != null) {
                                checkIfSongInPlaylistExistsDialog(playlist);
                            }
                        }
                        default -> {
                            System.out.println("Invalid Input");
                        }
                    }
                }
                case "EDIT" -> {
                    System.out.println("In case 'Edit' you can do 4 things. Add/Delete a playlist to/from your library or " +
                            "Add/Delete a song to/from a playlist");
                    System.out.println("List of commands: AddPlaylist/DeletePlaylist/AddSong/DeleteSong");
                    String choiceEdit = scanner.nextLine();
                    switch (choiceEdit.toUpperCase()) {
                        case "ADDPLAYLIST" -> {
                            System.out.println("Choose a name for your playlist");
                            String playlistName = scanner.nextLine();
                            clientController.addPlaylist(playlistName);
                        }
                        case "DELETEPLAYLIST" -> {
                            deletePlaylistDialog();
                        }
                        case "ADDSONG" -> {
                            Playlist playlist = checkIfPlaylistExistsDialog();
                            if (playlist != null) {
                                addSongInPlaylistDialog(playlist);
                            }
                        }
                        case "DELETESONG" -> {
                            Playlist playlist = checkIfPlaylistExistsDialog();
                            if (playlist != null) {
                                deleteSongDialog(playlist);
                            }
                        }
                        default -> {
                            System.out.println("Invalid Input");
                        }
                    }
                }
                case "IMPORT" -> {
                    System.out.println("If you have 2 accounts you can import the library from your other account to this account" +
                            "You only need an username and password of the other account" +
                            "Note: You can have only one library, so if you want to import, your current library will be overwritten");
                    importLibraryDialog();
                }
                default -> {
                    System.out.println("Invalid Input");
                }
                case "INBOX" -> {
                    HashMap<String, Integer> favouriteArtist = clientController.favouriteArtist();
                    if (favouriteArtist.isEmpty()) {
                        System.out.println("You still haven't listened to anyone");
                    } else {
                        System.out.println("HashMap Contents:");
                        for (Map.Entry<String, Integer> entry : favouriteArtist.entrySet()) {
                            String key = entry.getKey();
                            Integer value = entry.getValue();
                            System.out.println("Artist Name: " + key + ", Total listens: " + value);
                        }
                    }
                }
            }
            clientController.changeArtistDataViews();
            loadSaveSongs.saveSongsToJson(Constants.SONG_JSON_PATH, songDB);
            System.out.println("Choose a new mode or exit the programm(Listen, Edit, Import, Exit)");
            choice = scanner.nextLine();
        }
    }

    public List<Songs> searchForSongDialog() {
        System.out.println("Search a song to listen to:");
        System.out.print("Search Bar: ");
        String searchWord = scanner.nextLine();
        List<Songs> filteredSongs = clientController.filterSongsBySubstring(searchWord);
        for (Songs song : filteredSongs) {
            System.out.println(song);
        }
        if (filteredSongs.isEmpty()) {
            System.out.println("No songs with this name. Do you want to try again? ");
            String choice = scanner.nextLine();
            if (choice.equals("Y")) {
                searchForSongDialog();
            } else {
                return null;
            }
        }
        return filteredSongs;

    }

    public void listenChoosenSongDialog(List<Songs> filteredSongs) {
        System.out.println("Choose which song to play");
        String choiceSong = scanner.nextLine();
        Songs song = clientController.getSongByChoice(filteredSongs, choiceSong);
        if (song == null) {
            System.out.println("Wrong song name. Try again");
            listenChoosenSongDialog(filteredSongs);
        } else {
            visualizeSongRemainingTime(song);
        }
    }

    public Playlist checkIfPlaylistExistsDialog() {
        System.out.println("Search playlist");
        String playlistChoice = scanner.nextLine();
        Playlist choosenPlaylist = clientController.searchPlaylistFromLibrary(playlistChoice);
        if (choosenPlaylist == null) {
            System.out.println("No playlist with that name. Do you want to try again");
            String choice = scanner.nextLine();
            if (choice.equals("Y")) {
                checkIfPlaylistExistsDialog();
            }
        }
        return choosenPlaylist;
    }

    public void checkIfSongInPlaylistExistsDialog(Playlist choosenPlaylist) {
        if (choosenPlaylist.getSongPlaylist().isEmpty()) {
            System.out.println("No added songs in this playlist");
        } else {
            System.out.println("Which song do you want to play?");
            String songChoice = scanner.nextLine();
            Songs song = clientController.searchSongInPlaylist(choosenPlaylist, songChoice);
            if (song == null) {
                System.out.println("Wrong name. Try again");
                checkIfSongInPlaylistExistsDialog(choosenPlaylist);
            } else {
                visualizeSongRemainingTime(song);
            }
        }
    }

    public void deletePlaylistDialog() {
        System.out.println("Which playlist to delete");
        String playlistName = scanner.nextLine();
        if (clientController.deletePlaylist(playlistName)) {
            System.out.println("Playlist deleted");
        } else {
            System.out.println("No playlist found. Try again.");
            String choice = scanner.nextLine();
            if (choice.equals("Y")) {
                deletePlaylistDialog();
            }
        }
    }

    public void addSongInPlaylistDialog(Playlist choosenPlaylist) {
        System.out.println("Which song to add");
        String songChoice = scanner.nextLine();
        if (clientController.addSong(choosenPlaylist, songChoice)) {
            System.out.println("Success");
        } else {
            System.out.println("No such song. Try again.");
            String choice = scanner.nextLine();
            if (choice.equals("Y")) {
                addSongInPlaylistDialog(choosenPlaylist);
            }
        }
    }

    public void deleteSongDialog(Playlist choosenPlaylist) {
        System.out.println("Which song to delete");
        String songChoice = scanner.nextLine();
        if (!clientController.deleteSong(choosenPlaylist, songChoice)) {
            System.out.println("No songs with that name found. Try again?");
            String choice = scanner.nextLine();
            if (choice.equals("Y")) {
                deleteSongDialog(choosenPlaylist);
            }
        } else {
            System.out.println("Success");
        }
    }

    public void importLibraryDialog() {
        System.out.println("Enter username");
        String username = scanner.nextLine();
        if (!clientController.importLibrary(username)) {
            System.out.println("No user with that name found. Do you want to try again?");
            String choice = scanner.nextLine();
            if (choice.equals("Y")) {
                importLibraryDialog();
            }
        } else {
            System.out.println("Success");
        }
    }

    public void visualizeSongRemainingTime(Songs song) {
        songRemainingTime.visualizeRemainingTime(song, scanner);
        song.setTimesListened(song.getTimesListened() + 1);
    }

}
