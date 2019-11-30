package com.example.hy.user_setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.hy.R;
import com.example.hy.search.search;

public class like_vege extends AppCompatActivity {
    ImageButton back_user;
    Button go_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like_vege);

        back_user=findViewById(R.id.back_user);
        go_search=findViewById(R.id.go_search);

        back_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(like_vege.this, user_setting.class);
                startActivity(intent);
            }
        });
        go_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x=new Intent(like_vege.this, search.class);
                startActivity(x);
            }
        });
    }
}
