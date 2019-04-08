package com.momask.library;

import android.content.Context;
import android.util.Log;

public class BlueToothServiceProxy extends BluetoothSubject {


    public BlueToothServiceProxy(Context context) {
        super(context);
    }

    public void start() {
        startSearching();

    }

}
