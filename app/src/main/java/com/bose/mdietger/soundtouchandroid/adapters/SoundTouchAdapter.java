package com.bose.mdietger.soundtouchandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bose.mdietger.soundtouchandroid.R;
import com.bose.mdietger.soundtouchandroid.soundtouch.SoundTouch;

import java.util.List;

/**
 * SoundTouchAdapter class. This class is used as adapter for the
 * listView.
 */
public class SoundTouchAdapter extends ArrayAdapter<SoundTouch> {

    /**
     * Instantiates a new SoundTouchAdapter.
     * @param context the context
     * @param textViewResourceId the textViewResourceId
     */
    public SoundTouchAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    /**
     * Instantiates a new SoundTouchAdapter.
     * @param context the context
     * @param resource the resource
     * @param items the items
     */
    public SoundTouchAdapter(Context context, int resource, List<SoundTouch> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.soundtouch_list, null);
        }

        SoundTouch st = getItem(position);

        if (st != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.soundTouchName);
            TextView tt2 = (TextView) v.findViewById(R.id.soundTouchIP);

            if (tt1 != null) {
                tt1.setText(st.getName());
            }

            if (tt2 != null) {
                tt2.setText(st.getIp());
            }
        }

        return v;
    }

}