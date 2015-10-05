package com.bose.mdietger.soundtouchandroid.http;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bose.mdietger.soundtouchandroid.AppController;
import com.bose.mdietger.soundtouchandroid.http.volume.Volume;
import com.bose.mdietger.soundtouchandroid.soundtouch.SoundTouch;

/**
 * SoundTouchDeviceManager class. This class is reponsible for interactions
 * with the SoundTouch devices.
 */
public class SoundTouchDeviceManager implements DeviceManager {

    private static final String TAG = "SoundTouchDeviceManager";
    private static final String PROTOCOL = "http://";
    private static final String COLON = ":";
    private static final String PORT = "8090";

    private SoundTouch device;

    /**
     * Instantiates a new SoundTouchDeviceManager.
     * @param device the device to manage
     */
    public SoundTouchDeviceManager(SoundTouch device) {
        this.device = device;
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

    /**
     * Do GET to url with path.
     * @param path the path
     * @param responseListener the responseListener
     * @param errorListener the errorListener
     * @return String the response
     */
    void doGet(String path, Response.Listener responseListener, Response.ErrorListener errorListener) {
        String  tag_string_req = "string_req";
        StringRequest strReq = new StringRequest(Request.Method.GET, getAbsoluteUrl(path), responseListener, errorListener);
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    /**
     * Do POST to url with path.
     * @param path the path
     * @param content the content
     * @param responseListener the responseListener
     * @param errorListener the errorListener
     */
    void doPost(String path, final String content, Response.Listener responseListener, Response.ErrorListener errorListener) {
        String  tag_string_req = "string_req";
        StringRequest strReq = new StringRequest(Request.Method.POST, getAbsoluteUrl(path), responseListener, errorListener) {

            @Override
            public byte[] getBody() throws AuthFailureError {
                return content.getBytes();
            }

        };
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    /**
     * Returns the absolute url.
     * @param path the path
     * @return String the absolute url
     */
    String getAbsoluteUrl(String path) {
        return PROTOCOL + device.getIp() + COLON + PORT + path;
    }

}
