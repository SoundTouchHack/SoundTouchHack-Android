package com.bose.mdietger.soundtouchandroid.websockets.volume;

import org.simpleframework.xml.Element;

/**
 * Volume class. Part of NowSelectionUpdated class. Contains updated
 * Volume info.
 */
public class Volume {

    @Element(name = "targetvolume")
    private Integer targetVolume;

    @Element(name = "actualvolume")
    private Integer actualVolume;

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

}
