package MusicConsoleApp.Models;

public class Artist extends Users {

    private long totalViews;

    public Artist(String username, String password) {
        super(username, password);
        this.totalViews = 0;
        setUserType(UserType.ARTIST);
    }

    public void listenToSong() {
        this.totalViews++;
    }

//    public List<Songs> getSongsList() {
//        return songsList;
//    }
//
//    public void setSongsList(List<Songs> songsList) {
//        this.songsList = songsList;
//    }

    public long getTotalViews() {
        return totalViews;
    }

    public void setTotalViews(long totalViews) {
        this.totalViews = totalViews;
    }
}
