package MusicConsoleApp.Controller.UserControllers;

import MusicConsoleApp.Controller.FileHandling.LoadSongs;
import MusicConsoleApp.Controller.SongData;
import MusicConsoleApp.Controller.UserDB;
import MusicConsoleApp.Models.*;
import MusicConsoleApp.Controller.SongRemainingTime;

import java.util.*;
import java.util.stream.Collectors;

public class ClientController {
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
        for (Songs song : filteredSongs) {
            if (song.getName().equals(songChoice)) {
                return song;
            }
        }
        return null;
    }


    public Songs randomSong(SongData songData) {
        Random random = new Random();
        int randomIndex = random.nextInt(songData.getSongsList().size());
        Songs currentSong = songData.getSongsList().get(randomIndex);
        return currentSong;
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

    public Playlist searchPlaylist(String playlistChoice) {
        for(Playlist playlist: client.getLibrary().getLibraryList()){
            if(playlistChoice.equals(playlist.getPlaylistName())){
                return playlist;
            }
        }
        return null;
    }

    public Songs searchSongInPlaylist(Playlist playlist, String choiceSong){
            for(Songs song: playlist.getSongPlaylist()){
                if(song.getName().equals(choiceSong)){
                    return song;
                }
            }
        return null;
    }

    public void addPlaylist(String playlistName) {
        Playlist playlist = new Playlist(playlistName);
        client.getLibrary().getLibraryList().add(playlist);
    }

    public boolean deletePlaylist(String playlistName) {
        List<Playlist> removedPlaylists = new ArrayList<>();
        for (Playlist playlist : client.getLibrary().getLibraryList()) {
            if (playlistName.equals(playlist.getPlaylistName())) {
                removedPlaylists.add(playlist);
            }
        }
        if(removedPlaylists.isEmpty()){
            return false;
        }
        else{
            client.getLibrary().getLibraryList().removeAll(removedPlaylists);
            return true;
        }
    }

    public boolean addSong(Playlist playlist, String songName, SongData songData) {
        for(Songs song: songData.getSongsList()){
            if (song.getName().equals(songName)) {
                playlist.getSongPlaylist().add(song);
                return true;
            }
        }
        return false;
    }

    public boolean deleteSong(Playlist playlist, String songName) {
        List<Songs> deletedSongs = new ArrayList<>();
        for (Songs song : playlist.getSongPlaylist()) {
            if (song.getName().equals(songName)) {
                deletedSongs.add(song);
            }
        }
        if(deletedSongs.isEmpty()){
            return false;
        }
        else {
            System.out.println("Success");
            playlist.getSongPlaylist().removeAll(deletedSongs);
            return true;
        }
    }

    public boolean importLibrary(UserDB userDB, String username) {
        for(Users user: userDB.getUsersList()){
            if(user instanceof Client importClient && user.getUsername().equals(username)){
                client.setLibrary(importClient.getLibrary());
                return true;
            }
        }
        return false;
    }

    public HashMap<String, Integer> favouriteArtist(UserDB userDB){
        HashMap<String,Integer> favouriteArtist = new HashMap<>();
        for(Users user:userDB.getUsersList()){
            if(user instanceof Client client){
                for(Playlist playlist:client.getLibrary().getLibraryList()){
                    for(Songs song: playlist.getSongPlaylist()){
                        favouriteArtist.put(song.getArtistName(), favouriteArtist.getOrDefault(song.getArtistName(), 0) + song.getTimesListened());
                    }
                }
            }
        }
        return favouriteArtist;

    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}


