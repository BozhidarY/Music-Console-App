package MusicConsoleApp.View;

import MusicConsoleApp.Controller.FileHandling.LoadSaveUsersToJson;
import MusicConsoleApp.Controller.ServiceMethods.AdminServiceMethods;
import MusicConsoleApp.Controller.ServiceMethods.ArtistServiceMethods;
import MusicConsoleApp.Controller.ServiceMethods.ClientServiceMethods;
import MusicConsoleApp.Controller.FileHandling.LoadSongs;
import MusicConsoleApp.Controller.SongData;
import MusicConsoleApp.Models.*;
import MusicConsoleApp.Controller.UserDB;

import java.util.Scanner;

public class UserService {
    Scanner scanner = new Scanner(System.in);
    LoadSongs loadSongs = new LoadSongs();
    SongData songData = loadSongs.loadFromFile(Constants.SONG_JSON_PATH);
    ClientServiceMethods clientServiceMethods = new ClientServiceMethods();
    ArtistServiceMethods artistServiceMethods = new ArtistServiceMethods();
    AdminServiceMethods adminServiceMethods = new AdminServiceMethods();


    public void openClientCommunication(Client client, UserDB userDB) {

        clientServiceMethods.openMessage(client);

        String choice = scanner.nextLine();
        while (!choice.equals("Exit")) {
            switch (choice.toUpperCase()) {
                case "LISTEN" -> {
                    System.out.println("Search for a song/ Listen to a random one/Listen to a song from a playlist of yours");
                    System.out.println("List of commands: Search/Random/Playlist");
                    String listenChoice = scanner.nextLine();
                    switch (listenChoice.toUpperCase()) {
                        case "SEARCH" -> {
                            clientServiceMethods.searchBar(songData, client, userDB);
//                            loadSaveUsersToJson.saveUsers(Constants.USERS_JSON_PATH, userDB);
//                            loadSaveUsersToJson.loadUsers(Constants.USERS_JSON_PATH);
//                            clientServiceMethods.setDefaultPlaylist(client);
                        }
                        case "RANDOM" -> clientServiceMethods.randomSong(client, songData);
                        case "PLAYLIST" -> clientServiceMethods.playlistListen(client, songData);
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
                        case "ADDPLAYLIST" -> clientServiceMethods.addPlaylist(client);
                        case "DELETEPLAYLIST" -> clientServiceMethods.deletePlaylist(client);
                        case "ADDSONG" -> clientServiceMethods.addSong(client, songData);
                        case "DELETESONG" -> clientServiceMethods.deleteSong(client);
                        default -> {
                            System.out.println("Invalid Input");
                        }
                    }
                }
                case "IMPORT" -> clientServiceMethods.importLibrary(client, userDB);
                default -> {
                    System.out.println("Invalid Input");
                }
                case "INBOX" -> {
                    clientServiceMethods.favouriteArtist(userDB);
                }
            }

            clientServiceMethods.artistDataChange(songData, userDB);
            loadSongs.saveSongs(Constants.SONG_JSON_PATH, songData);
            System.out.println("Choose a new mode or exit the programm(Listen, Edit, Import, Exit)");
            choice = scanner.nextLine();
        }
    }

    public void openAdminCommunication(Admin admin, UserDB userDB) {
        System.out.println("Do you want to delete or recover account");
        String choice = scanner.nextLine();
        switch (choice) {
            case "Recover" -> adminServiceMethods.recoverAccount(admin, userDB);
            case "Delete" -> adminServiceMethods.deleteAccount(admin, userDB);
        }
    }

    public void openArtistCommunication(Artist artist, UserDB userDB) {
//        artistServiceMethods.printArtistSongs(songData, artist);
        artistServiceMethods.showMostListened(userDB);
        artistServiceMethods.addSongToJson(songData, artist);
    }
}
