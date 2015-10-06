package com.bose.mdietger.soundtouchandroid.websockets;

import android.util.Log;

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

    private static final String PROTOCOL = "ws://";
    private static final String COLON = ":";
    private static final String PORT = "8080";

    /**
     * Instantiates a new WebSocketConnector.
     * @param serverUri the serverUri
     * @param draft the draft
     * @param headers the headers
     * @param timeout the connect timeout
     */
    public WebSocketConnector(URI serverUri , Draft draft , Map<String,String> headers , int timeout ) {
        super(serverUri, draft, headers, timeout);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        Log.i(TAG, "Opened");
    }

    @Override
    public void onMessage(String message) {
        Log.i(TAG, "Message " + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        Log.i(TAG, "Closed " + reason);
    }

    @Override
    public void onError(Exception ex) {
        Log.i(TAG, "Error " + ex.getMessage());
    }

    /**
     * Get absolute WebSocket url.
     * @param ip the ip address
     * @return String the absolute url
     */
    String getAbsoluteUrl(String ip) {
        return PROTOCOL + ip + COLON + PORT;
    }

}
