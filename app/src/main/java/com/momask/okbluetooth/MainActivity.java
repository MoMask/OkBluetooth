package com.momask.okbluetooth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.momask.library.BluetoothServiceProxy;

public class MainActivity extends AppCompatActivity {

    private BluetoothServiceProxy blueToothServiceProxy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        blueToothServiceProxy = new BluetoothServiceProxy(this);
        blueToothServiceProxy.start();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        blueToothServiceProxy.unMount();
    }
}
