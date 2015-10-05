package com.bose.mdietger.soundtouchandroid.http;

import android.util.Log;

import com.bose.mdietger.soundtouchandroid.http.volume.Volume;
import com.bose.mdietger.soundtouchandroid.soundtouch.SoundTouch;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

/**
 * SoundTouchDeviceManager class. This class is reponsible for interactions
 * with the SoundTouch devices.
 */
public class SoundTouchDeviceManager implements DeviceManager {

    private static final String TAG = "SoundTouchDeviceManager";
    private static final String PORT = "8090";

    private SoundTouch device;

    private AsyncHttpClient client = new AsyncHttpClient();

    /**
     * Instantiates a new SoundTouchDeviceManager.
     * @param device the device to manage
     */
    public SoundTouchDeviceManager(SoundTouch device) {
        this.device = device;
        this.client = new AsyncHttpClient();
    }

    @Override
    public void getVolume(AsyncHttpResponseHandler responseHandler) {
        doGet("/volume", responseHandler);
    }

    @Override
    public void setVolume(Volume volumeLevel, AsyncHttpResponseHandler responseHandler) {
        String dataXml = XmlMarshaller.getInstance().marshall(volumeLevel);
        doPost("/volume", dataXml, responseHandler);
    }

    /**
     * Do GET to url with path.
     * @param path the path
     * @param responseHandler the responseHandler
     * @return String the response
     */
    void doGet(String path, AsyncHttpResponseHandler responseHandler) {
        client.get(null, getAbsoluteUrl(path), null, "application/xml", responseHandler);
    }

    /**
     * Do POST to url with path.
     * @param path the path
     * @param content the content
     * @param responseHandler the responseHandler
     */
    void doPost(String path, String content, AsyncHttpResponseHandler responseHandler) {
        StringEntity data = null;
        try {
            data = new StringEntity(content);
            data.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/xml"));
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "Error occured: " + e.getMessage());
            return;
        }

        client.post(null, getAbsoluteUrl(path), data, "application/xml", responseHandler);
    }

    /**
     * Returns the absolute url.
     * @param path the path
     * @return String the absolute url
     */
    String getAbsoluteUrl(String path) {
        return "http://" + device.get_ip() + ":" + PORT + path;
    }

}
