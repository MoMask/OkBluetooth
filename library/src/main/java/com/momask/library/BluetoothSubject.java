package com.momask.library;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

public abstract class BluetoothSubject {

    private final BluetoothAdapter mDefaultAdapter;
    private Context mContext;
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();

            Log.d("OkBlueTooth", "action:   " + action);
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                if (device.getBondState() == BluetoothDevice.BOND_BONDED) {    //显示已配对设备
                    Log.d("OkBlueTooth", "device bond:   " + device.getName() + "////////" + device.getAddress());
                } else if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    Log.d("OkBlueTooth", "device unBond:   " + device.getName() + "////////" + device.getAddress());
                }

            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {

                Log.d("OkBlueTooth", "finish search");

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

    protected  void startSearching() {
        try {
            if (!mDefaultAdapter.isEnabled()) {
                if (mDefaultAdapter.enable()) {
                    Log.d("OkBlueTooth", "open bluetooth succeed");
                    //开启到能够启动是有延迟的
                    discovery();
                } else {
                    Log.d("OkBlueTooth", "open bluetooth failed");
                }
            }
            discovery();
        } catch (Exception e) {
            Log.e("OkBlueTooth", "error:" + e.getMessage());
        }
    }

    private synchronized void discovery() {
        if (mDefaultAdapter.startDiscovery()) {
            Log.d("OkBlueTooth", "open discovery succeed");
        } else {
            Log.d("OkBlueTooth", "open discovery failed");
            discovery();
        }
    }

    public void unMount() {
        mContext.unregisterReceiver(mReceiver);
    }


}
