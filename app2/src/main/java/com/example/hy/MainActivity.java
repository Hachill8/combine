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
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab,fab_ft01,fab_ft02,fab_ft03,fab_ft04,fab_ft05;
    boolean fg=false;TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab=(FloatingActionButton)findViewById(R.id.fab);
        fab_ft01=(FloatingActionButton)findViewById(R.id.fab_紀);
        fab_ft02=(FloatingActionButton)findViewById(R.id.fab_討);
        fab_ft03=(FloatingActionButton)findViewById(R.id.fab_尋);
        fab_ft04=(FloatingActionButton)findViewById(R.id.fab_曆);
        fab_ft05=(FloatingActionButton)findViewById(R.id.fab_商);
        tv=(TextView)findViewById(R.id.cloud);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fg==false){
                    openmenu(view);
                    fg=true;
                }else{
                    closemenu(view);
                    fg=false;
                }
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closemenu(fab);
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
        fab_ft03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,search.class);
                startActivity(intent);
            }
        });

    }

    private void openmenu(View view){
        fab_ft01.show();fab_ft02.show();fab_ft03.show();fab_ft04.show();fab_ft05.show();
        ObjectAnimator animator=ObjectAnimator.ofFloat(view,"rotation",0,-155,-135);
        animator.setDuration(500);
        animator.start();
        tv.setVisibility(View.VISIBLE);
        AlphaAnimation alphaAnimation=new AlphaAnimation(0,0.7f);
        alphaAnimation.setDuration(500);
        alphaAnimation.setFillAfter(true);
        tv.startAnimation(alphaAnimation);
    }

    private void closemenu(View view){
        fab_ft01.hide();fab_ft02.hide();fab_ft03.hide();fab_ft04.hide();fab_ft05.hide();
        ObjectAnimator animator=ObjectAnimator.ofFloat(view,"rotation",-135,20,0);
        animator.setDuration(500);
        animator.start();
        AlphaAnimation alphaAnimation=new AlphaAnimation(0.7f,0);
        alphaAnimation.setDuration(500);
        tv.setVisibility(View.GONE);
        tv.startAnimation(alphaAnimation);
    }
}
