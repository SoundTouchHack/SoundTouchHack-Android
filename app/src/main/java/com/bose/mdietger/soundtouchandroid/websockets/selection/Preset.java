package com.bose.mdietger.soundtouchandroid.websockets.selection;

import com.bose.mdietger.soundtouchandroid.websockets.ContentItem;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

/**
 * Preset class. Part of NewSelectionUpdated class. Contains updated
 * Preset info.
 */
public class Preset {

    @Attribute(name = "id")
    private Integer id;

    @Element(name = "ContentItem")
    private ContentItem contentItem;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the contentItem
     */
    public ContentItem getContentItem() {
        return contentItem;
    }

    /**
     * @param contentItem the contentItem to set
     */
    public void setContentItem(ContentItem contentItem) {
        this.contentItem = contentItem;
    }

}
