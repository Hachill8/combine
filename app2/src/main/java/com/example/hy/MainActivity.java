package com.example.hy;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageButton fab,fab_ft01,fab_ft02,fab_ft03,fab_ft04;
    Button fab_ft05;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab=(ImageButton)findViewById(R.id.fab);
        fab_ft01=(ImageButton)findViewById(R.id.fab_紀);
        fab_ft02=(ImageButton)findViewById(R.id.fab_討);
        fab_ft03=(ImageButton)findViewById(R.id.fab_商);
        fab_ft04=(ImageButton)findViewById(R.id.fab_曆);
        fab_ft05=(Button)findViewById(R.id.fab_搜);



        fab_ft05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,search.class);
                startActivity(intent);
            }
        });

        fab_ft04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,calendar.class);
                startActivity(intent);
            }
        });

        fab_ft01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,record.class);
                startActivity(intent);
            }
        });
        fab_ft02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,forum.class);
                startActivity(intent);
            }
        });
    }
}
