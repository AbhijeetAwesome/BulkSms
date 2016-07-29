package com.sphere.BulkSms;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class checking_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checking);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Your message sending command has been initiated", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                    Intent intent = getIntent();
                    Bundle b = intent.getExtras();
                    String[] array=b.getStringArray("params");

                    checking_activityFragment caf = new checking_activityFragment();
                    caf.sending(array[0], array[1], array[2], array[3]);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
