package com.bose.mdietger.soundtouchandroid.discovery;

import java.util.List;

/**
 * DeviceHandler interface. This interface defines the functionality
 * of the DeviceHandler for devices of type <T>.
 * @param <T> the type of device
 */
public interface DeviceHandler<T> {

    /**
     * Get a List of all devices.
     * @return List<T> of all devices
     */
    List<T> getDevices();

    /**
     * Add a device to the List<T>.
     * @param device the device to add
     */
    void addDevice(T device);

}
