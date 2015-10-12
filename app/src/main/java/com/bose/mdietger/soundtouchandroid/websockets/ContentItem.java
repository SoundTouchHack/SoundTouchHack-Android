package com.bose.mdietger.soundtouchandroid.websockets;

import com.bose.mdietger.soundtouchandroid.websockets.selection.ItemName;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

/**
 * ContentItem class. Part of Preset class. Contains ContentItem info.
 */
public class ContentItem {

    @Attribute(name = "source")
    private String source;

    @Attribute(name = "location")
    private String location;

    @Attribute(name = "sourceAccount")
    private String sourceAccount;

    @Attribute(name = "isPresetable")
    private boolean isPresetable;

    @Element(name = "itemName")
    private ItemName itemName;

    /**
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the sourceAccount
     */
    public String getSourceAccount() {
        return sourceAccount;
    }

    /**
     * @param sourceAccount the sourceAccount to set
     */
    public void setSourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    /**
     * @return true if presetable, false if not
     */
    public boolean isPresetable() {
        return isPresetable;
    }

    /**
     * @param isPresetable true if presetable, false if not
     */
    public void setIsPresetable(boolean isPresetable) {
        this.isPresetable = isPresetable;
    }

    /**
     * @return the itemName
     */
    public ItemName getItemName() {
        return itemName;
    }

    /**
     * @param itemName the itemName to set
     */
    public void setItemName(ItemName itemName) {
        this.itemName = itemName;
    }

}
