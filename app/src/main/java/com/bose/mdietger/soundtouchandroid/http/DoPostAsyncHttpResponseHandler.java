package com.bose.mdietger.soundtouchandroid.http;

import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

/**
 * DoPostAsyncResponseHandler class. This class is responsible of handling
 * default POST requests.
 */
public class DoPostAsyncHttpResponseHandler extends AsyncHttpResponseHandler {

    private static final String TAG = "DoPostResponseHandler";

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        Log.d(TAG, "Success");
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        Log.d(TAG, "Failure");
    }

}
