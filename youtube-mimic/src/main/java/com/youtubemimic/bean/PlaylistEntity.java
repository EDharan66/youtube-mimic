package com.youtubemimic.bean;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.OnSave;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
public class PlaylistEntity {
    @Id
    Long playlistId;
    @Index
    Long userId;
    @Index
    String playlistName;
    String createAt;
    @Index
    String modifiedAt;
    @Ignore
    String videoId;
    HashMap<String, PlaylistVideo> videos = new HashMap<String, PlaylistVideo>();

    public PlaylistEntity() {
    }

    public String getVideoId() {
        return this.videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public PlaylistEntity(Long playlistId, Long userId, String playlistName, String createAt, String modifiedAt, HashMap<String, PlaylistVideo> videos) {
        this.playlistId = playlistId;
        this.userId = userId;
        this.playlistName = playlistName;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
        this.videos = videos;
    }

    public Long getPlaylistId() {
        return this.playlistId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public String getPlaylistName() {
        return this.playlistName;
    }

    public String getCreateAt() {
        return this.createAt;
    }

    public String getModifiedAt() {
        return this.modifiedAt;
    }

    public Map<String, PlaylistVideo> getVideos() {
        return this.videos;
    }

    public void setPlaylistId(Long playlistId) {
        this.playlistId = playlistId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public void setModifiedAt(String modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public void setVideos(HashMap<String, PlaylistVideo> videos) {
        this.videos = videos;
    }
    
    @OnSave void setTimeUpdate() {
    	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		this.modifiedAt = formatter.format(date);
		
    }
}
