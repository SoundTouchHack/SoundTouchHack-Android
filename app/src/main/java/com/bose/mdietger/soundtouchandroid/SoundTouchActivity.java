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
import com.bose.mdietger.soundtouchandroid.websockets.DeviceUpdate;
import com.bose.mdietger.soundtouchandroid.websockets.DeviceUpdateCallback;

/**
 * SoundTouchActivity class. Activity for controlling the SoundTouch device.
 */
public class SoundTouchActivity extends AppCompatActivity implements DeviceUpdateCallback, VolumeCallback {

    private static final String TAG = "SoundTouchActivity";

    private DeviceManager deviceManager;

    private TextView tvVolume;
    private SeekBar sbVolume;
    private TextView tvNowPlaying;

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
        tvNowPlaying = (TextView) findViewById(R.id.tvVolumeValue);

        sbVolume.setOnSeekBarChangeListener(new VolumeChangeHandler());

        deviceManager = new SoundTouchDeviceManager(device);
        deviceManager.listenForMessages(this);
        deviceManager.getVolume(new VolumeResponseListener(this), new DefaultResponseErrorListener());
    }

    @Override
    public void onMessage(DeviceUpdate update) {
        if (update.getVolumeUpdated() != null) {
            setVolume(update.getVolumeUpdated().getVolume().getActualVolume());
        }
    }

    /**
     * http://stackoverflow.com/questions/5161951/android-only-the-original-thread-that-created-a-view-hierarchy-can-touch-its-vi
     * @param volume the volume
     */
    @Override
    public void setVolume(final Integer volume) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvVolume.setText(String.valueOf(volume));
                sbVolume.setProgress(volume);
            }
        });

    }

    /**
     * Click toggle the device power button
     * @param v the view
     */
    public void onPowerButtonPressed(View v) {
        Log.d(TAG, "Turn device on/off");
        deviceManager.togglePower(new DefaultResponseListener(), new DefaultResponseErrorListener());
    }

    public void onSourceButtonPressed(View v) {
        Log.d(TAG, "Source button pressed");
        deviceManager.toggleSource(new DefaultResponseListener(), new DefaultResponseErrorListener());
    }

    /**
     * One of the preset buttons has been pressend
     * @param v the view
     */
    public void onPresetButtonPressed(View v) {
        switch (v.getId()) {
            case R.id.btnPreset1:
                Log.d(TAG, "Click Preset 1");
                deviceManager.clickPreset(KeyValue.PRESET_1.toString(),new DefaultResponseListener(), new DefaultResponseErrorListener());
                break;
            case R.id.btnPreset2:
                Log.d(TAG, "Click Preset 1");
                deviceManager.clickPreset(KeyValue.PRESET_2.toString(),new DefaultResponseListener(), new DefaultResponseErrorListener());
                break;
            case R.id.btnPreset3:
                Log.d(TAG, "Click Preset 1");
                deviceManager.clickPreset(KeyValue.PRESET_3.toString(),new DefaultResponseListener(), new DefaultResponseErrorListener());
                break;
            case R.id.btnPreset4:
                Log.d(TAG, "Click Preset 1");
                deviceManager.clickPreset(KeyValue.PRESET_4.toString(),new DefaultResponseListener(), new DefaultResponseErrorListener());
                break;
            case R.id.btnPreset5:
                Log.d(TAG, "Click Preset 1");
                deviceManager.clickPreset(KeyValue.PRESET_5.toString(),new DefaultResponseListener(), new DefaultResponseErrorListener());
                break;
            case R.id.btnPreset6:
                Log.d(TAG, "Click Preset 1");
                deviceManager.clickPreset(KeyValue.PRESET_6.toString(),new DefaultResponseListener(), new DefaultResponseErrorListener());
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!deviceManager.isListening()){
            deviceManager.listenForMessages(this);
        }
    }

    @Override
    public void onPause() {
        deviceManager.stopListenForMessages();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if(deviceManager.isListening()){
            deviceManager.stopListenForMessages();
        }
        super.onDestroy();
    }

    private class VolumeChangeHandler implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (fromUser) {
                tvVolume.setText(String.valueOf(progress));
                Volume vol = new Volume(String.valueOf(progress));
                deviceManager.setVolume(vol, new DefaultResponseListener(), new DefaultResponseErrorListener());
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            tvVolume.setText(String.valueOf(seekBar.getProgress()));
            Volume vol = new Volume(String.valueOf(seekBar.getProgress()));
            deviceManager.setVolume(vol, new DefaultResponseListener(), new DefaultResponseErrorListener());
        }

    }

}
