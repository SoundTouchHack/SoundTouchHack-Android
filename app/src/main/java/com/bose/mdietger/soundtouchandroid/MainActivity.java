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
import com.bose.mdietger.soundtouchandroid.callbacks.UpdateDeviceListCallback;
import com.bose.mdietger.soundtouchandroid.discovery.DeviceHandler;
import com.bose.mdietger.soundtouchandroid.discovery.SoundTouchDeviceHandler;
import com.bose.mdietger.soundtouchandroid.discovery.SoundTouchDiscoverer;

import com.bose.mdietger.soundtouchandroid.soundtouch.SoundTouch;

/**
 * MainActivity class. Root Activity for the Android SoundTouch App.
 */
public class MainActivity extends AppCompatActivity implements UpdateDeviceListCallback {

    private static final String TAG = "MainActivity";

    private SoundTouchDiscoverer discoverer;
    private DeviceHandler deviceHandler;
    private SoundTouchAdapter soundTouchAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        deviceHandler = new SoundTouchDeviceHandler(this);
        discoverer = new SoundTouchDiscoverer(getApplicationContext(), deviceHandler);
        discoverer.start();

        populateListView();
    }

    /**
     * Populates the ListView with devices from the deviceHandler.
     */
    protected void populateListView() {
        soundTouchAdapter = new SoundTouchAdapter(this, R.layout.soundtouch_list, deviceHandler.getDevices());

        ListView lvSoundTouchList = (ListView) findViewById(R.id.lvSoundTouchList);
        lvSoundTouchList.setAdapter(soundTouchAdapter);
        lvSoundTouchList.setOnItemClickListener(new ListViewHandler());
    }

    @Override
    public void update() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                soundTouchAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * ListViewHandler class.
     */
    private class ListViewHandler implements OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent soundtouchIntent = new Intent(getApplicationContext(), SoundTouchActivity.class);
            soundtouchIntent.putExtra("STObject", ((SoundTouchDeviceHandler) deviceHandler).getDevices().get(position));
            startActivity(soundtouchIntent);
        }

    }

}
