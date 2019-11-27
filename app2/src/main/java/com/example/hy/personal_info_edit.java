package com.example.hy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class personal_info_edit extends AppCompatActivity {

    //找到UI工人的經紀人，這樣才能派遣工作  (找到顯示畫面的UI Thread上的Handler)
    private Handler mUI_Handler = new Handler();
    //宣告特約工人的經紀人
    private Handler mThreadHandler;
    //宣告特約工人
    private HandlerThread mThread;
    EditText edit_name,edit_phone,edit_address,edit_age,edit_gender,edit_expri;
    String name,phone,addr,age,gender,expri,line;
    Button user_info_confirm,user_info_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info_edit);

        //聘請一個特約工人，有其經紀人派遣其工人做事 (另起一個有Handler的Thread)
        mThread = new HandlerThread("");

        //讓Worker待命，等待其工作 (開啟Thread)
        mThread.start();

        //找到特約工人的經紀人，這樣才能派遣工作 (找到Thread上的Handler)
        mThreadHandler=new Handler(mThread.getLooper());

        edit_name=findViewById(R.id.edit_name);
        edit_phone=findViewById(R.id.edit_phone);
        edit_address=findViewById(R.id.edit_address);
        edit_age=findViewById(R.id.edit_age);
        edit_gender=findViewById(R.id.edit_gender);
        edit_expri=findViewById(R.id.edit_expri);

        user_info_confirm=findViewById(R.id.user_info_confirm);
        user_info_cancel=findViewById(R.id.user_info_cancel);

        name=edit_name.getText().toString();
        phone=edit_phone.getText().toString();
        addr=edit_address.getText().toString();
        age=edit_age.getText().toString();
        gender=edit_gender.getText().toString();
        expri=edit_expri.getText().toString();

        user_info_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mThreadHandler.post(r1);
            }
        });


    }

    //工作名稱 r1 的工作內容

    private Runnable r1=new Runnable () {

        public void run() {
            line = webservice.update_user_info(name,phone,addr,age,gender,expri);
            //請經紀人指派工作名稱 r，給工人做
            mUI_Handler.post(r2);

        }

    };

    //工作名稱 r2 的工作內容

    private Runnable r2=new Runnable () {

        public void run() {

            Intent intent=new Intent(personal_info_edit.this, personal_info.class);
            //把備註傳回栽培曆首頁
            intent.putExtra("ETname",name);
            intent.putExtra("ETphone",phone);
            intent.putExtra("ETaddr",addr);
            intent.putExtra("ETage",age);
            intent.putExtra("ETgender",gender);
            intent.putExtra("ETexpri",expri);
            startActivity(intent);
        }

    };



    @Override
    protected void onDestroy() {
        super.onDestroy();

        //移除工人上的工作
        if (mThreadHandler != null) {
            mThreadHandler.removeCallbacks(r1);
        }
        //解聘工人 (關閉Thread)
        if (mThread != null) {
            mThread.quit();
        }
    }
}
