package com.bose.mdietger.soundtouchandroid.discovery;

import com.bose.mdietger.soundtouchandroid.soundtouch.SoundTouch;

import java.util.ArrayList;
import java.util.List;

/**
 * SoundTouchDeviceHandler class. This class implements the functionality
 * for the SoundTouchDeviceHandler for SoundTouch devices.
 */
public class SoundTouchDeviceHandler implements DeviceHandler<SoundTouch> {

    private List<SoundTouch> devices;

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
        this.devices.add(device);
    }

}
