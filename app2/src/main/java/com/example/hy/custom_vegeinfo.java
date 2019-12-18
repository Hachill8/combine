package com.example.hy;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;

public class custom_vegeinfo extends AppCompatActivity {

    //找到UI工人的經紀人，這樣才能派遣工作  (找到顯示畫面的UI Thread上的Handler)
    private Handler mUI_Handler = new Handler();
    //宣告特約工人的經紀人
    private Handler mThreadHandler;
    //宣告特約工人
    private HandlerThread mThread;

    ImageView madd_img,mtool_pic1,mtool_pic2,mtool_pic3,mtool_pic4;
    EditText mvege_name,medit_step,medit_container,medit_soil,medit_place,medit_water,medit_fertilizer,
            medit_bug,medit_harvest,mtool_name1,mtool_name2,mtool_name3,mtool_name4;
    String add_img,tool_pic1,tool_pic2,tool_pic3,tool_pic4
    ,vege_name,edit_step,edit_container,edit_soil,edit_place,edit_water,edit_fertilizer,
    edit_bug,edit_harvest,tool_name1,tool_name2,tool_name3,tool_name4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_vegeinfo);

        //聘請一個特約工人，有其經紀人派遣其工人做事 (另起一個有Handler的Thread)
        mThread = new HandlerThread("");
        //讓Worker待命，等待其工作 (開啟Thread)
        mThread.start();
        //找到特約工人的經紀人，這樣才能派遣工作 (找到Thread上的Handler)
        mThreadHandler=new Handler(mThread.getLooper());

        madd_img=findViewById(R.id.add_img);
        mvege_name=findViewById(R.id.vege_name);
        medit_step=findViewById(R.id.edit_step);
        medit_container=findViewById(R.id.edit_container);
        medit_soil=findViewById(R.id.edit_soil);
        medit_place=findViewById(R.id.edit_place);
        medit_water=findViewById(R.id.edit_water);
        medit_fertilizer=findViewById(R.id.edit_fertilizer);
        medit_bug=findViewById(R.id.edit_bug);
        medit_harvest=findViewById(R.id.edit_harvest);
        mtool_pic1=findViewById(R.id.tool_pic1);
        mtool_pic2=findViewById(R.id.tool_pic2);
        mtool_pic3=findViewById(R.id.tool_pic3);
        mtool_pic4=findViewById(R.id.tool_pic4);
        mtool_name1=findViewById(R.id.tool_name1);
        mtool_name2=findViewById(R.id.tool_name2);
        mtool_name3=findViewById(R.id.tool_name3);
        mtool_name4=findViewById(R.id.tool_name4);

        vege_name=mvege_name.getText().toString();
        edit_step=medit_step.getText().toString();
        edit_container=medit_container.getText().toString();
        edit_soil=medit_soil.getText().toString();
        edit_place=medit_place.getText().toString();
        edit_water=medit_water.getText().toString();
        edit_fertilizer=medit_fertilizer.getText().toString();
        edit_bug=medit_bug.getText().toString();
        edit_harvest=medit_harvest.getText().toString();
        tool_name1=mtool_name1.getText().toString();
        tool_name2=mtool_name2.getText().toString();
        tool_name3=mtool_name3.getText().toString();
        tool_name4=mtool_name4.getText().toString();

    }

    //工作名稱 r1 的工作內容

    private Runnable r1=new Runnable () {

        public void run() {
 //madd_img,tool_pic1~還沒寫好
            webservice.Insert_custom_vegeinfo(add_img,vege_name,edit_step,edit_container,edit_soil,edit_place,edit_water,
                    edit_fertilizer,edit_bug,edit_harvest,tool_pic1,tool_pic2,tool_pic3,tool_pic4,tool_name1,tool_name2,tool_name3,tool_name4);

        }

    };

    //工作名稱 r2 的工作內容

    private Runnable r2=new Runnable () {

        public void run() {

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
