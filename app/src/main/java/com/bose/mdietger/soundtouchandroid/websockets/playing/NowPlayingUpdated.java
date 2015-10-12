package com.bose.mdietger.soundtouchandroid.websockets.playing;

import com.bose.mdietger.soundtouchandroid.websockets.selection.Preset;

import org.simpleframework.xml.Element;

/**
 * NowPlayingUpdated class. Part of DeviceUpdated class. Contains updated
 * playing info.
 */
public class NowPlayingUpdated {

    @Element(name = "nowPlaying")
    private NowPlaying nowPlaying;

    /**
     * @return the nowPlaying
     */
    public NowPlaying getNowPlaying() {
        return nowPlaying;
    }

    /**
     * @param nowPlaying the nowPlaying to set
     */
    public void setNowPlaying(NowPlaying nowPlaying) {
        this.nowPlaying = nowPlaying;
    }

}
