package com.wytings.demo;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by rex on 12/9/16.
 */

public abstract class App extends Application {

    private static App context;
    private List<Activity> activityList;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        init();
        initialize();
    }

    private void init() {
        Thread.setDefaultUncaughtExceptionHandler(new CrashHandler());
        activityList = Collections.synchronizedList(new ArrayList<Activity>());
        registerActivityLifecycleCallbacks(new SimpleActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                super.onActivityCreated(activity, bundle);
                G.i(activity.getLocalClassName() + " is created");
                activityList.add(activity);
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                super.onActivityDestroyed(activity);
                activityList.remove(activity);
                G.i(activity.getLocalClassName() + " is destroyed");
            }
        });
    }


    public static App getApp() {
        return context;
    }

    public void exit() {
        Iterator<Activity> iterator = activityList.iterator();
        G.i("remain activity count - " + activityList.size());
        while (iterator.hasNext()) {
            Activity activity = iterator.next();
            activity.finish();
            iterator.remove();
            G.i("finish activity - " + activity.getClass().getSimpleName());
        }
        activityList.clear();
        System.exit(0); // this is line cannot be commented out;
    }

    public void restart() {
        Intent intent = new Intent(this, ActivityNo1.class);
        intent.putExtra("isExist", true); // 这是一个民间偏方,虽非正统,但是疗效明显……
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
        System.exit(0); // this is line cannot be commented out;
    }

    public void showActivityList() {
        StringBuilder builder = new StringBuilder();
        builder.append("list size is ").append(activityList.size()).append("\n");
        for (Activity activity : activityList) {
            builder.append(activity.getLocalClassName()).append("\n");
        }
        builder.deleteCharAt(builder.length() - 1);
        G.i(builder.toString());
        Toast.makeText(this, builder.toString(), Toast.LENGTH_LONG).show();
    }


    protected abstract void initialize();


}
