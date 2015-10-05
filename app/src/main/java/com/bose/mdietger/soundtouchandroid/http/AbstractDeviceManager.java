package com.bose.mdietger.soundtouchandroid.http;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.bose.mdietger.soundtouchandroid.AppController;

/**
 * AbstractDeviceManager class. This class is reponsible for interactions
 * with the devices via HTTP requests.
 */
public abstract class AbstractDeviceManager<T> implements DeviceManager {

    protected T device;

    /**
     * Instantiates a new AbstractDeviceManager.
     * @param device the device to manage
     */
    public AbstractDeviceManager(T device) {
        this.device = device;
    }

    /**
     * Do GET to url with path.
     * @param path the path
     * @param responseListener the responseListener
     * @param errorListener the errorListener
     * @return String the response
     */
    protected void doGet(String path, Response.Listener responseListener, Response.ErrorListener errorListener) {
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
    protected void doPost(String path, final String content, Response.Listener responseListener, Response.ErrorListener errorListener) {
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
    protected abstract String getAbsoluteUrl(String path);

}
