package com.example.hy.user_setting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.example.hy.R;
import com.example.hy.like_post;
import com.example.hy.like_vege;
import com.example.hy.login.login;
import com.example.hy.my_post;
import com.example.hy.personal_help;
import com.example.hy.personal_info;

public class user_setting extends AppCompatActivity
{
    public TextView Name,Email;
    Button level,personal_user_info,personal_article,personal_collect,personal_article_like,personal_help,personal_signout;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.personal_page );


//        Name=(TextView)findViewById(R.id.name);
//        Email=(TextView)findViewById(R.id.email);
//        //取得intent中的bundle物件
//        Bundle bundle01 =this.getIntent().getExtras();
//
//        String name = bundle01.getString("name");
//        String email = bundle01.getString("email");
//        Name.setText(name);
//        Email.setText(email);

        level=findViewById(R.id.level);
        personal_user_info=findViewById(R.id.personal_user_info);
        personal_article=findViewById(R.id.personal_article);
        personal_collect=findViewById(R.id.personal_collect);
        personal_article_like=findViewById(R.id.personal_article_like);
        personal_help=findViewById(R.id.personal_help);
        personal_signout=findViewById(R.id.personal_signout);

        level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(user_setting.this);
                TextView title = new TextView(user_setting.this);
                builder.setTitle("等級功能");
                builder.setMessage("即將推出...敬請期待!!");
                builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog=builder.create();
                dialog.show();
            }
        });
        personal_user_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(user_setting.this, personal_info.class);
                startActivity(intent);
            }
        });
        personal_article.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(user_setting.this, my_post.class);
                startActivity(intent);
            }
        });
        personal_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(user_setting.this, like_vege.class);
                startActivity(intent);
            }
        });
        personal_article_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(user_setting.this, like_post.class);
                startActivity(intent);
            }
        });
        personal_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(user_setting.this, personal_help.class);
                startActivity(intent);
            }
        });
        personal_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x=new Intent(user_setting.this, login.class);
                startActivity(x);
            }
        });
    }
}
