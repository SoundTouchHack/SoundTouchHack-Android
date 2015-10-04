package com.bose.mdietger.soundtouchandroid.http;

import com.bose.mdietger.soundtouchandroid.http.volume.Volume;

/**
 * DeviceManager interface. The DeviceManager defines all possible interactions
 * with the device.
 */
public interface DeviceManager {

    /**
     * Sets volume to volume level.
     * @param volumeLevel the volume level to set
     */
    void setVolume(Volume volumeLevel);

}
