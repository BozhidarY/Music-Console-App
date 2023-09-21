package MusicConsoleApp.Controller.ServiceMethods;

import MusicConsoleApp.Controller.FileHandling.LoadSaveUsersToJson;
import MusicConsoleApp.Controller.SongData;
import MusicConsoleApp.Controller.UserDB;
import MusicConsoleApp.Models.*;

import java.util.*;
import java.util.stream.Collectors;

public class ClientServiceMethods {
    Scanner scanner = new Scanner(System.in);
    SongRemainingTime songRemainingTime = new SongRemainingTime();
    LoadSaveUsersToJson loadSaveUsersToJson = new LoadSaveUsersToJson();

    public void openMessage(Client client) {
        System.out.println("Welcome " + client.getUsername() + ". There re 3 modes to operate in this app.\nThe first mode is that" +
                " you can listen to music. \nSecond one - change and add playlists to your library and \nthe third" +
                " one - you can import a library from another account to yours. Inbox - check your favourite artists.");
        System.out.println("List of commands: Listen/Edit/Import/Exit");
    }

    public List<Songs> searchMenu(SongData songData, String substring) {
        return songData.getSongsList().stream()
                .filter(songs -> songs.getName().contains(substring))
                .collect(Collectors.toList());
    }
    public void likeSong(Client client, Songs song) {
        boolean result = false;
        System.out.println("Did you like the song? (Y/N)");
        String likeChoice = scanner.nextLine();
        if (likeChoice.equals("Y")) {
            for (Playlists playlists : client.getLibrary().getLibraryList()) {
                if (playlists.getPlaylistName().equals("Like playlist")) {
//                    song.setLiked(true);
                    playlists.getSongPlaylist().add(song);
                    result = true;
                }
            }
            if (!result) {
                Playlists playlists = new Playlists("Like playlist");
                client.getLibrary().getLibraryList().add(playlists);
//                song.setLiked(true);
                playlists.getSongPlaylist().add(song);
            }
        } else if (likeChoice.equals("N")) {
//            song.setLiked(false);
            for (Playlists playlists : client.getLibrary().getLibraryList()) {
                if (playlists.getPlaylistName().equals("Like playlist")) {
                    playlists.getSongPlaylist().remove(song);
                    System.out.println("Success");
                }
            }
        }
    }

//    public void listenSong(Client client, List<Songs> songsToListen){
//
//    }

    public void searchBar(SongData songData, Client client, UserDB userDB) {
        List<Songs> filteredSongs;
        System.out.print("Search bar: ");
        String search = scanner.nextLine();
        filteredSongs = searchMenu(songData, search);
        if (filteredSongs.isEmpty()) {
            System.out.println("No songs with this name. Try again");
            searchBar(songData, client, userDB);
        }
        else{
            filteredSongs.forEach(songs -> System.out.println(songs));
            System.out.println("Choose a song name to play");
            String choiceSong = scanner.nextLine();
            for (Songs song : filteredSongs) {
                if (song.getName().equals(choiceSong)) {
                    int count = song.getTimesListened();
                    System.out.println("You re listening");
                    songRemainingTime.stopwatch(song, scanner);
                    song.setTimesListened(song.getTimesListened() + 1);
                }
            }
        }
    }
//    public void setDefaultPlaylist(Client client) {
//        client.getLibrary().getLibraryList().forEach(playlists -> {
//            if (playlists.getPlaylistName().equals("defaultPlaylist")) {
//                playlists.getSongPlaylist().forEach(songs -> {
//                    songs.setTimesListened(1);
//                });
//            }
//        });
//    }

    public void randomSong(Client client, SongData songData) {
        Random random = new Random();
        int randomIndex = random.nextInt(songData.getSongsList().size());
        Songs currentSong = songData.getSongsList().get(randomIndex);
        System.out.println("Now listening to " + currentSong.getName());
        songRemainingTime.stopwatch(currentSong, scanner);
        currentSong.setTimesListened(currentSong.getTimesListened() + 1);
//        likeSong(client, currentSong);
    }

    public void songDataChange(Songs song, SongData songData){
        for(Songs songFromData: songData.getSongsList()){
            if(song.getName().equals(songFromData.getName())){
                songFromData.setTimesListened(songFromData.getTimesListened() + 1);
            }
        }
    }
    public void artistDataChange(SongData songData, UserDB userDB){
        for(Users user: userDB.getUsersList()){
            if(user instanceof Artist artist){
                long counter = 0;
                for(Songs song: songData.getSongsList()){
                    if(artist.getUsername().equals(song.getArtistName())){
                        counter += song.getTimesListened();
                        artist.setTotalViews(counter);
                    }
                }
            }
        }
    }

