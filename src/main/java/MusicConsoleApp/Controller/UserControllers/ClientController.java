package MusicConsoleApp.Controller.UserControllers;

import MusicConsoleApp.Controller.FileHandling.LoadSongs;
import MusicConsoleApp.Controller.SongData;
import MusicConsoleApp.Controller.UserDB;
import MusicConsoleApp.Models.Client;
import MusicConsoleApp.Models.Constants;
import MusicConsoleApp.View.ClientView;

import java.util.Scanner;

public class ClientController {
    private Client client;
    private ClientView clientView;
    Scanner scanner = new Scanner(System.in);
    LoadSongs loadSongs = new LoadSongs();
    SongData songData = loadSongs.loadFromFile(Constants.SONG_JSON_PATH);

    public ClientController(Client client, ClientView clientView){
        this.client = client;
        this.clientView = clientView;
    }

    public void openClientCommunication(UserDB userDB) {

        clientView.openMessage(client);

        String choice = scanner.nextLine();
        while (!choice.equals("Exit")) {
            switch (choice.toUpperCase()) {
                case "LISTEN" -> {
                    System.out.println("Search for a song/ Listen to a random one/Listen to a song from a playlist of yours");
                    System.out.println("List of commands: Search/Random/Playlist");
                    String listenChoice = scanner.nextLine();
                    switch (listenChoice.toUpperCase()) {
                        case "SEARCH" -> {
                            clientView.searchBar(songData, client, userDB);
                        }
                        case "RANDOM" -> clientView.randomSong(client, songData);
                        case "PLAYLIST" -> clientView.playlistListen(client, songData);
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
                        case "ADDPLAYLIST" -> clientView.addPlaylist(client);
                        case "DELETEPLAYLIST" -> clientView.deletePlaylist(client);
                        case "ADDSONG" -> clientView.addSong(client, songData);
                        case "DELETESONG" -> clientView.deleteSong(client);
                        default -> {
                            System.out.println("Invalid Input");
                        }
                    }
                }
                case "IMPORT" -> clientView.importLibrary(client, userDB);
                default -> {
                    System.out.println("Invalid Input");
                }
                case "INBOX" -> {
                    clientView.favouriteArtist(userDB);
                }
            }

            clientView.artistDataChange(songData, userDB);
            loadSongs.saveSongs(Constants.SONG_JSON_PATH, songData);
            System.out.println("Choose a new mode or exit the programm(Listen, Edit, Import, Exit)");
            choice = scanner.nextLine();
        }
    }
}
