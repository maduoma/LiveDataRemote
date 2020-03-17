package com.dodemy.android.livedata;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.dodemy.android.livedata.ui.transform.TransformFragment;


public class TransformActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transform_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, TransformFragment.newInstance())
                    .commitNow();
        }
    }
}
