package com.example.hy.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hy.GlobalVariable;
import com.example.hy.R;
import com.example.hy.webservice;

public class calendar_memo extends AppCompatActivity {

    EditText memo;
    TextView date_tv;
    String date;
    GlobalVariable action_item_value,action_item_value2;
    Button cancel;
    CheckBox cb1,cb2,cb3,cb4,cb5,cb6,cb7,cb8;
    String message;
    String s="",decide_action,decide_action2;

    //找到UI工人的經紀人，這樣才能派遣工作  (找到顯示畫面的UI Thread上的Handler)
    private Handler mUI_Handler = new Handler();
    //宣告特約工人的經紀人
    private Handler mThreadHandler;
    //宣告特約工人
    private HandlerThread mThread;
    String line,bbb;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_memo);

        //聘請一個特約工人，有其經紀人派遣其工人做事 (另起一個有Handler的Thread)
        mThread = new HandlerThread("");

        //讓Worker待命，等待其工作 (開啟Thread)
        mThread.start();

        //找到特約工人的經紀人，這樣才能派遣工作 (找到Thread上的Handler)
        mThreadHandler=new Handler(mThread.getLooper());

        memo = findViewById(R.id.edit_message1);
        date_tv = findViewById(R.id.date_tv);
        cancel = findViewById(R.id.cancel);

        cb1=findViewById(R.id.cb1);
        cb2=findViewById(R.id.cb2);
        cb3=findViewById(R.id.cb3);
        cb4=findViewById(R.id.cb4);
        cb5=findViewById(R.id.cb5);
        cb6=findViewById(R.id.cb6);
        cb7=findViewById(R.id.cb7);
        cb8=findViewById(R.id.cb8);

        action_item_value= (GlobalVariable)getApplicationContext();
        action_item_value2= (GlobalVariable)getApplicationContext();
        action_item_value2.setAction_item(action_item_value.getAction_item());

        //cb1到cb8是活動被勾選時會有顏色變化
        cb1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(cb1.isChecked()){
                    cb1.setTextColor(getResources().getColor(R.color.Green1));
                }else{
                    cb1.setTextColor(getResources().getColor(R.color.black));
                }
            }
        });
        cb2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(cb2.isChecked()){
                    cb2.setTextColor(getResources().getColor(R.color.Green1));
                }else{
                    cb2.setTextColor(getResources().getColor(R.color.black));
                }
            }
        });
        cb3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(cb3.isChecked()){
                    cb3.setTextColor(getResources().getColor(R.color.Green1));
                }else{
                    cb3.setTextColor(getResources().getColor(R.color.black));
                }
            }
        });
        cb4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(cb4.isChecked()){
                    cb4.setTextColor(getResources().getColor(R.color.Green1));
                }else{
                    cb4.setTextColor(getResources().getColor(R.color.black));
                }
            }
        });
        cb5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(cb5.isChecked()){
                    cb5.setTextColor(getResources().getColor(R.color.Green1));
                }else{
                    cb5.setTextColor(getResources().getColor(R.color.black));
                }
            }
        });
        cb6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(cb6.isChecked()){
                    cb6.setTextColor(getResources().getColor(R.color.Green1));
                }else{
                    cb6.setTextColor(getResources().getColor(R.color.black));
                }
            }
        });
        cb7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(cb7.isChecked()){
                    cb7.setTextColor(getResources().getColor(R.color.Green1));
                }else{
                    cb7.setTextColor(getResources().getColor(R.color.black));
                }
            }
        });
        cb8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(cb8.isChecked()){
                    cb8.setTextColor(getResources().getColor(R.color.Green1));
                }else{
                    cb8.setTextColor(getResources().getColor(R.color.black));
                }
            }
        });


        Intent intent2 = getIntent();
        //把傳送進來的String型別的date的值賦給新的變數
        date = intent2.getStringExtra("EXTRA_DATE");
        decide_action = intent2.getStringExtra("decide_edit");
        //在最上面textview中顯示出日期
        date_tv.setText(date);


        Log.v("test",action_item_value.getAction_item());
        Intent intent = getIntent();
        //把傳送進來的String型別的Message的值賦給新的變數message
        String message = intent.getStringExtra("EXTRA_MESSAGE");
        decide_action2 = intent.getStringExtra("decide_update");
        //在textview中顯示出來message
        memo.setText(message);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(calendar_memo.this,calendar.class);
                startActivity(intent);
            }
        });
    }
    public void sendMessage(View view)
    {
        //String s="";
        if(cb1.isChecked()){
            s += "播種  ";
        }
        if(cb2.isChecked()){
            s += "育苗  ";
        }
        if(cb3.isChecked()){
            s += "定植  ";
        }
        if(cb4.isChecked()){
            s += "澆水  ";
        }
        if(cb5.isChecked()){
            s += "施肥  ";
        }
        if(cb6.isChecked()){
            s += "鋤草  ";
        }
        if(cb7.isChecked()){
            s += "除病蟲  ";
        }
        if(cb8.isChecked()){
            s += "收成  ";
        }
        intent = new Intent(calendar_memo.this,calendar.class);
        //宣告一個編輯框和佈局檔案中id為edit_message的編輯框連結起來。
        //把編輯框獲取的文字賦值給String型別的message
        //String message = ed.getText().toString();

        message = memo.getText().toString();

        //請經紀人指派工作名稱 r，給工人做
        mThreadHandler.post(r1);

        //dbUtil.insert(date,s,ed.getText().toString());


    }

    //工作名稱 r1 的工作內容

    private Runnable r1=new Runnable () {

        public void run() {


            line = webservice.Insert_calendar(date,s,message);

            //請經紀人指派工作名稱 r，給工人做
            mUI_Handler.post(r2);

        }

    };

    //工作名稱 r2 的工作內容

    private Runnable r2=new Runnable () {

        public void run() {

            //給message起一個名字，並傳給另一個activity
            intent.putExtra("EXTRA_MESSAGE",message);
            intent.putExtra("EXTRA_MESSAGE2",s);
            //啟動意圖
            Log.v("test1","line是: "+line);
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
