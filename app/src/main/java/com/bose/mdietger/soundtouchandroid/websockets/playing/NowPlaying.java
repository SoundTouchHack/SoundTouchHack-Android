package com.bose.mdietger.soundtouchandroid.websockets.playing;

import com.bose.mdietger.soundtouchandroid.websockets.ContentItem;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

/**
 * NowPlaying class. Part of NowPlayingUpdated class. Contains updated
 * playing info.
 */
public class NowPlaying {

    @Attribute(name = "deviceID")
    private String deviceId;

    @Attribute(name = "source")
    private String source;

    @Element(name = "ContentItem")
    private ContentItem contentItem;

    @Element(name = "track")
    private String track;

    @Element(name = "artist")
    private String artist;

    @Element(name = "album")
    private String album;

    @Element(name = "stationName")
    private String stationName;

    @Element(name = "playStatus")
    private String playStatus;

    @Element(name = "description")
    private String description;

    @Element(name = "stationLocation")
    private String stationLocation;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public ContentItem getContentItem() {
        return contentItem;
    }

    public void setContentItem(ContentItem contentItem) {
        this.contentItem = contentItem;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getPlayStatus() {
        return playStatus;
    }

    public void setPlayStatus(String playStatus) {
        this.playStatus = playStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStationLocation() {
        return stationLocation;
    }

    public void setStationLocation(String stationLocation) {
        this.stationLocation = stationLocation;
    }
}
