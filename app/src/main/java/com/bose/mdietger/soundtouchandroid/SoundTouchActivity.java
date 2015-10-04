package com.bose.mdietger.soundtouchandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bose.mdietger.soundtouchandroid.http.DeviceManager;
import com.bose.mdietger.soundtouchandroid.http.SoundTouchDeviceManager;
import com.bose.mdietger.soundtouchandroid.http.volume.Volume;
import com.bose.mdietger.soundtouchandroid.soundtouch.SoundTouch;

public class SoundTouchActivity extends AppCompatActivity {

    private static final String TAG = "SoundTouchActivity";

    private DeviceManager deviceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_touch);

        SoundTouch device = getIntent().getParcelableExtra("STObject");

        TextView STNaam = (TextView) findViewById(R.id.soundTouchDetailName);
        TextView STIP = (TextView) findViewById(R.id.soundTouchDetailIP);

        STNaam.setText(device.get_name());
        STIP.setText(device.get_ip());

        deviceManager = new SoundTouchDeviceManager(device);
    }

    public void volumeUp(View v) {
        Log.d(TAG, "Volume Up");
        Volume volume = new Volume("40");
        deviceManager.setVolume(volume);
    }

    public void volumeDown(View v) {
        Log.d(TAG, "Volume Down");
        Volume volume = new Volume("30");
        deviceManager.setVolume(volume);
    }

}
