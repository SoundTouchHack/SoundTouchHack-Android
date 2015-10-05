package com.bose.mdietger.soundtouchandroid.http;

import com.bose.mdietger.soundtouchandroid.http.volume.Volume;
import com.bose.mdietger.soundtouchandroid.http.volume.VolumeResponse;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * DeviceManager interface. The DeviceManager defines all possible interactions
 * with the device.
 */
public interface DeviceManager {

    /**
     * Get the current volume level.
     * @param responseHandler the responseHandler
     */
    void getVolume(AsyncHttpResponseHandler responseHandler);

    /**
     * Sets volume to volume level.
     * @param volumeLevel the volume level to set
     * @param responseHandler the responseHandler
     */
    void setVolume(Volume volumeLevel, AsyncHttpResponseHandler responseHandler);

}
