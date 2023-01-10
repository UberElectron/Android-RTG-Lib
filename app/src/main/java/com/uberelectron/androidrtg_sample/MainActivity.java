package com.uberelectron.androidrtg_sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
    }

    private void initComponents()
    {
        Button btExActivity = findViewById(R.id.btMainExActivity);
        btExActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                initExampleActivity();
            }
        });

        Button btExFragments = findViewById(R.id.btMainExFragments);
        btExFragments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initExampleFragments();
            }
        });

        //TODO: About Button!
    }


    private void initExampleActivity()
    {
        Intent newActivity = new Intent(this, ActivityExample.class);
        startActivity(newActivity);
    }

    private void initExampleFragments()
    {
        Intent newActivity = new Intent(this, FragmentExampleActivity.class);
        startActivity(newActivity);
    }
}