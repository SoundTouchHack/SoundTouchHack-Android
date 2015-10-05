package com.bose.mdietger.soundtouchandroid.http.volume;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

/**
 * VolumeResponse class. This class represents the volume response.
 */
@Root
public class VolumeResponse {

    @Attribute(name = "deviceID")
    private String deviceId;

    @Element(name = "targetvolume")
    private Integer targetVolume;

    @Element(name = "actualvolume")
    private Integer actualVolume;

    @Element(name = "muteenabled", required = false)
    private Boolean muteEnabled;

    /**
     * VolumeResponse
     */
    public VolumeResponse() {

    }

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
     * @return the targetVolume
     */
    public Integer getTargetVolume() {
        return targetVolume;
    }

    /**
     * @param targetVolume the targetVolume to set
     */
    public void setTargetVolume(Integer targetVolume) {
        this.targetVolume = targetVolume;
    }

    /**
     * @return the actualVolume
     */
    public Integer getActualVolume() {
        return actualVolume;
    }

    /**
     * @param actualVolume the actualVolume to set
     */
    public void setActualVolume(Integer actualVolume) {
        this.actualVolume = actualVolume;
    }

    /**
     * @return true if mute is enabled, false if not
     */
    public Boolean getMuteEnabled() {
        return muteEnabled;
    }

    /**
     * @param muteEnabled true if mute is enabled, false if not
     */
    public void setMuteEnabled(Boolean muteEnabled) {
        this.muteEnabled = muteEnabled;
    }

}
