package com.bose.mdietger.soundtouchandroid.http;

import android.app.ProgressDialog;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bose.mdietger.soundtouchandroid.AppController;
import com.bose.mdietger.soundtouchandroid.http.volume.Volume;
import com.bose.mdietger.soundtouchandroid.soundtouch.SoundTouch;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

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
        String  tag_string_req = "string_req";

        StringRequest strReq = new StringRequest(Request.Method.GET,
                getAbsoluteUrl(path), new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "error");
            }
        });

        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    /**
     * Do POST to url with path.
     * @param path the path
     * @param content the content
     * @param responseHandler the responseHandler
     */
    void doPost(String path, final String content, AsyncHttpResponseHandler responseHandler) {
        String  tag_string_req = "string_req";

        StringRequest strReq = new StringRequest(Request.Method.POST,
                getAbsoluteUrl(path), new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "error");
            }
        }) {

            @Override
            public byte[] getBody() throws AuthFailureError {
                return content.getBytes();
            }
        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
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
