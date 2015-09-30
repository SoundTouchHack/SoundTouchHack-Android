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
import com.bose.mdietger.soundtouchandroid.discovery.SoundTouchDiscoverer;
import com.bose.mdietger.soundtouchandroid.soundtouch.SoundTouch;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<SoundTouch> soundTouchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        * Dummy data
        */
        SoundTouch dummy1 = null;
        SoundTouch dummy2 = null;

        try{
            dummy1 = new SoundTouch("Soundtouch 1", "192.168.0.174");
            dummy2 = new SoundTouch("Soundtouch 2", "127.0.0.1");
        }catch (Exception e){
            Log.d("Inet", e.getMessage());
        }

        soundTouchList = new ArrayList<SoundTouch>();
        soundTouchList.add(dummy1);
        soundTouchList.add(dummy2);

        /*
        * End dummy data
        */

        ListView lvSoundTouchList = (ListView) findViewById(R.id.lvSoundTouchList);
        SoundTouchAdapter soundTouchAdapter = new SoundTouchAdapter(this, R.layout.soundtouch_list, soundTouchList);
        lvSoundTouchList.setAdapter(soundTouchAdapter);
        lvSoundTouchList.setOnItemClickListener(new ListViewHandler());

        new SoundTouchDiscoverer(getApplicationContext());
    }

    private class ListViewHandler implements OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent soundtouchIntent = new Intent(getApplicationContext(), SoundTouchActivity.class);
            soundtouchIntent.putExtra("STObject", soundTouchList.get(position));
            startActivity(soundtouchIntent);
        }
    }
}
