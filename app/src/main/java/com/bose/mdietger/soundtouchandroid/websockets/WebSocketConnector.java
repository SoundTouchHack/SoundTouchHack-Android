package com.bose.mdietger.soundtouchandroid.websockets;

import android.util.Log;

import com.bose.mdietger.soundtouchandroid.http.XmlMarshaller;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;


/**
 * WebSocketConnector class. This class is used to connect and listen
 * for WebSocket messages.
 */
public class WebSocketConnector extends WebSocketClient {

    private static final String TAG = "WebSocketConnector";

    private DeviceUpdateCallback callback;

    /**
     * Instantiates a new WebSocketConnector.
     * @param serverUri the serverUri
     * @param draft the draft
     * @param headers the headers
     * @param timeout the connect timeout
     * @param callback the deviceUpdateCallback
     */
    public WebSocketConnector(URI serverUri , Draft draft , Map<String,String> headers , int timeout, DeviceUpdateCallback callback ) {
        super(serverUri, draft, headers, timeout);
        this.callback = callback;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        Log.i(TAG, "Opened");
    }

    @Override
    public void onMessage(String message) {
        Log.i(TAG, "Message " + message);

        DeviceUpdate deviceUpdate = XmlMarshaller.getInstance().unmarshall(DeviceUpdate.class, message);
        callback.onMessage(deviceUpdate);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        Log.i(TAG, "Closed " + reason);
    }

    @Override
    public void onError(Exception ex) {
        Log.i(TAG, "Error " + ex.getMessage());
    }

}
