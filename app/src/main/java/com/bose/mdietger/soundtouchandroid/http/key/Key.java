package com.bose.mdietger.soundtouchandroid.http.key;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

/**
 * Keys are used as a simple means to interact with the SoundTouch speaker.
 */
@Root
public class Key {

    public static final String SENDER = "Gabbo";

    @Attribute(name = "state")
    private String state;

    @Text
    private String value;

    @Attribute(name = "sender")
    private String sender;

    /**
     * Instantiates a new Key.
     */
    public Key(){}

    /**
     * Instantiates a new Key.
     * @param state the state
     * @param sender the sender
     * @param value the volume
     */
    public Key(String state, String sender, String value){
        this.state = state;
        this.value = value;
        this.sender = sender;
    }

    /**
     * @return the sender
     */
    public String getSender() {
        return sender;
    }

    /**
     * @param sender the sender to set
     */
    public void setSender(String sender) {
        this.sender = sender;
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

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

}
