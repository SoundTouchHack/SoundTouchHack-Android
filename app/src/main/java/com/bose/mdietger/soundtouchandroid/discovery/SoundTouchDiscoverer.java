package com.bose.mdietger.soundtouchandroid.discovery;

import android.content.Context;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.util.Log;

import com.bose.mdietger.soundtouchandroid.soundtouch.SoundTouch;

/**
 * SoundTouchDiscoverer class. This class is used to discover SoundTouch
 * devices on the network.
 */
public class SoundTouchDiscoverer {

    private static final String TAG = "SoundTouchDiscoverer";
    private static final String SERVICE_TYPE = "_soundtouch._tcp.";

    private NsdManager mNsdManager;
    private NsdManager.DiscoveryListener mDiscoveryListener;
    private NsdManager.ResolveListener mResolveListener;
    private NsdServiceInfo mServiceInfo;

    private DeviceHandler deviceHandler;

    /**
     * Instantiates a new SoundTouchDiscoverer.
     * @param ctx the context
     */
    public SoundTouchDiscoverer(Context ctx, DeviceHandler handler) {
        mNsdManager = (NsdManager)(ctx.getSystemService(Context.NSD_SERVICE));
        deviceHandler = handler;

        initializeResolveListener();
        initializeDiscoveryListener();

    }

    /**
     * Starts discovering devices.
     */
    public void start() {
        mNsdManager.discoverServices(SERVICE_TYPE, NsdManager.PROTOCOL_DNS_SD, mDiscoveryListener);
    }

    /**
     * Initializes a ResolveListener.
     */
    void initializeResolveListener() {
        mResolveListener = new NsdManager.ResolveListener() {

            @Override
            public void onResolveFailed(NsdServiceInfo serviceInfo, int errorCode) {
                Log.e(TAG, "Resolve failed " + errorCode);

                // on failure.. retry
                mNsdManager.discoverServices(SERVICE_TYPE, NsdManager.PROTOCOL_DNS_SD, mDiscoveryListener);

            }

            @Override
            public void onServiceResolved(NsdServiceInfo serviceInfo) {
                mServiceInfo = serviceInfo;

                Log.d(TAG, "Resolved address = " + serviceInfo.getHost().getHostName());
                SoundTouch device = new SoundTouch(mServiceInfo.getServiceName(), mServiceInfo.getHost().getHostName());
                deviceHandler.addDevice(device);
            }

        };
    }

    /**
     * Initializes a DiscoveryListener.
     */
    void initializeDiscoveryListener() {
        mDiscoveryListener = new NsdManager.DiscoveryListener() {

            @Override
            public void onDiscoveryStarted(String regType) {
                Log.d(TAG, "Service discovery started");
            }

            @Override
            public void onServiceFound(NsdServiceInfo service) {
                Log.d(TAG, "Service found: " + service);

                Log.d(TAG, "Service Name = " + service.getServiceName());
                Log.d(TAG, "Service Type = " + service.getServiceType());
                if (SERVICE_TYPE.equals(service.getServiceType())) {
                    Log.d(TAG, "Service Found @ '" + service.getServiceName() + "'");
                    mNsdManager.resolveService(service, mResolveListener);
                }
            }

            @Override
            public void onServiceLost(NsdServiceInfo service) {
                Log.e(TAG, "service lost: " + service);
            }

            @Override
            public void onDiscoveryStopped(String serviceType) {
                Log.i(TAG, "Discovery stopped: " + serviceType);
            }

            @Override
            public void onStartDiscoveryFailed(String serviceType, int errorCode) {
                Log.e(TAG, "Discovery failed: Error code: " + errorCode);
            }

            @Override
            public void onStopDiscoveryFailed(String serviceType, int errorCode) {
                Log.e(TAG, "Discovery failed: Error code:" + errorCode);
            }

        };

    }

}
