package MusicConsoleApp.Models;

public class Songs {
    private String name;
    private Artist artist;
    private String artistName;
    private String duration;
    private int timesListened;
//    private boolean liked;

    public Songs(String name, Artist artist) {
        this.name = name;
        this.artistName = artist.getUsername();
        setDuration("0:05");
        this.timesListened = 0;
//        setLiked(false);
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

    public int getTimesListened() {
        return timesListened;
    }

    public void setTimesListened(int timesListened) {
        this.timesListened = timesListened;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
    //    public boolean isLiked() {
//        return liked;
//    }
//
//    public void setLiked(boolean liked) {
//        this.liked = liked;
//    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", duration='" + duration + '\'';
    }
}
