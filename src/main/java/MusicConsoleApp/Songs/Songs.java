package MusicConsoleApp.Songs;

import MusicConsoleApp.Users.Artist;

public class Songs {
    public static final String SONG_JSON_PATH = System.getProperty("user.dir") + "\\src\\main\\java\\MusicConsoleApp\\Songs\\songsFile.json";
    private String name;
    //    private Artists artist;
    private Artist artist;
    private String duration;
    private boolean liked;

    public Songs(String name, Artist artist) {
        this.name = name;
        this.artist = artist;
        setDuration("0:05");
        setLiked(false);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", duration='" + duration + '\'' +
                ", liked=" + liked;
    }
}
