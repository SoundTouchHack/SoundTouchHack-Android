package com.bose.mdietger.soundtouchandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.bose.mdietger.soundtouchandroid.adapters.SoundTouchAdapter;
import com.bose.mdietger.soundtouchandroid.discovery.DeviceHandler;
import com.bose.mdietger.soundtouchandroid.discovery.SoundTouchDeviceHandler;
import com.bose.mdietger.soundtouchandroid.discovery.SoundTouchDiscoverer;
import com.bose.mdietger.soundtouchandroid.soundtouch.SoundTouch;

import java.util.ArrayList;
import java.util.List;

/**
 * MainActivity class. Root Activity for the Android SoundTouch App.
 */
public class MainActivity extends AppCompatActivity {

    private SoundTouchDiscoverer discoverer;
    private DeviceHandler deviceHandler;

    private List<SoundTouch> devices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        deviceHandler = new SoundTouchDeviceHandler();
        discoverer = new SoundTouchDiscoverer(getApplicationContext(), deviceHandler);
        discoverer.start();

        devices = new ArrayList<>();
        devices.add(new SoundTouch("Name", "192.168.1.3"));

        ListView lvSoundTouchList = (ListView) findViewById(R.id.lvSoundTouchList);
        // SoundTouchAdapter soundTouchAdapter = new SoundTouchAdapter(this, R.layout.soundtouch_list, deviceHandler.getDevices());
        SoundTouchAdapter soundTouchAdapter = new SoundTouchAdapter(this, R.layout.soundtouch_list, devices);
        lvSoundTouchList.setAdapter(soundTouchAdapter);
        lvSoundTouchList.setOnItemClickListener(new ListViewHandler());

    }

    /**
     * ListViewHandler class.
     */
    private class ListViewHandler implements OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent soundtouchIntent = new Intent(getApplicationContext(), SoundTouchActivity.class);
            // soundtouchIntent.putExtra("STObject", ((SoundTouchDeviceHandler) deviceHandler).getDevices().get(position));
            soundtouchIntent.putExtra("STObject", devices.get(position));
            startActivity(soundtouchIntent);
        }

    }

}
