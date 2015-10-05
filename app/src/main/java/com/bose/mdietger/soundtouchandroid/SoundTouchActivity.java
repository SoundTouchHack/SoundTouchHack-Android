package com.bose.mdietger.soundtouchandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bose.mdietger.soundtouchandroid.http.DefaultResponseErrorListener;
import com.bose.mdietger.soundtouchandroid.http.DefaultResponseListener;
import com.bose.mdietger.soundtouchandroid.http.DeviceManager;
import com.bose.mdietger.soundtouchandroid.http.SoundTouchDeviceManager;
import com.bose.mdietger.soundtouchandroid.http.volume.Volume;
import com.bose.mdietger.soundtouchandroid.http.volume.VolumeCallback;
import com.bose.mdietger.soundtouchandroid.http.volume.VolumeResponse;
import com.bose.mdietger.soundtouchandroid.http.volume.VolumeResponseListener;
import com.bose.mdietger.soundtouchandroid.soundtouch.SoundTouch;
import com.bose.mdietger.soundtouchandroid.websockets.WebSocketConnector;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_10;
import org.java_websocket.drafts.Draft_17;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * SoundTouchActivity class. Activity for controlling the SoundTouch device.
 */
public class SoundTouchActivity extends AppCompatActivity implements VolumeCallback {

    private static final String TAG = "SoundTouchActivity";

    private DeviceManager deviceManager;
    private WebSocketClient wsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_touch);

        SoundTouch device = getIntent().getParcelableExtra("STObject");

        TextView tvName = (TextView) findViewById(R.id.soundTouchDetailName);
        tvName.setText(device.getName());

        TextView tvIP = (TextView) findViewById(R.id.soundTouchDetailIP);
        tvIP.setText(device.getIp());

        Map<String,String> headers = new HashMap<String, String>();
        headers.put("Sec-WebSocket-Protocol","gabbo");

        try{
            wsc = new WebSocketConnector(new URI("ws://192.168.0.168:8080"), new Draft_17(), headers, 5000);
            wsc.connect();
        }catch(Exception e){
            Log.d(TAG, e.getMessage());
        }


        deviceManager = new SoundTouchDeviceManager(device);
        deviceManager.getVolume(new VolumeResponseListener(this), new DefaultResponseErrorListener());
    }

    // ----------------------------------------------------------------------------------------------- VOLUME

    private static final Integer INCREMENT = new Integer(5);

    private Integer volume = new Integer(35);

    @Override
    public void setVolume(VolumeResponse volume) {
        this.volume = volume.getActualVolume();
    }

    /**
     * Click volumeUp.
     * @param v the view
     */
    public void volumeUp(View v) {
        Log.d(TAG, "Volume Up");

        volume = volume + INCREMENT;
        Volume vol = new Volume(String.valueOf(volume));
        deviceManager.setVolume(vol, new DefaultResponseListener(), new DefaultResponseErrorListener());
    }

    /**
     * Click volumeDown
     * @param v the view
     */
    public void volumeDown(View v) {
        Log.d(TAG, "Volume Down");

        volume = volume - INCREMENT;
        Volume vol = new Volume(String.valueOf(volume));
        deviceManager.setVolume(vol, new DefaultResponseListener(), new DefaultResponseErrorListener());
    }

}
