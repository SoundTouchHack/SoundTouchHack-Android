package com.bose.mdietger.soundtouchandroid.http;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * DefaultResponseListener class. This class returns default impl on
 * Response.Listener which simply logs.
 */
public class DefaultResponseListener implements Response.Listener<String> {

    private static final String TAG = "ResponseListener";

    @Override
    public void onResponse(String response) {
        Log.d(TAG, "response: " + response);
    }

}
