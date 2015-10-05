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
import com.bose.mdietger.soundtouchandroid.http.volume.Volume;
import com.bose.mdietger.soundtouchandroid.soundtouch.SoundTouch;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * MainActivity class. Root Activity for the Android SoundTouch App.
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

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

        SoundTouch st1 = new SoundTouch("Hallo", "192.168.0.123");
        SoundTouch st2 = new SoundTouch("Hallo", "192.168.0.123");

        if(st1.equals(st2)){
            Log.d(TAG, "Whoo");
        }else{
            Log.d(TAG, "Booh");
        }

        populateListView();
    }

    /**
     * Click Refresh.
     * @param view the View
     */
    public void refresh(View view) {
        Log.d(TAG, "Refresh list of devices");

        populateListView();
    }

    /**
     * Populates the ListView with devices from the deviceHandler.
     */
    protected void populateListView() {
        SoundTouchAdapter soundTouchAdapter = new SoundTouchAdapter(this, R.layout.soundtouch_list, deviceHandler.getDevices());

        ListView lvSoundTouchList = (ListView) findViewById(R.id.lvSoundTouchList);
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
            soundtouchIntent.putExtra("STObject", ((SoundTouchDeviceHandler) deviceHandler).getDevices().get(position));
            startActivity(soundtouchIntent);
        }

    }

}
