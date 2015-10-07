package com.bose.mdietger.soundtouchandroid.websockets;

import com.bose.mdietger.soundtouchandroid.http.volume.VolumeResponse;

/**
 * DeviceUdpateCallback interface. This callback interface is used to return
 * message callback to the activity.
 */
public interface DeviceUpdateCallback {

    /**
     * @param update the update
     */
    void onMessage(DeviceUpdate update);

}
