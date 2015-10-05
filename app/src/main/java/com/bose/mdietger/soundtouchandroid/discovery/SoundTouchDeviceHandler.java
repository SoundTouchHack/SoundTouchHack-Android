package com.bose.mdietger.soundtouchandroid.discovery;

import android.util.Log;

import com.bose.mdietger.soundtouchandroid.callbacks.UpdateDeviceListCallback;
import com.bose.mdietger.soundtouchandroid.soundtouch.SoundTouch;

import java.util.ArrayList;
import java.util.List;

/**
 * SoundTouchDeviceHandler class. This class implements the functionality
 * for the SoundTouchDeviceHandler for SoundTouch devices.
 */
public class SoundTouchDeviceHandler implements DeviceHandler<SoundTouch> {

    private static final String TAG = "SoundTouchDeviceHandler";

    private List<SoundTouch> devices;
    private UpdateDeviceListCallback updateDeviceListCallback;

    public SoundTouchDeviceHandler(UpdateDeviceListCallback updateDeviceListCallback) {
        this.updateDeviceListCallback = updateDeviceListCallback;
    }

    @Override
    public List<SoundTouch> getDevices() {
        if (this.devices == null) {
            this.devices = new ArrayList<>();
        }
        return this.devices;
    }

    @Override
    public void addDevice(SoundTouch device) {
        if (this.devices == null) {
            this.devices = new ArrayList<>();
        }

        if(this.devices.contains(device)) {
            return;
        }

        this.devices.add(device);
        this.updateDeviceListCallback.update();
    }
}
