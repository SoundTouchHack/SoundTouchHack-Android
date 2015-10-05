package com.bose.mdietger.soundtouchandroid.http;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * DefaultResponseErrorListener class. This class returns default impl
 * on Response.ErrorListener which simply logs.
 */
public class DefaultResponseErrorListener implements Response.ErrorListener {

    private static final String TAG = "ErrorListener";

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e(TAG, "error");
    }

}
