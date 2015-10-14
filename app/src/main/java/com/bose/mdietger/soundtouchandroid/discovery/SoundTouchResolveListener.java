package com.bose.mdietger.soundtouchandroid.discovery;

import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.util.Log;

import com.bose.mdietger.soundtouchandroid.soundtouch.SoundTouch;

/**
 * SoundTouchResolveListener class. This class is used as listener in the
 * DiscoveryListener and can resolve the SoundTouch device services.
 */
public class SoundTouchResolveListener implements NsdManager.ResolveListener {

    private static final String TAG = "SoundTouchDiscoverer";

    private NsdServiceInfo mServiceInfo;

    private NsdManager mNsdManager;
    private NsdManager.DiscoveryListener mDiscoveryListener;
    private DeviceHandler deviceHandler;

    /**
     * Instantiates a new SoundTouchResolveListener.
     * @param mNsdManager the mNsdManager
     * @param mDiscoveryListener the discoveryListener
     * @param deviceHandler the deviceHandler
     */
    public SoundTouchResolveListener(NsdManager mNsdManager, NsdManager.DiscoveryListener mDiscoveryListener, DeviceHandler deviceHandler) {
        this.mNsdManager = mNsdManager;
        this.mDiscoveryListener = mDiscoveryListener;
        this.deviceHandler = deviceHandler;
    }

    @Override
    public void onResolveFailed(NsdServiceInfo serviceInfo, int errorCode) {
        Log.e(TAG, "Resolve failed " + errorCode);

        // on failure.. retry
        mNsdManager.discoverServices(SoundTouchDiscoverer.SERVICE_TYPE, NsdManager.PROTOCOL_DNS_SD, mDiscoveryListener);
    }

    @Override
    public void onServiceResolved(NsdServiceInfo serviceInfo) {
        mServiceInfo = serviceInfo;

        Log.d(TAG, "Resolved address = " + serviceInfo.getHost().getHostAddress());
        SoundTouch device = new SoundTouch(mServiceInfo.getServiceName(), mServiceInfo.getHost().getHostAddress());
        deviceHandler.addDevice(device);
    }

}
