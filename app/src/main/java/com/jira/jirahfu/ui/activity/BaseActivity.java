package com.jira.jirahfu.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.jira.jirahfu.R;
import com.jira.jirahfu.core.PreferenceManager;

/**
 * @author Gonca Akguen
 * @version 1.0
 * @since 11/2016 (WS16/17)
 */

public class BaseActivity extends AppCompatActivity
{
    protected FrameLayout mFrameLayoutBase;
    protected PreferenceManager mPreferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        mFrameLayoutBase = (FrameLayout) findViewById(R.id.fl_content_frame_base);


    }
}
