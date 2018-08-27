package com.wytings.demo;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import static android.widget.LinearLayout.SHOW_DIVIDER_BEGINNING;
import static android.widget.LinearLayout.SHOW_DIVIDER_MIDDLE;

public abstract class BaseActivity extends Activity {

    LinearLayout buttonsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buttonsContainer = new LinearLayout(this);
        buttonsContainer.setOrientation(LinearLayout.VERTICAL);
        buttonsContainer.setGravity(Gravity.CENTER_HORIZONTAL);
        buttonsContainer.setShowDividers(SHOW_DIVIDER_MIDDLE | SHOW_DIVIDER_BEGINNING);
        setContentView(buttonsContainer);
        baseInit();
        initialize();
    }

    private void baseInit() {
        if (getActionBar() != null) {
            getActionBar().setTitle(this.getClass().getSimpleName());
        }

        addButton("make a crash", android.R.color.holo_red_light, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                throw new RuntimeException("a test exception for app");
            }
        });
        addButton("App.exit()", android.R.color.holo_orange_light, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.getApp().exit();
            }
        });
        addButton("System.exit(0)", android.R.color.holo_orange_light, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
        addButton("android.os.Process.killProcess", android.R.color.holo_orange_light, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
    }

    public void addButton(String title, int backgroundColor, View.OnClickListener listener) {
        if (!TextUtils.isEmpty(title)) {
            Button button = new Button(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2);
            int margin = 30;
            params.setMargins(margin, margin, margin, 0);
            button.setLayoutParams(params);
            button.setText(title);
            button.setBackgroundColor(getResources().getColor(backgroundColor));
            button.setOnClickListener(listener);
            buttonsContainer.addView(button);
        }
    }

    private static final int activity_counts = Menu.FIRST + 1;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(Menu.NONE, activity_counts, 0, "current activity counts");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == activity_counts) {
            App.getApp().showActivityList();
        }
        return super.onOptionsItemSelected(item);
    }

    public Activity getActivity() {
        return this;
    }

    protected abstract void initialize();


}
