package com.example.hy.search;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.hy.GlobalVariable;
import com.example.hy.R;
import com.example.hy.home.home2;
import com.example.hy.webservice;

import org.w3c.dom.Text;

public class VegeInfo extends AppCompatActivity {

    GlobalVariable vege;
    Button start_plant,vege_info;
    Dialog variety_info;
    String line, vegeinfo_name;
    TextView step/**  小撇步  **/,
            container/**  容器 **/,
            soil/**  土壤 **/,
            place/**  放置場所**/,
            water/**  澆水 **/,
            fertilizer/**  肥料 **/,
            bug/**  病蟲害 **/,
            harvest/**  收穫標準 **/,
            vege_name /**  菜名 **/;


    //找到UI工人的經紀人，這樣才能派遣工作  (找到顯示畫面的UI Thread上的Handler)

    private Handler mUI_Handler = new Handler();

    //宣告特約工人的經紀人

    private Handler mThreadHandler;

    //宣告特約工人

    private HandlerThread mThread;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate( savedInstanceState );
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView( R.layout.activity_vege_info );

        ViewPager viewPager=findViewById( R.id.viewpager );
        ImageAdapter adapter=new ImageAdapter( this );
        viewPager.setAdapter( adapter );

        // 各欄位宣告

        step = (TextView) findViewById(R.id.step);
        container = (TextView) findViewById(R.id.container);
        soil = (TextView) findViewById(R.id.soil);
        place = (TextView) findViewById(R.id.place);
        water = (TextView) findViewById(R.id.water);
        fertilizer = (TextView) findViewById(R.id.fertilizer);
        bug = (TextView) findViewById(R.id.bug);
        harvest = (TextView) findViewById(R.id.harvest);
        vege_name = (TextView) findViewById(R.id.vege_name);

        //globalvariable變數
        vege = (GlobalVariable)getApplicationContext();
        vegeinfo_name=vege.getWord();

        //聘請一個特約工人，有其經紀人派遣其工人做事 (另起一個有Handler的Thread)

        mThread = new HandlerThread("");

        //讓Worker待命，等待其工作 (開啟Thread)

        mThread.start();

        //找到特約工人的經紀人，這樣才能派遣工作 (找到Thread上的Handler)

        mThreadHandler=new Handler(mThread.getLooper());


        //請經紀人指派工作名稱 r，給工人做

        mThreadHandler.post(r1);


        variety_info=new Dialog(this);

        start_plant=(Button)findViewById(R.id.start_plant);
        start_plant.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent b = new Intent(VegeInfo.this, home2.class);
                startActivity(b);
            }
        } );

        vege_info=(Button)findViewById(R.id.vege_info);
        vege_info.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ShowPopUp_vege_info();
            }
        } );


    }

    private void ShowPopUp_vege_info()
    {
        variety_info.setContentView(R.layout.variety_info);


        variety_info.getWindow().setBackgroundDrawable(new ColorDrawable( Color.TRANSPARENT));
        variety_info.show();
    }

    //工作名稱 r1 的工作內容

    private Runnable r1=new Runnable () {

        public void run() {

            if(!vegeinfo_name.equals("")) {
                line = webservice.VegeInfo_WS(vegeinfo_name);
            }

            //請經紀人指派工作名稱 r，給工人做

            mUI_Handler.post(r2);

        }

    };

    //工作名稱 r2 的工作內容

    private Runnable r2=new Runnable () {

        public void run() {

                        String can = "can't not found";

                        if (!line.equals("can't not found"))
                        {
                            String[] split_line = line.split("%");

                            Log.v("test", "0. " + split_line[0] + "1. " + split_line[1] + "2. " + split_line[2] + "3. " + split_line[3]
                                    + "4. " + split_line[4] + "5. " + split_line[5] + "6. " + split_line[6] + "7. " + split_line[7]);

                            vege_name.setText(vegeinfo_name);
                            step.setText(split_line[0]);
                            container.setText(split_line[1]);
                            soil.setText(split_line[2]);
                            place.setText(split_line[3]);
                            water.setText(split_line[4]);
                            fertilizer.setText(split_line[5]);
                            bug.setText(split_line[6]);
                            harvest.setText(split_line[7]);
                        }
                        else if (line.equals(can))
                        {
                            vege_name.setText(vegeinfo_name);
                            step.setText(can);
                            container.setText(can);
                            soil.setText(can);
                            place.setText(can);
                            water.setText(can);
                            fertilizer.setText(can);
                            bug.setText(can);
                            harvest.setText(can);
                        }
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


//    public void vegeinfo_ws() {
//        final String finalName = vegeinfo_name;
//        if(!vegeinfo_name.equals("")) {
//            Thread thread = new Thread() {
//                public void run(){
//                        String line = webservice.VegeInfo_WS(finalName);
//                        String can = "can't not found";
//                        String name = finalName;
//                        if (!line.equals("can't not found")){
//                            String[] split_line = line.split("%");
////                          vege_name.setText(name);
//                            step.setText(split_line[0]);       container.setText(split_line[1]);
//                            soil.setText(split_line[2]);       place.setText(split_line[3]);
//                            water.setText(split_line[4]);      fertilizer.setText(split_line[5]);
//                            bug.setText(split_line[6]);        harvest.setText(split_line[7]);
//                        } else if (line.equals(can)){
//                            vege_name.setText(name);     step.setText(can);
//                            container.setText(can);     soil.setText(can);
//                            place.setText(can);     water.setText(can);
//                            fertilizer.setText(can);     bug.setText(can);     harvest.setText(can);
//                        }} };     thread.start(); } }

