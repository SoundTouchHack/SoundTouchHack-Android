package com.bose.mdietger.soundtouchandroid.http;

import android.util.Log;

import com.android.volley.Response;
import com.bose.mdietger.soundtouchandroid.http.volume.Volume;
import com.bose.mdietger.soundtouchandroid.soundtouch.SoundTouch;

/**
 * SoundTouchDeviceManager class. This class is reponsible for interactions
 * with the SoundTouch devices.
 */
public class SoundTouchDeviceManager extends AbstractDeviceManager<SoundTouch> implements DeviceManager {

    private static final String TAG = "SoundTouchDeviceManager";

    private static final String PROTOCOL = "http://";
    private static final String COLON = ":";
    private static final String PORT = "8090";

    /**
     * Instantiates a new SoundTouchDeviceManager.
     * @param device the device to manage
     */
    public SoundTouchDeviceManager(SoundTouch device) {
        super(device);
    }

    @Override
    protected String getAbsoluteUrl(String path) {
        return PROTOCOL + device.getIp() + COLON + PORT + path;
    }

    // ----------------------------------------------------------------------------------------------- VOLUME

    private static final String VOLUME = "/volume";

    @Override
    public void getVolume(Response.Listener responseListener, Response.ErrorListener errorListener) {
        Log.d(TAG, "getVolume");
        doGet(VOLUME, responseListener, errorListener);
    }

    @Override
    public void setVolume(Volume volumeLevel, Response.Listener responseListener, Response.ErrorListener errorListener) {
        Log.d(TAG, "setVolume");
        String dataXml = XmlMarshaller.getInstance().marshall(volumeLevel);
        doPost(VOLUME, dataXml, responseListener, errorListener);
    }

}
