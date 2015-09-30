package com.bose.mdietger.soundtouchandroid.http;

import com.loopj.android.http.*;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

/**
 * Created by mdiet on 30/09/2015.
 */
public class SoundTouchHTTP {

    private static String BASE_URL;
    private static final String BASE_PORT = "8090";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public SoundTouchHTTP(String url){
        BASE_URL = "http://"+url;
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {

        StringEntity data = null;
        try {
            data = new StringEntity("<volume>40</volume>");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return;
        }
        data.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/xml"));

        client.post(null, getAbsoluteUrl(url), data, "application/xml", responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + ":" + BASE_PORT + relativeUrl;
    }
}
