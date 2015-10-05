package com.bose.mdietger.soundtouchandroid.http.volume;

/**
 * VolumeCallback interface. This interface is used to create callbacks
 * from the AsyncHttpResponseHandlers.
 */
public interface VolumeCallback {

    /**
     * @param volume the volume
     */
    void setVolume(VolumeResponse volume);

}
