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
import com.bose.mdietger.soundtouchandroid.http.bass.Bass;
import com.bose.mdietger.soundtouchandroid.http.bass.BassCallback;
import com.bose.mdietger.soundtouchandroid.http.bass.BassResponseListener;
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
public class SoundTouchActivity extends AppCompatActivity implements DeviceUpdateCallback, VolumeCallback, BassCallback{

    private static final String TAG = "SoundTouchActivity";

    private DeviceManager deviceManager;

    private TextView tvVolume;
    private TextView tvBass;
    private SeekBar sbVolume;
    private SeekBar sbBass;
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
        tvBass = (TextView) findViewById(R.id.tvBass);
        sbVolume = (SeekBar) findViewById(R.id.sbVolume);
        sbBass = (SeekBar) findViewById(R.id.sbBass);
        tvNowPlaying = (TextView) findViewById(R.id.tvNowPlaying);

        sbVolume.setOnSeekBarChangeListener(new VolumeChangeHandler());
        sbBass.setOnSeekBarChangeListener(new BassChangeHandler());

        deviceManager = new SoundTouchDeviceManager(device);
        deviceManager.listenForMessages(this);
        deviceManager.getBass(new BassResponseListener(this), new DefaultResponseErrorListener());
        deviceManager.getVolume(new VolumeResponseListener(this), new DefaultResponseErrorListener());
    }

    @Override
    public void onMessage(DeviceUpdate update) {
        if (update.getVolumeUpdated() != null) {
            setVolume(update.getVolumeUpdated().getVolume().getActualVolume());
        }
        if (update.getNowSelectionUpdated() != null) {
            setNowPlaying(update.getNowSelectionUpdated().getPreset().getContentItem().getItemName().getValue());
        }
    }

    public void setNowPlaying(final String nowPlaying) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvNowPlaying.setText(nowPlaying);
            }
        });
    }

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

    @Override
    public void setBass(final Integer bass) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvBass.setText(String.valueOf(bass));
                sbBass.setProgress(bass + 9);
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

    /**
     * Click toggle the source.
     * @param v the view
     */
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

    /**
     * VolumeChangeHandler class. This class is used as handler
     * with the Volume seekbar.
     */
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

    /**
     * BassChangeHandler class. This class is used as handler
     * with the Bass seekbar.
     */
    private class BassChangeHandler implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (fromUser) {
                tvBass.setText(String.valueOf(progress - 9));
                Bass bass = new Bass(String.valueOf(progress - 9));
                deviceManager.setBass(bass, new DefaultResponseListener(), new DefaultResponseErrorListener());
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            tvBass.setText(String.valueOf(seekBar.getProgress() - 9));
            Bass bass = new Bass(String.valueOf(seekBar.getProgress() - 9));
            deviceManager.setBass(bass, new DefaultResponseListener(), new DefaultResponseErrorListener());
        }

    }

}
