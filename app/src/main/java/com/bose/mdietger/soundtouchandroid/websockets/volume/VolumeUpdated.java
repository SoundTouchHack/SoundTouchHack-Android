package com.bose.mdietger.soundtouchandroid.websockets.volume;

import org.simpleframework.xml.Element;

/**
 * NowSelectionUpdated class. Part of DeviceUpdated class. Contains updated
 * Volume info.
 */
public class VolumeUpdated {

    @Element(name = "volume")
    private Volume volume;

    /**
     * @return the volume
     */
    public Volume getVolume() {
        return volume;
    }

    /**
     * @param volume the volume to set
     */
    public void setVolume(Volume volume) {
        this.volume = volume;
    }

}
