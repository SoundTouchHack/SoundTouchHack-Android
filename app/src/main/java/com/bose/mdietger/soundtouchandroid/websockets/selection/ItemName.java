package com.bose.mdietger.soundtouchandroid.websockets.selection;

import org.simpleframework.xml.Text;

/**
 * ItemName class. Part of ContentItem class. Contains ItemName info.
 */
public class ItemName {

    @Text
    private String value;

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
