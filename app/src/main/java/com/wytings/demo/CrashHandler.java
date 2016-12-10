package com.wytings.demo;

/**
 * Created by rex on 12/10/16.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private boolean isRestart = true;

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        G.i("uncaughtException -> thread=" + t + ", throwable=" + e);
        if (isRestart) {
            App.getApp().restart();
        } else {
            App.getApp().exit();
        }
    }
}
