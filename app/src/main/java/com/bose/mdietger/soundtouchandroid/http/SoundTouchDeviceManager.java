package com.bose.mdietger.soundtouchandroid.http;

import android.util.Log;

import com.android.volley.Response;
import com.bose.mdietger.soundtouchandroid.http.key.Key;
import com.bose.mdietger.soundtouchandroid.http.key.KeyState;
import com.bose.mdietger.soundtouchandroid.http.key.KeyValue;
import com.bose.mdietger.soundtouchandroid.http.volume.Volume;
import com.bose.mdietger.soundtouchandroid.soundtouch.SoundTouch;
import com.bose.mdietger.soundtouchandroid.websockets.DeviceUpdateCallback;
import com.bose.mdietger.soundtouchandroid.websockets.WebSocketConnector;

import org.java_websocket.WebSocket;
import org.java_websocket.drafts.Draft_17;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * SoundTouchDeviceManager class. This class is reponsible for interactions
 * with the SoundTouch devices.
 */
public class SoundTouchDeviceManager extends AbstractDeviceManager<SoundTouch> implements DeviceManager {

    private static final String TAG = "SoundTouchDeviceManager";

    private static final String HTTP_PROTOCOL = "http://";
    private static final String HTTP_PORT = "8090";

    private static final String WS_PROTOCOL = "ws://";
    private static final String WS_PORT = "8080";

    private static final String COLON = ":";

    private WebSocketConnector connector;


    /**
     * Instantiates a new SoundTouchDeviceManager.
     * @param device the device to manage
     */
    public SoundTouchDeviceManager(SoundTouch device) {
        super(device);
    }

    @Override
    protected String getAbsoluteUrl(String path) {
        return HTTP_PROTOCOL + device.getIp() + COLON + HTTP_PORT + path;
    }

    // ----------------------------------------------------------------------------------------------- MESSAGES (WEBSOCKETS)

    @Override
    public void listenForMessages(DeviceUpdateCallback callback) {
        try {
            URI uri = new URI(WS_PROTOCOL + device.getIp() + COLON + WS_PORT);

            Map<String,String> headers = new HashMap<String, String>();
            headers.put("Sec-WebSocket-Protocol", "gabbo");

            connector = new WebSocketConnector(uri, new Draft_17(), headers, 5000, callback);
            connector.connect();

            Log.d(TAG, "Listening for messages");
        } catch(Exception e) {
            Log.e(TAG, "Error listening for messages: " + e.getMessage());
        }
    }

    @Override
    public boolean isListening() {
        if(connector.getReadyState() == WebSocket.READYSTATE.CONNECTING
                || connector.getReadyState() == WebSocket.READYSTATE.OPEN
                || connector.getReadyState() == WebSocket.READYSTATE.NOT_YET_CONNECTED){
            return true;
        }
        return false;
    }

    @Override
    public void stopListenForMessages() {
        if(connector == null) {
            return;
        }
        connector.close();
    }

    // ----------------------------------------------------------------------------------------------- VOLUME

    private static final String VOLUME = "/volume";
    private static final String KEY = "/key";

    @Override
    public void getVolume(Response.Listener responseListener, Response.ErrorListener errorListener) {
        Log.d(TAG, "getVolume");
        doGet(VOLUME, responseListener, errorListener);
    }

    @Override
    public void setVolume(Volume volumeLevel, Response.Listener responseListener, Response.ErrorListener errorListener) {
        Log.d(TAG, "setVolume");
        String dataXml = XmlMarshaller.getInstance().marshall(volumeLevel);
        doPost(VOLUME, dataXml, responseListener, errorListener);
    }

    // ----------------------------------------------------------------------------------------------- KEY

    @Override
    public void togglePower(Response.Listener responseListener, Response.ErrorListener errorListener) {
        Log.d(TAG, "Power device off");
        deviceKeyPress(KeyValue.POWER.toString(), responseListener, errorListener);
    }

    @Override
    public void toggleSource(Response.Listener responseListener, Response.ErrorListener errorListener) {
        Log.d(TAG, "Toggle source button");
        deviceKeyPress(KeyValue.AUX_INPUT.toString(), responseListener, errorListener);
    }

    @Override
    public void clickPreset(String presetNumber, Response.Listener responseListener, Response.ErrorListener errorListener) {
        Log.d(TAG, "Click Preset button: " + presetNumber);
        deviceKeyPress(presetNumber, responseListener, errorListener);
    }

    /**
     * A device key press.
     * @param keyValue The value of the Key
     * @param responseListener the responseListener
     * @param errorListener the errorListener
     */
    void deviceKeyPress(String keyValue, Response.Listener responseListener, Response.ErrorListener errorListener) {
        Key keyPress = new Key(KeyState.press.toString(), Key.SENDER.toString(),keyValue );
        Key keyRelease = new Key(KeyState.release.toString(), Key.SENDER.toString(), keyValue);
        // press
        String dataXml = XmlMarshaller.getInstance().marshall(keyPress);
        doPost(KEY, dataXml, responseListener, errorListener);
        // release
        dataXml = XmlMarshaller.getInstance().marshall(keyRelease);
        doPost(KEY, dataXml, responseListener, errorListener);
    }

}
