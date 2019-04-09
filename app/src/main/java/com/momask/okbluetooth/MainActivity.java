package com.momask.okbluetooth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.momask.library.BlueToothServiceProxy;

public class MainActivity extends AppCompatActivity {

    private BlueToothServiceProxy blueToothServiceProxy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        blueToothServiceProxy = new BlueToothServiceProxy(this);
        blueToothServiceProxy.start();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        blueToothServiceProxy.unMount();
    }
}
