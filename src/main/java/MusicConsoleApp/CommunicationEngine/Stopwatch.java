package MusicConsoleApp.CommunicationEngine;

import MusicConsoleApp.Songs.Songs;

import java.util.Scanner;

public class Stopwatch {

    public int converSongDuration(Songs songs) {
        String[] parts = songs.getDuration().split(":");
        int duration = Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
        return duration;
    }

    public void stopwatch(Songs songs, Scanner scanner) {

        System.out.print("Enter the duration in seconds: ");
        int durationInSeconds = converSongDuration(songs);

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
