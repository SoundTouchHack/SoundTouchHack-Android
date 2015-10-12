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

    /**
     * @return the deviceId
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * @param deviceId the deviceId to set
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return the contentItem
     */
    public ContentItem getContentItem() {
        return contentItem;
    }

    /**
     * @param contentItem the contentItem to set
     */
    public void setContentItem(ContentItem contentItem) {
        this.contentItem = contentItem;
    }

    /**
     * @return the track
     */
    public String getTrack() {
        return track;
    }

    /**
     * @param track the track to set
     */
    public void setTrack(String track) {
        this.track = track;
    }

    /**
     * @return the artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * @param artist the artist to set
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * @return the album
     */
    public String getAlbum() {
        return album;
    }

    /**
     * @param album the album to set
     */
    public void setAlbum(String album) {
        this.album = album;
    }

    /**
     * @return the stationName
     */
    public String getStationName() {
        return stationName;
    }

    /**
     * @param stationName the stationName to set
     */
    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    /**
     * @return the playStatus
     */
    public String getPlayStatus() {
        return playStatus;
    }

    /**
     * @param playStatus the playStatus to set
     */
    public void setPlayStatus(String playStatus) {
        this.playStatus = playStatus;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the stationLocation
     */
    public String getStationLocation() {
        return stationLocation;
    }

    /**
     * @param stationLocation the stationLocation to set
     */
    public void setStationLocation(String stationLocation) {
        this.stationLocation = stationLocation;
    }

}
