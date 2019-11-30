package com.example.hy.user_setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.hy.R;
import com.example.hy.forum.forum;

public class like_post extends AppCompatActivity {
    ImageButton back_user;
    Button go_forum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like_post);

        back_user=findViewById(R.id.back_user);
        go_forum=findViewById(R.id.go_forum);

        back_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(like_post.this, user_setting.class);
                startActivity(intent);
            }
        });
        go_forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x=new Intent(like_post.this, forum.class);
                startActivity(x);
            }
        });
    }
}
