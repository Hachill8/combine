package com.example.hy.search;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hy.AutoSplitTextView;
import com.example.hy.GlobalVariable;
import com.example.hy.R;
import com.example.hy.calendar.choose_calendar;
import com.example.hy.home.home2;
import com.example.hy.webservice;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class VegeInfo extends AppCompatActivity {

    GlobalVariable vege
            ,vege_home,gl;     //首頁作物照片(暫時)
    Button start_plant,choose_calendar;
    Dialog variety_info;
    String line="can't not found", vegeinfo_name,setdate,gmail;
    AutoSplitTextView step/**  小撇步  **/,
            container/**  容器 **/,
            soil/**  土壤 **/,
            place/**  放置場所**/,
            water/**  澆水 **/,
            fertilizer/**  肥料 **/,
            bug/**  病蟲害 **/,
            harvest/**  收穫標準 **/,
            vege_name /**  菜名 **/;





     ImageView imageview;
     String img_result; //圖片字串

//     ImageAdapter imgadapter;
//     //加入圖片用
//
//     ViewPager viewPager;


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

//        viewPager=findViewById( R.id.viewpager );
//        imgadapter=new ImageAdapter( this);
//        //設定圖片的位置
//        viewPager.setAdapter( imgadapter );

        imageview = (ImageView)findViewById (R.id.vege_img);

        // 各欄位宣告

        step =  findViewById(R.id.step);
        container =  findViewById(R.id.container);
        soil =  findViewById(R.id.soil);
        place =  findViewById(R.id.place);
        water =  findViewById(R.id.water);
        fertilizer =  findViewById(R.id.fertilizer);
        bug =  findViewById(R.id.bug);
        harvest =  findViewById(R.id.harvest);
        vege_name =  findViewById(R.id.vege_name);
        setdate = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date());
        gl= (GlobalVariable)getApplicationContext();
        gmail=gl.getUser_email();

        //globalvariable變數
        vege = (GlobalVariable)getApplicationContext();
        vegeinfo_name=vege.getWord();

        Log.v("test","vege name: "+vegeinfo_name);

        //聘請一個特約工人，有其經紀人派遣其工人做事 (另起一個有Handler的Thread)
        mThread = new HandlerThread("");
        //讓Worker待命，等待其工作 (開啟Thread)
        mThread.start();
        //找到特約工人的經紀人，這樣才能派遣工作 (找到Thread上的Handler)
        mThreadHandler=new Handler(mThread.getLooper());
        //請經紀人指派工作名稱 r，給工人做
        mThreadHandler.post(r1);
        //首頁作物照片(暫時)
        vege_home = (GlobalVariable)getApplicationContext();

        variety_info=new Dialog(this);
        start_plant=(Button)findViewById(R.id.start_plant);
        start_plant.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                vege_home.setVege_image_home(vege_name.getText().toString());
              //  Intent b = new Intent(VegeInfo.this, home_add_vege.class);
                mThreadHandler.post(r3);
                Intent b = new Intent(VegeInfo.this, home2.class);
                startActivity(b);

            }
        } );

        choose_calendar=(Button)findViewById(R.id.choose_calendar);
        choose_calendar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(VegeInfo.this, choose_calendar.class);
                startActivity(i);

//                AlertDialog.Builder builder = new AlertDialog.Builder(VegeInfo.this);
//                builder.setTitle("栽培日曆☆即將推出，敬請期待!");
//                builder.setMessage("可參考相近地區使用者的栽培日曆，查看該作物的栽種事項");
//                builder.setPositiveButton("好", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                AlertDialog dialog=builder.create();
//                dialog.show();
            }
        } );

    }

    //工作名稱 r1 的工作內容

    private Runnable r1=new Runnable () {

        public void run() {

            if(!vegeinfo_name.equals("No_message")) {
                line = webservice.VegeInfo_WS(vegeinfo_name);

                img_result = webservice.downImage(vegeinfo_name);
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
                        //下載照片
            try {

                Bitmap bitmap=null;
                byte[] decode = Base64.decode(img_result,Base64.NO_CLOSE);
                bitmap = BitmapFactory.decodeByteArray(decode, 0, decode.length);
                Log.v("test","bitmap: "+decode.toString());
                imageview.setImageBitmap(bitmap);


            } catch (Exception e) {
                e.printStackTrace();
                Log.v("test","錯誤: "+e.toString());
            }
        }

    };

    private Runnable r3=new Runnable () {

        public void run() {

            webservice.Insert_vege(vegeinfo_name,gmail);

        }

    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //移除工人上的工作
        if (mThreadHandler != null) {
            mThreadHandler.removeCallbacks(r1);
            mThreadHandler.removeCallbacks(r3);
        }
        //解聘工人 (關閉Thread)
        if (mThread != null) {
            mThread.quit();
        }
    }
}

