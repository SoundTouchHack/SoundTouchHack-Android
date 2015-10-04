package com.bose.mdietger.soundtouchandroid.http.volume;

import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

/**
 * Volume class. This class represents the volume.
 */
@Root
public class Volume {

    @Text
    private String value;

    /**
     * Instantiates a new Volume
     * @param value the value
     */
    public Volume(String value) {
        this.value = value;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

}
