package com.bose.mdietger.soundtouchandroid.websockets;

import com.bose.mdietger.soundtouchandroid.websockets.playing.NowPlayingUpdated;
import com.bose.mdietger.soundtouchandroid.websockets.selection.NowSelectionUpdated;
import com.bose.mdietger.soundtouchandroid.websockets.volume.VolumeUpdated;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * DeviceUpdate class. This class contains information for device
 * updates.
 */
@Root
public class DeviceUpdate {

    @Attribute(name = "deviceID")
    private String deviceId;

    @Element(name = "volumeUpdated", required = false)
    private VolumeUpdated volumeUpdated;

    @Element(name = "nowSelectionUpdated", required =  false)
    private NowSelectionUpdated nowSelectionUpdated;

    @Element(name = "nowPlayingUpdated", required = false)
    private NowPlayingUpdated nowPlayingUpdated;

    /**
     * @return the deviceId.
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
     * @return the volumeUpdated
     */
    public VolumeUpdated getVolumeUpdated() {
        return volumeUpdated;
    }

    /**
     * @param volumeUpdated the volumeUpdated to set
     */
    public void setVolumeUpdated(VolumeUpdated volumeUpdated) {
        this.volumeUpdated = volumeUpdated;
    }

    public NowSelectionUpdated getNowSelectionUpdated() {
        return nowSelectionUpdated;
    }

    public void setNowSelectionUpdated(NowSelectionUpdated nowSelectionUpdated) {
        this.nowSelectionUpdated = nowSelectionUpdated;
    }

    public NowPlayingUpdated getNowPlayingUpdated() {
        return nowPlayingUpdated;
    }

    public void setNowPlayingUpdated(NowPlayingUpdated nowPlayingUpdated) {
        this.nowPlayingUpdated = nowPlayingUpdated;
    }
}
