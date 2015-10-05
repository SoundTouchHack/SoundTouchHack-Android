package com.bose.mdietger.soundtouchandroid.http;

import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

/**
 * DoGetAsyncResponseHandler class. This class is responsible of handling
 * default GET requests.
 */
public class DoGetAsyncHttpResponseHandler extends AsyncHttpResponseHandler {

    private static final String TAG = "DoGetResponseHandler";

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        Log.d(TAG, "Success");
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        Log.d(TAG, "Failure");
    }

}
