package com.bose.mdietger.soundtouchandroid.http.bass;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * BassResponse class. This class represents the bass response.
 */
@Root
public class BassResponse {
    @Attribute(name = "deviceID")
    private String deviceId;

    @Element(name = "targetbass")
    private Integer targetbass;

    @Element(name = "actualbass")
    private Integer actualbass;

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
     * @return the targetBass
     */
    public Integer getTargetbass() {
        return targetbass;
    }

    /**
     * @param targetbass the targetBass to set
     */
    public void setTargetbass(Integer targetbass) {
        this.targetbass = targetbass;
    }

    /**
     * @return the actualBass
     */
    public Integer getActualbass() {
        return actualbass;
    }

    /**
     * @param actualbass the actualBass to set
     */
    public void setActualbass(Integer actualbass) {
        this.actualbass = actualbass;
    }

}
