package com.bose.mdietger.soundtouchandroid.http.bass;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by BC828427 on 8/10/2015.
 */
@Root
public class BassResponse {
    @Attribute(name = "deviceID")
    private String deviceId;

    @Element(name = "targetbass")
    private Integer targetbass;

    @Element(name = "actualbass")
    private Integer actualbass;


    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getTargetbass() {
        return targetbass;
    }

    public void setTargetbass(Integer targetbass) {
        this.targetbass = targetbass;
    }

    public Integer getActualbass() {
        return actualbass;
    }

    public void setActualbass(Integer actualbass) {
        this.actualbass = actualbass;
    }
}
