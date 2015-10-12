package com.bose.mdietger.soundtouchandroid.websockets.selection;

import com.bose.mdietger.soundtouchandroid.websockets.volume.Volume;

import org.simpleframework.xml.Element;

/**
 * NowSelectionUpdated class. Part of DeviceUpdated class. Contains updated
 * Selection info.
 */
public class NowSelectionUpdated {

    @Element(name = "preset")
    private Preset preset;

    /**
     * @return the preset
     */
    public Preset getPreset() {
        return preset;
    }

    /**
     * @param preset the preset to set
     */
    public void setPreset(Preset preset) {
        this.preset = preset;
    }

}
