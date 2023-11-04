package MusicConsoleApp.View;

import MusicConsoleApp.Models.Songs;

import java.util.Scanner;

public class SongRemainingTime {

    public int convertSongDuration(Songs songs) {
        String[] parts = songs.getDuration().split(":");
        int duration = Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
        return duration;
    }

    public void visualizeRemainingTime(Songs songs, Scanner scanner) {
        int durationInSeconds = convertSongDuration(songs);

        long startTime = System.currentTimeMillis();
        long endTime = startTime + (durationInSeconds * 1000L);

        while (System.currentTimeMillis() < endTime) {
            long remainingTime = (endTime - System.currentTimeMillis()) / 1000;
            System.out.print("\rTime remaining: " + remainingTime + " seconds");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\nSong has ended");
    }
}
