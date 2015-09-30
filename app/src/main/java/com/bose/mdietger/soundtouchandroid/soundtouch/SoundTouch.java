package com.bose.mdietger.soundtouchandroid.soundtouch;

import android.os.Parcel;
import android.os.Parcelable;

import java.net.InetAddress;

/**
 * Created by mdiet on 30/09/2015.
 */
public class SoundTouch implements Parcelable {
    private String _name;
    private String _ip;

    public SoundTouch(){

    }

    public SoundTouch(String name, String ip){
        this._name = name;
        this._ip = ip;
    }

    public SoundTouch(Parcel in){
        String[] data= new String[2];
        in.readStringArray(data);

        this._name = data[0];
        this._ip = data[1];
    }

    public String get_ip() {
        return _ip;
    }

    public void set_ip(String _ip) {
        this._ip = _ip;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub
        dest.writeStringArray(new String[]{this._name,this._ip});
    }

    public static final Parcelable.Creator<SoundTouch> CREATOR = new Parcelable.Creator<SoundTouch>() {

        @Override
        public SoundTouch createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            return new SoundTouch(source);  //using parcelable constructor
        }

        @Override
        public SoundTouch[] newArray(int size) {
            // TODO Auto-generated method stub
            return new SoundTouch[size];
        }
    };
}
