package com.bose.mdietger.soundtouchandroid;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * AppController class. Singleton Application Controller class that holds Volley's
 * requestQueue's for making HTTP requests.
 */
public class AppController extends Application {

    public static final String TAG = "AppController";

    private RequestQueue mRequestQueue;

    private static AppController instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    /**
     * Get AppController isntance.
     * @return AppController the AppController
     */
    public static synchronized AppController getInstance() {
        return instance;
    }

    /**
     * @return RequestQueue the RequestQueue
     */
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    /**
     * Add request to queue.
     * @param request the request
     * @param tag the tag
     * @param <T> the type
     */
    public <T> void addToRequestQueue(Request<T> request, String tag) {
        request.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(request);
    }

    /**
     * Add request to queue.
     * @param request the request
     * @param <T> the type
     */
    public <T> void addToRequestQueue(Request<T> request) {
        request.setTag(TAG);
        getRequestQueue().add(request);
    }

    /**
     * Cancel all pending requests on queue.
     * @param tag the tag
     */
    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

}
