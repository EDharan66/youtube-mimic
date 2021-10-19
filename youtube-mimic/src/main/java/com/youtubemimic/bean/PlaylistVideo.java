package com.youtubemimic.bean;

public class PlaylistVideo {
    String videoId;
    String dateAdded;

    public PlaylistVideo(String videoId, String string) {
        this.videoId = videoId;
        this.dateAdded = string;
    }

    public PlaylistVideo() {
    }

    public String getVideoId() {
        return this.videoId;
    }

    public String getDateAdded() {
        return this.dateAdded;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }
}
