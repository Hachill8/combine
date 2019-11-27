package com.example.hy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.hy.user_setting.user_setting;

public class personal_info extends AppCompatActivity {

    GlobalVariable gl;
    ImageButton user_info_edit,back_user;
    String UPname,UPphone,UPaddr,UPage,UPgender,UPexpri;
    TextView name_tv,phone_tv,addr_tv,age_tv,gender_tv,expri_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        gl = (GlobalVariable)getApplicationContext();

        user_info_edit=findViewById(R.id.user_info_edit);
        back_user=findViewById(R.id.back_user);
        name_tv=findViewById(R.id.name_tv);
        phone_tv=findViewById(R.id.phone_tv);
        addr_tv=findViewById(R.id.addr_tv);
        age_tv=findViewById(R.id.age_tv);
        gender_tv=findViewById(R.id.gender_tv);
        expri_tv=findViewById(R.id.expri_tv);

        back_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(personal_info.this, user_setting.class);
                startActivity(x);
            }
        });

        user_info_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(personal_info.this, personal_info_edit.class);
                startActivity(x);
            }
        });


//        name_tv.setText(gl.getUser_name());
//        phone_tv.setText(gl.getUser_phone());
//        addr_tv.setText(gl.getUser_addr());
//        age_tv.setText(gl.getUser_age());
//        gender_tv.setText(gl.getUser_gender());
//        expri_tv.setText(gl.getUser_expri());

        Intent intent = getIntent();
        UPname=intent.getStringExtra("ETname");
        UPphone=intent.getStringExtra("ETphone");
        UPaddr=intent.getStringExtra("ETaddr");
        UPage=intent.getStringExtra("ETage");
        UPgender=intent.getStringExtra("ETgender");
        UPexpri=intent.getStringExtra("ETexpri");

        name_tv.setText(UPname);
        phone_tv.setText(UPphone);
        addr_tv.setText(UPaddr);
        age_tv.setText(UPage);
        gender_tv.setText(UPgender);
        expri_tv.setText(UPexpri);



    }
}
