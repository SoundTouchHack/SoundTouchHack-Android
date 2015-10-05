package com.bose.mdietger.soundtouchandroid.http.volume;

import android.util.Log;

import com.bose.mdietger.soundtouchandroid.http.XmlMarshaller;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

/**
 * DoGetVolumeAsyncResponseHandler class. This class is responsible of handling
 * GET volume requests.
 */
public class DoGetVolumeAsyncHttpResponseHandler extends AsyncHttpResponseHandler {

    private static final String TAG = "DoGetResponseHandler";

    private VolumeCallback callback;

    /**
     * Instantiates a new DoGetVolumeAsyncHttpResponseHandler.
     * @param callback the callback
     */
    public DoGetVolumeAsyncHttpResponseHandler(VolumeCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        Log.d(TAG, "Success");
        callback.setVolume(XmlMarshaller.getInstance().unmarshall(VolumeResponse.class, new String(responseBody)));
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        Log.d(TAG, "Failure");
    }

}
