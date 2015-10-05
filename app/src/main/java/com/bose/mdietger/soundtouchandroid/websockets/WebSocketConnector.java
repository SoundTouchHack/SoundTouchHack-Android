package com.bose.mdietger.soundtouchandroid.websockets;

import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;


/**
 * Created by mdiet on 5/10/2015.
 */
public class WebSocketConnector extends WebSocketClient {

    public WebSocketConnector( URI serverUri , Draft draft , Map<String,String> headers , int connecttimeout ){
        super(serverUri, draft, headers, connecttimeout);
    }

    public WebSocketConnector(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    public WebSocketConnector(URI serverURI) {
        super(serverURI);
    }


    @Override
    public void onOpen(ServerHandshake handshakedata) {
        Log.i("Websocket", "Opened");
    }

    @Override
    public void onMessage(String message) {
        Log.i("Websocket", "Message " + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        Log.i("Websocket", "Closed " + reason);
    }

    @Override
    public void onError(Exception ex) {
        Log.i("Websocket", "Error " + ex.getMessage());
    }
}
