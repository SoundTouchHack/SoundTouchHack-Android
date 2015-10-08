package com.bose.mdietger.soundtouchandroid.http;

import com.android.volley.Response;
import com.bose.mdietger.soundtouchandroid.http.key.Key;
import com.bose.mdietger.soundtouchandroid.http.volume.Volume;
import com.bose.mdietger.soundtouchandroid.websockets.DeviceUpdateCallback;

/**
 * DeviceManager interface. The DeviceManager defines all possible interactions
 * with the device.
 */
public interface DeviceManager {

    /**
     * Start listening for messages from device.
     * @param callback the deviceUpdateCallback
     */
    void listenForMessages(DeviceUpdateCallback callback);

    /**
     * Check if device is listening
     * @return true if listening, false if not
     */
    boolean isListening();

    /**
     * Stop listening for message from device.
     */
    void stopListenForMessages();

    /**
     * Get the current volume level.
     * @param responseListener the responseListener
     * @param errorListener the errorListener
     */
    void getVolume(Response.Listener responseListener, Response.ErrorListener errorListener);

    /**
     * Sets volume to volume level.
     * @param volumeLevel the volume level to set
     * @param responseListener the responseListener
     * @param errorListener the errorListener
     */
    void setVolume(Volume volumeLevel, Response.Listener responseListener, Response.ErrorListener errorListener);


    /**
     * Toggle the power button
     * @param responseListener the responseListener
     * @param errorListener the errorListener
     */
    void togglePower(Response.Listener responseListener, Response.ErrorListener errorListener);

    /**
     * Toggle the source button
     * @param responseListener the responseListener
     * @param errorListener the errorListener
     */
    void toggleSource(Response.Listener responseListener, Response.ErrorListener errorListener);

    /**
     * Click the preset 1 button
     * @param presetNumber the preset button
     * @param responseListener the responseListener
     * @param errorListener the errorListener
     */
    void clickPreset(String presetNumber, Response.Listener responseListener, Response.ErrorListener errorListener);

}
