package com.wytings.demo;

import android.content.Intent;
import android.view.View;

public class ActivityNo1 extends BaseActivity {

    @Override
    protected void initialize() {
        boolean isExit = getIntent().getBooleanExtra("isExist", false);
        if (isExit) {
            finish();
            return;
        }

        addButton("start activity No.2", android.R.color.holo_green_light, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ActivityNo2.class));
            }
        });
    }
}
