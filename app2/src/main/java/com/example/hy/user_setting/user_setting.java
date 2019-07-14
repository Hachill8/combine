package com.example.hy.user_setting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.hy.R;

public class user_setting extends AppCompatActivity
{
    public TextView Name,Email;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate( savedInstanceState );
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView( R.layout.activity_user_setting );


//        Name=(TextView)findViewById(R.id.name);
//        Email=(TextView)findViewById(R.id.email);
//        //取得intent中的bundle物件
//        Bundle bundle01 =this.getIntent().getExtras();
//
//        String name = bundle01.getString("name");
//        String email = bundle01.getString("email");
//        Name.setText(name);
//        Email.setText(email);
    }
}
