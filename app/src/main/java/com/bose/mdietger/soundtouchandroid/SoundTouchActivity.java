package com.bose.mdietger.soundtouchandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bose.mdietger.soundtouchandroid.http.DefaultResponseErrorListener;
import com.bose.mdietger.soundtouchandroid.http.DefaultResponseListener;
import com.bose.mdietger.soundtouchandroid.http.DeviceManager;
import com.bose.mdietger.soundtouchandroid.http.SoundTouchDeviceManager;
import com.bose.mdietger.soundtouchandroid.http.key.Key;
import com.bose.mdietger.soundtouchandroid.http.key.KeyState;
import com.bose.mdietger.soundtouchandroid.http.key.KeyValue;
import com.bose.mdietger.soundtouchandroid.http.volume.Volume;
import com.bose.mdietger.soundtouchandroid.http.volume.VolumeCallback;
import com.bose.mdietger.soundtouchandroid.http.volume.VolumeResponse;
import com.bose.mdietger.soundtouchandroid.http.volume.VolumeResponseListener;
import com.bose.mdietger.soundtouchandroid.soundtouch.SoundTouch;

/**
 * SoundTouchActivity class. Activity for controlling the SoundTouch device.
 */
public class SoundTouchActivity extends AppCompatActivity implements VolumeCallback {

    private static final String TAG = "SoundTouchActivity";

    private DeviceManager deviceManager;

    private TextView tvVolume;
    private SeekBar sbVolume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_touch);

        SoundTouch device = getIntent().getParcelableExtra("STObject");

        setTitle(device.getName());

        TextView tvIP = (TextView) findViewById(R.id.soundTouchDetailIP);
        tvIP.setText(device.getIp());

        tvVolume = (TextView) findViewById(R.id.tvVolumeValue);
        sbVolume = (SeekBar) findViewById(R.id.sbVolume);

        sbVolume.setOnSeekBarChangeListener(new VolumeChangeHandler());

        deviceManager = new SoundTouchDeviceManager(device);
        deviceManager.listenForMessages();
        deviceManager.getVolume(new VolumeResponseListener(this), new DefaultResponseErrorListener());
    }

    @Override
    public void setVolume(VolumeResponse volume) {
        tvVolume.setText(volume.getActualVolume().toString());
        sbVolume.setProgress(volume.getActualVolume());
    }

    @Override
    protected void onResume() {
        super.onResume();
        deviceManager.listenForMessages();
    }

    @Override
    public void onPause() {
        deviceManager.stopListenForMessages();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        deviceManager.stopListenForMessages();
        super.onDestroy();
    }

    private class VolumeChangeHandler implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            tvVolume.setText(String.valueOf(progress));
            Volume vol = new Volume(String.valueOf(progress));
            deviceManager.setVolume(vol, new DefaultResponseListener(), new DefaultResponseErrorListener());
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }

    /**
     * Click volumeDown
     * @param v the view
     */
    public void switchPower(View v) {
        Log.d(TAG, "Turn device on/off");

        Key key = new Key(KeyState.press.toString(),Key.SENDER.toString(), KeyValue.POWER.toString());


    }

}
