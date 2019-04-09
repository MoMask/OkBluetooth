package com.momask.library;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public abstract class BluetoothSubject {

    private final BluetoothAdapter mDefaultAdapter;
    private Context mContext;
    private volatile boolean isOpenBlueTooth;
    private BlueToothHelper blueToothHelper;
    private List<BluetoothDevice> mBondDevices = new ArrayList<>();
    private List<BluetoothDevice> mUnBondDevices = new ArrayList<>();
    private List<BluetoothDevice> mTotalDevices = new ArrayList<>();
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                blueToothHelper.onReceiveDevice(device);
                mTotalDevices.add(device);
                if (device.getBondState() == BluetoothDevice.BOND_BONDED) {    //显示已配对设备
                    blueToothHelper.onReceiveBondDevice(device);
                    mBondDevices.add(device);
                    Log.d("OkBlueTooth", "device bond:   " + device.getName() + "////////" + device.getAddress());
                } else if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    blueToothHelper.onReceiveUnBondDevice(device);
                    mUnBondDevices.add(device);
                    Log.d("OkBlueTooth", "device unBond:   " + device.getName() + "////////" + device.getAddress());
                }

            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                //当结束搜索或者关闭蓝牙的时候调用这个方法
                Log.d("OkBlueTooth", "finish search");
                blueToothHelper.onBondDevicesResult(mBondDevices);
                blueToothHelper.onUnBondDevicesResult(mUnBondDevices);
                blueToothHelper.onTotalDevicesResult(mTotalDevices);
            }

        }


    };

    public BluetoothSubject(Context context) {
        this.mContext = context;
        mDefaultAdapter = BluetoothAdapter.getDefaultAdapter();
        IntentFilter mFoundFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        mContext.registerReceiver(mReceiver, mFoundFilter);
        IntentFilter mFinishedFilter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        mContext.registerReceiver(mReceiver, mFinishedFilter);

    }

    public void setListener(BlueToothHelper blueToothHelper) {
        this.blueToothHelper = blueToothHelper;
    }

    protected void startSearching() {
        if (blueToothHelper == null) {
            blueToothHelper = new BlueToothHelper();
        }

        try {
            if (!mDefaultAdapter.isEnabled()) {
                if (mDefaultAdapter.enable()) {
                    //开启到能够启动是有延迟的
                    discovery();
                } else {
                    //开启失败。。
                    blueToothHelper.onOpenBlueToothError("蓝牙启动失败");
                }
            }
            discovery();
        } catch (Exception e) {
            blueToothHelper.onOpenBlueToothError(e.getMessage());
        }
    }

    private synchronized void discovery() {
        if (!isOpenBlueTooth) {
            if (mDefaultAdapter.startDiscovery()) {
                clearContainer();
                isOpenBlueTooth = true;
            } else {
                discovery();
            }
        } else {
            clearContainer();
            mDefaultAdapter.startDiscovery();
        }

    }

    private void clearContainer() {
        mBondDevices.clear();
        mUnBondDevices.clear();
        mTotalDevices.clear();
    }

    public void unMount() {
        mContext.unregisterReceiver(mReceiver);
    }


}
