package com.bose.mdietger.soundtouchandroid.discovery;

import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.util.Log;

import com.bose.mdietger.soundtouchandroid.soundtouch.SoundTouch;

/**
 * Created by dm828586 on 5/10/2015.
 */
public class SoundTouchResolveListener implements NsdManager.ResolveListener {

    private static final String TAG = "SoundTouchDiscoverer";
    private NsdServiceInfo mServiceInfo;
    private DeviceHandler deviceHandler;

    public SoundTouchResolveListener(DeviceHandler deviceHandler) {
        this.deviceHandler = deviceHandler;
    }

    @Override
    public void onResolveFailed(NsdServiceInfo serviceInfo, int errorCode) {
        Log.e(TAG, "Resolve failed " + errorCode);

        // on failure.. retry
        //mNsdManager.discoverServices(SERVICE_TYPE, NsdManager.PROTOCOL_DNS_SD, mDiscoveryListener);

    }

    @Override
    public void onServiceResolved(NsdServiceInfo serviceInfo) {
        mServiceInfo = serviceInfo;

        Log.d(TAG, "Resolved address = " + serviceInfo.getHost().getHostAddress());
        SoundTouch device = new SoundTouch(mServiceInfo.getServiceName(), mServiceInfo.getHost().getHostAddress());
        deviceHandler.addDevice(device);
    }

}
