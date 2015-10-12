package com.bose.mdietger.soundtouchandroid.http.bass;

import com.bose.mdietger.soundtouchandroid.http.value.Value;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Bass class. This class represents the bass.
 */
@Root
public class Bass extends Value {

    /**
     * Instantiates a new Bass
     *
     * @param value the value
     */
    public Bass(String value) {
        super(value);
    }
}
