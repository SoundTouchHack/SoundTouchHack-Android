package com.bose.mdietger.soundtouchandroid.http.value;

import org.simpleframework.xml.Text;

/**
 * Value class. This class represents a value.
 */
public class Value {

    @Text
    private String value;

    /**
     * Instantiates a new Value
     * @param value the value
     */
    public Value(String value) {
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
