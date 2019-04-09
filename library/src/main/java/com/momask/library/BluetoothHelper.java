package com.momask.library;

import android.bluetooth.BluetoothDevice;

import java.util.List;

public class BluetoothHelper implements ResultCallBack {
    @Override
    public void onOpenBlueToothError(String error) {

    }

    @Override
    public void onReceiveDevice(BluetoothDevice bluetoothDevice) {

    }

    @Override
    public void onReceiveBondDevice(BluetoothDevice bluetoothDevice) {

    }

    @Override
    public void onReceiveUnBondDevice(BluetoothDevice bluetoothDevice) {

    }

    @Override
    public void onBondDevicesResult(List<BluetoothDevice> bondDevices) {

    }

    @Override
    public void onUnBondDevicesResult(List<BluetoothDevice> unBondDevices) {

    }

    @Override
    public void onTotalDevicesResult(List<BluetoothDevice> unBondDevices) {

    }


}
