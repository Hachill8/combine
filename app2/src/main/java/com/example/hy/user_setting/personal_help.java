package com.example.hy.user_setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.example.hy.R;

public class personal_help extends AppCompatActivity {

    ImageButton back_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_personal_help);

        back_user=findViewById(R.id.back_user);

        back_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(personal_help.this, user_setting.class);
                startActivity(intent);
                personal_help.this.finish();
            }
        });
    }
}
