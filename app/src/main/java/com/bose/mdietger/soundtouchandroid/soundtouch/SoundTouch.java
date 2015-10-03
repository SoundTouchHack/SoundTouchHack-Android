package com.bose.mdietger.soundtouchandroid.soundtouch;

import android.os.Parcel;
import android.os.Parcelable;

import java.net.InetAddress;

/**
 * SoundTouch class. This class represents a SoundTouch device with
 * its name and ip address on the local network.
 */
public class SoundTouch implements Parcelable {

    private String _name;
    private String _ip;

    /**
     * Instantiates a new SoundTouch.
     */
    public SoundTouch(){

    }

    /**
     * Instantiates a new SoundTouch.
     * @param name the name of the device
     * @param ip the ip on the local network
     */
    public SoundTouch(String name, String ip) {
        this._name = name.replace("\\\\032", " ");
        this._ip = ip;
    }

    /**
     * Instantiates a new SoundTouch.
     * @param in the Parcel in
     */
    public SoundTouch(Parcel in) {
        String[] data= new String[2];
        in.readStringArray(data);

        this._name = data[0];
        this._ip = data[1];
    }

    /**
     * @return the ip
     */
    public String get_ip() {
        return _ip;
    }

    /**
     * @param _ip the ip to set
     */
    public void set_ip(String _ip) {
        this._ip = _ip;
    }

    /**
     * @return the name
     */
    public String get_name() {
        return _name;
    }

    /**
     * @param _name the name to set
     */
    public void set_name(String _name) {
        this._name = _name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] { this._name,this._ip });
    }

    public static final Parcelable.Creator<SoundTouch> CREATOR = new Parcelable.Creator<SoundTouch>() {

        @Override
        public SoundTouch createFromParcel(Parcel source) {
            return new SoundTouch(source);
        }

        @Override
        public SoundTouch[] newArray(int size) {
            return new SoundTouch[size];
        }

    };

}
