package com.wytings.demo;

import android.util.Log;

/**
 * Created by rex on 12/10/16.
 */

public class G {
    private static final String TAG = "wytings";

    public static void i(Object msg) {
        Log.i(TAG, " ----- " + (msg == null ? "null" : msg.toString()) + " ----- ");
    }
}
