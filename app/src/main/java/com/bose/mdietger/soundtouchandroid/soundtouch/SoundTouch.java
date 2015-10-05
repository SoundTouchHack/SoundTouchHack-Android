package com.bose.mdietger.soundtouchandroid.soundtouch;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * SoundTouch class. This class represents a SoundTouch device with
 * its name and ip address on the local network.
 */
public class SoundTouch implements Parcelable {

    private String name;
    private String ip;

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
        this.name = name.replace("\\\\032", " ");
        this.ip = ip;
    }

    /**
     * Instantiates a new SoundTouch.
     * @param in the Parcel in
     */
    public SoundTouch(Parcel in) {
        String[] data= new String[2];
        in.readStringArray(data);

        this.name = data[0];
        this.ip = data[1];
    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SoundTouch that = (SoundTouch) o;

        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] { this.name,this.ip});
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
