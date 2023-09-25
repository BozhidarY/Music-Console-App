package MusicConsoleApp.Controller.UserControllers;

import MusicConsoleApp.Controller.FileHandling.LoadSaveUsersToJson;
import MusicConsoleApp.Controller.SongData;
import MusicConsoleApp.Controller.UserDB;
import MusicConsoleApp.Models.*;
import MusicConsoleApp.Controller.SongRemainingTime;

import java.util.*;
import java.util.stream.Collectors;

public class ClientController {
    Scanner scanner = new Scanner(System.in);
    SongRemainingTime songRemainingTime = new SongRemainingTime();
    LoadSaveUsersToJson loadSaveUsersToJson = new LoadSaveUsersToJson();

    private Client client;
    public ClientController(Client client){
        this.client = client;
    }

    public List<Songs> searchMenu(SongData songData, String substring) {
        return songData.getSongsList().stream()
                .filter(songs -> songs.getName().contains(substring))
                .collect(Collectors.toList());
    }


    public Songs SongByChoice(List<Songs> filteredSongs, String songChoice) {
        filteredSongs.forEach(songs -> System.out.println(songs));
        for (Songs song : filteredSongs) {
            if (song.getName().equals(songChoice)) {
                return song;
            }
        }
        return null;
    }

    public void visualizeSongRemainingTime(Songs song){
        System.out.println("You re listening");
        songRemainingTime.stopwatch(song, scanner);
        song.setTimesListened(song.getTimesListened() + 1);
    }


    public Songs randomSong(SongData songData) {
        Random random = new Random();
        int randomIndex = random.nextInt(songData.getSongsList().size());
        Songs currentSong = songData.getSongsList().get(randomIndex);
        return currentSong;
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

    public Playlists searchPlaylist(String playlistChoice) {
        for(Playlists playlist: client.getLibrary().getLibraryList()){
            if(playlistChoice.equals(playlist.getPlaylistName())){
                return playlist;
            }
        }
        return null;
    }

    public Songs searchSongInPlaylist(Playlists playlists, String choiceSong){
        for(Songs song: playlists.getSongPlaylist()){
            if(song.getName().equals(choiceSong)){
                return song;
            }

        }
        return null;
    }

    public void addPlaylist(String playlistName) {
        Playlists playlists = new Playlists(playlistName);
        client.getLibrary().getLibraryList().add(playlists);
    }

    public void deletePlaylist(String playlistName) {
        List<Playlists> removedPlaylists = new ArrayList<>();
        for (Playlists playlists : client.getLibrary().getLibraryList()) {
            if (playlistName.equals(playlists.getPlaylistName())) {
                removedPlaylists.add(playlists);
            }
        }
        client.getLibrary().getLibraryList().removeAll(removedPlaylists);
    }

//    public void choosePlaylis

    public void addSong(Playlists playlist, String songName, SongData songData) {
        for(Songs song: songData.getSongsList()){
            if (song.getName().equals(songName)) {
                playlist.getSongPlaylist().add(song);
            }
        }
    }

    public void deleteSong(Playlists playlist, String songName) {
        List<Songs> deletedSongs = new ArrayList<>();
        for (Songs song : playlist.getSongPlaylist()) {
            if (song.getName().equals(songName)) {
                deletedSongs.add(song);
            }
        }
        playlist.getSongPlaylist().removeAll(deletedSongs);
    }

    public void importLibrary(UserDB userDB, String username, String password) {
        userDB.getUsersList().forEach(client1 -> {
            if (client1.getUsername().equals(username) && client1.getPassword().equals(password) && client1 instanceof Client client2) {
                client.setLibrary(client2.getLibrary());
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    //    public void likeSong(Client client, Songs song) {
//        boolean result = false;
//        System.out.println("Did you like the song? (Y/N)");
//        String likeChoice = scanner.nextLine();
//        if (likeChoice.equals("Y")) {
//            for (Playlists playlists : client.getLibrary().getLibraryList()) {
//                if (playlists.getPlaylistName().equals("Like playlist")) {
////                    song.setLiked(true);
//                    playlists.getSongPlaylist().add(song);
//                    result = true;
//                }
//            }
//            if (!result) {
//                Playlists playlists = new Playlists("Like playlist");
//                client.getLibrary().getLibraryList().add(playlists);
////                song.setLiked(true);
//                playlists.getSongPlaylist().add(song);
//            }
//        } else if (likeChoice.equals("N")) {
////            song.setLiked(false);
//            for (Playlists playlists : client.getLibrary().getLibraryList()) {
//                if (playlists.getPlaylistName().equals("Like playlist")) {
//                    playlists.getSongPlaylist().remove(song);
//                    System.out.println("Success");
//                }
//            }
//        }
//    }
}


