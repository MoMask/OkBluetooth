package com.momask.library;

import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;

import java.util.List;

public interface ResultCallBack {
    void onOpenBlueToothError(String error);

    void onReceiveDevice(BluetoothDevice bluetoothDevice);

    void onReceiveBondDevice(BluetoothDevice bluetoothDevice);

    void onReceiveUnBondDevice(BluetoothDevice bluetoothDevice);

    void onBondDevicesResult(List<BluetoothDevice> bondDevices);

    void onUnBondDevicesResult(List<BluetoothDevice> unBondDevices);

    void onTotalDevicesResult(List<BluetoothDevice> unBondDevices);
}
