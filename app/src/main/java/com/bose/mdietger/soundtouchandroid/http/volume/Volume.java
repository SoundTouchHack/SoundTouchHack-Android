package com.bose.mdietger.soundtouchandroid.http.volume;

import com.bose.mdietger.soundtouchandroid.http.value.Value;

import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

/**
 * Volume class. This class represents the volume.
 */
@Root
public class Volume extends Value {

    /**
     * Instantiates a new Volume
     *
     * @param value the value
     */
    public Volume(String value) {
        super(value);
    }
}
