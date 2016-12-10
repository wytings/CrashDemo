package com.wytings.demo;

import android.content.Intent;
import android.view.View;

public class ActivityNo3 extends BaseActivity {

    @Override
    protected void initialize() {
        addButton("start activity No.1", android.R.color.holo_green_light,new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ActivityNo1.class));
            }
        });
    }
}
