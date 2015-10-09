package com.bose.mdietger.soundtouchandroid.http.bass;

import com.bose.mdietger.soundtouchandroid.http.DefaultResponseListener;
import com.bose.mdietger.soundtouchandroid.http.XmlMarshaller;
import com.bose.mdietger.soundtouchandroid.http.volume.VolumeCallback;
import com.bose.mdietger.soundtouchandroid.http.volume.VolumeResponse;

/**
 * BassResponseListener class. This class is responsible of handling
 * GET Bass responses.
 */
public class BassResponseListener extends DefaultResponseListener {

    private static final String TAG = "BassResponseListener";

    private BassCallback callback;

    /**
     * Instantiates a new BassResponseListener.
     * @param callback the callback
     */
    public BassResponseListener(BassCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onResponse(String response) {
        BassResponse bass = XmlMarshaller.getInstance().unmarshall(BassResponse.class, response);
        callback.setBass(bass.getActualbass() + 9);
    }

}
