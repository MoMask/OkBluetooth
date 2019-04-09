package com.momask.library;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.util.Log;

import java.io.OutputStream;
import java.util.UUID;

/**
 * 用于发送蓝牙配对请求
 */
public class BluetoothServiceProxy extends BluetoothSubject {


    private BluetoothServerSocket mBluetoothServerSocket;

    public BluetoothServiceProxy(Context context) {
        super(context);
    }

    public void start() {
        startSearching();

    }

    public void listenerAccept(String serviceName, UUID uuid, int timeout) {
        if ("".equals(serviceName)) {
            serviceName = SERVICE_NAME;
        }

        if ("".equals(uuid)) {
            uuid = PUBLIC_UUID;
        }
        try {
            mBluetoothServerSocket = mDefaultAdapter.listenUsingRfcommWithServiceRecord(serviceName, uuid);
            while (true) {
                BluetoothSocket socket = mBluetoothServerSocket.accept(timeout);
                if (socket != null) {
                    mBluetoothServerSocket.close();
                    break;
                }
            }
        } catch (Exception e) {

        }
    }

    public void listenerAccept() {
        try {
            mBluetoothServerSocket = mDefaultAdapter.listenUsingRfcommWithServiceRecord(SERVICE_NAME, PUBLIC_UUID);
            mBluetoothServerSocket.accept();
        } catch (Exception e) {

        }
    }


//    public void connectByMacAddress(String address) {
//        try {
//            mBluetoothServerSocket = mDefaultAdapter.listenUsingRfcommWithServiceRecord("",PUBLIC_UUID);
//            mBluetoothServerSocket.accept();
//        } catch (Exception e) {
//
//        }
//    }
//
//    public void connectByMacAddress(String address, UUID uuid) {
//        try {
//            mBluetoothSocket = mDefaultAdapter.getRemoteDevice(address).createRfcommSocketToServiceRecord(uuid);
//            mBluetoothSocket.connect();
//        } catch (Exception e) {
//
//        }
//    }
//
//    public void connectByDevice(BluetoothDevice bluetoothDevice) {
//        try {
//            mBluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(PUBLIC_UUID);
//            mBluetoothSocket.connect();
//        } catch (Exception e) {
//
//        }
//    }
//
//    public void connectByDevice(BluetoothDevice bluetoothDevice, UUID uuid) {
//        try {
//            mBluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(uuid);
//            mBluetoothSocket.connect();
//            mBluetoothSocket.getOutputStream();
//        } catch (Exception e) {
//
//        }
//    }


}
