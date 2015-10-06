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

    private static final Integer INCREMENT = new Integer(5);
    private Integer volume = new Integer(35);

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
        this.volume = volume.getActualVolume();
        tvVolume.setText(this.volume.toString());
        sbVolume.setProgress(this.volume);
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

    private class VolumeChangeHandler implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            tvVolume.setText(String.valueOf(progress));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }

}
