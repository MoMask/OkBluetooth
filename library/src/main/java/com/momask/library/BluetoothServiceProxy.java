package com.momask.library;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.util.Log;

/**
 * 用于发送蓝牙配对请求
 */
public class BluetoothServiceProxy extends BluetoothSubject {


    public BluetoothServiceProxy(Context context) {
        super(context);
    }

    public void start() {
        startSearching();

    }

    //采用nio进行流的处理

    /**
     * 通过mac地址进行链接
     *
     * @param address mac地址
     */
    public void connectByMacAddress(String address) {
        try {
            BluetoothDevice remoteDevice = mDefaultAdapter.getRemoteDevice(address);
            BluetoothSocket mBluetoothSocket = remoteDevice.createRfcommSocketToServiceRecord(PUBLIC_UUID);
            mBluetoothSocket.connect();
        } catch (Exception e) {

        }
    }


}