    public void playlistListen(Client client, SongData songData) {
        System.out.println("From what playlist do you want to play a song");
        String playlistChoice = scanner.nextLine();
        client.getLibrary().getLibraryList().forEach(playlists -> {
            if (playlistChoice.equals(playlists.getPlaylistName())) {
                System.out.println("Songs in this playlist");
                playlists.getSongPlaylist().forEach(songs -> {
                    System.out.println(songs);
                });
                System.out.println("Choose a song to play");
                String choiceSong2 = scanner.nextLine();
                playlists.getSongPlaylist().forEach(songs -> {
                    if (songs.getName().equals(choiceSong2)) {
                        System.out.println("You re listening");
                        songRemainingTime.stopwatch(songs, scanner);
                        songs.setTimesListened(songs.getTimesListened() + 1);
                        songDataChange(songs, songData);
//                        likeSong(client, songs);
                    }
                });
            }
        });
    }

    public void addPlaylist(Client client) {
        System.out.println("Choose a name for the playlist");
        String playlistName = scanner.nextLine();
        Playlists playlists = new Playlists(playlistName);
        client.getLibrary().getLibraryList().add(playlists);
    }

    public void deletePlaylist(Client client) {
        List<Playlists> removedPlaylists = new ArrayList<>();
        System.out.println("Playlist name you want to remove");
        String playlistName = scanner.nextLine();
        for (Playlists playlists : client.getLibrary().getLibraryList()) {
            if (playlistName.equals(playlists.getPlaylistName())) {
                removedPlaylists.add(playlists);
            }
        }
        client.getLibrary().getLibraryList().removeAll(removedPlaylists);
    }

    public void addSong(Client client, SongData songData) {
        System.out.println("Choose in which playlist do you want to a add a song");
        String playlistChoice = scanner.nextLine();
        client.getLibrary().getLibraryList().forEach(playlists -> {
            if (playlistChoice.equals(playlists.getPlaylistName())) {
                System.out.println("You re now in " + playlists.getPlaylistName() +
                        ". Choose a name of a song you want to add");
                String songName = scanner.nextLine();
                songData.getSongsList().forEach(songs -> {
                    if (songs.getName().equals(songName)) {
                        playlists.getSongPlaylist().add(songs);
                    }
                });
            }
        });
    }

    public void deleteSong(Client client) {
        List<Songs> deletedSongs = new ArrayList<>();
        System.out.println("From which playlist do you want to delete a song?");
        String playlistChoice = scanner.nextLine();
        for (Playlists playlists : client.getLibrary().getLibraryList()) {
            if (playlists.getPlaylistName().equals(playlistChoice)) {
                System.out.println("Choose a song");
                String songName = scanner.nextLine();
                for (Songs song : playlists.getSongPlaylist()) {
                    if (song.getName().equals(songName)) {
                        deletedSongs.add(song);
                    }
                }
            }
            playlists.getSongPlaylist().removeAll(deletedSongs);
        }
    }

    public void importLibrary(Client client, UserDB userDB) {
        System.out.println("If you have 2 accounts you can import the library from your other account to this account" +
                "You only need an username and password of the other account" +
                "Note: You can have only one library, so if you want to import, your current library will be overwritten");
        System.out.println("Enter username and password");
        String username = scanner.nextLine();
        String password = scanner.nextLine();
        userDB.getUsersList().forEach(client1 -> {
            if (client1.getUsername().equals(username) && client1.getPassword().equals(password) && client1 instanceof Client client2) {
                client.setLibrary(client2.getLibrary());
                System.out.println("Changes made");
            }
        });
    }

    public void favouriteArtist(UserDB userDB){
        HashMap<String,Integer> favouriteArtist = new HashMap<>();
        for(Users user:userDB.getUsersList()){
            if(user instanceof Client client){
                for(Playlists playlist:client.getLibrary().getLibraryList()){
                    for(Songs song: playlist.getSongPlaylist()){
                        favouriteArtist.put(song.getArtistName(), favouriteArtist.getOrDefault(song.getArtistName(), 0) + song.getTimesListened());
                    }
                }
            }
        }
        System.out.println("HashMap Contents:");
        for (Map.Entry<String, Integer> entry : favouriteArtist.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println("Artist Name: " + key + ", Total listens: " + value);
        }
    }
}

