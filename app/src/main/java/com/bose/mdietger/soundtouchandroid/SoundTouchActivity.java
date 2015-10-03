package com.bose.mdietger.soundtouchandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bose.mdietger.soundtouchandroid.http.SoundTouchHTTP;
import com.bose.mdietger.soundtouchandroid.soundtouch.SoundTouch;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class SoundTouchActivity extends AppCompatActivity {

    private static final String TAG = "SoundTouchActivity";

    private SoundTouchHTTP soundTouchHTTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_touch);

        SoundTouch STObject = getIntent().getParcelableExtra("STObject");

        TextView STNaam = (TextView) findViewById(R.id.soundTouchDetailName);
        TextView STIP = (TextView) findViewById(R.id.soundTouchDetailIP);

        STNaam.setText(STObject.get_name());
        STIP.setText(STObject.get_ip());

        soundTouchHTTP = new SoundTouchHTTP(STObject.get_ip());
    }

    public void volumeUp(View v) {
        Log.d("STApp", "Volume Up");
        soundTouchHTTP.post("/volume", null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.d("STApp", "Success");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("STApp", "Failed");
            }

        });
    }

    public void volumeDown(View v) {
        Log.d("STApp", "Volume Down");
    }

}
