package MusicConsoleApp.View;

import MusicConsoleApp.Controller.FileHandling.LoadSongs;
import MusicConsoleApp.Controller.SongData;
import MusicConsoleApp.Controller.UserControllers.ClientController;
import MusicConsoleApp.Controller.UserDB;
import MusicConsoleApp.Models.Client;
import MusicConsoleApp.Models.Constants;
import MusicConsoleApp.Models.Playlists;
import MusicConsoleApp.Models.Songs;

import java.util.List;
import java.util.Scanner;

public class ClientView {
    private ClientController clientController;
    Scanner scanner = new Scanner(System.in);
    LoadSongs loadSongs = new LoadSongs();
    SongData songData = loadSongs.loadFromFile(Constants.SONG_JSON_PATH);

    public ClientView(ClientController clientController){
        this.clientController = clientController;
    }

    public void openClientCommunication(UserDB userDB) {

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
                            System.out.println("Search a song to listen to:");
                            System.out.print("Search Bar: ");
                            String searchWord = scanner.nextLine();
                            List<Songs> filteredSongs = clientController.searchMenu(songData, searchWord);
                            if (filteredSongs.isEmpty()) {
                                System.out.println("No songs with this name. Try again");
                            }
                            else{
                                System.out.println("Choose which song to play");
                                String choiceSong = scanner.nextLine();
                                Songs song = clientController.SongByChoice(filteredSongs, choiceSong);
                                clientController.visualizeSongRemainingTime(song);
                            }
                        }
                        case "RANDOM" -> {
                            Songs song = clientController.randomSong(songData);
                            clientController.visualizeSongRemainingTime(song);
                        }
                        case "PLAYLIST" -> {
                            System.out.println("Search playlist");
                            String playlistChoice = scanner.nextLine();
                            Playlists choosenPlaylist = clientController.searchPlaylist(playlistChoice);
                            System.out.println("Which song do you want to play?");
                            String songChoice = scanner.nextLine();
                            Songs song = clientController.searchSongInPlaylist(choosenPlaylist, songChoice);
                            clientController.visualizeSongRemainingTime(song);
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
                            System.out.println("Which playlist to delete");
                            String playlistName = scanner.nextLine();
                            clientController.deletePlaylist(playlistName);
                        }
                        case "ADDSONG" -> {
                            System.out.println("Choose in which playlist to add a song");
                            String playlistName = scanner.nextLine();
                            Playlists choosenPlaylist = clientController.searchPlaylist(playlistName);
                            System.out.println("Which song to delete");
                            String songChoice = scanner.nextLine();
                            clientController.addSong(choosenPlaylist,songChoice, songData);
                        }
                        case "DELETESONG" -> {
                            System.out.println("Choose from which playlist to delete");
                            String playlistName = scanner.nextLine();
                            Playlists choosenPlaylist = clientController.searchPlaylist(playlistName);
                            System.out.println("Which song to delete");
                            String songChoice = scanner.nextLine();
                            clientController.deleteSong(choosenPlaylist, songChoice);
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
                    System.out.println("Enter username and password");
                    String username = scanner.nextLine();
                    String password = scanner.nextLine();
                    clientController.importLibrary(userDB, username, password);
                }
                default -> {
                    System.out.println("Invalid Input");
                }
                case "INBOX" -> {
                    clientController.favouriteArtist(userDB);
                }
            }

            clientController.artistDataChange(songData, userDB);
            loadSongs.saveSongs(Constants.SONG_JSON_PATH, songData);
            System.out.println("Choose a new mode or exit the programm(Listen, Edit, Import, Exit)");
            choice = scanner.nextLine();
        }
    }
}
