package com.example.hy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class login extends AppCompatActivity {

    TextView loginsub;
    ImageView bg2;
    Button google;
    Animation smalltobig, stb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        smalltobig = AnimationUtils.loadAnimation(this, R.anim.smalltobig);
        stb2 = AnimationUtils.loadAnimation(this, R.anim.stb2);

        loginsub = (TextView) findViewById(R.id.loginsub);
        google = (Button) findViewById(R.id.google);
        bg2 = (ImageView) findViewById(R.id.bg2);


        loginsub.setTranslationY(400);
        google.setTranslationY(400);

        google.setAlpha(0);
        loginsub.setAlpha(0);


        google.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        loginsub.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(700).start();

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(login.this,login2.class);
                startActivity(b);

            }
        });


    }
}
