package com.example.hy.calendar;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hy.GlobalVariable;
import com.example.hy.R;
import com.example.hy.webservice;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class calendar extends AppCompatActivity {
    CalendarView cv1,QQ;
    TextView tv1,tv2,act_tv;
    String[] listItems,everyvege,sl;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();
    Button edit,update,cancel;
    Spinner spi;
    Switch swi;
    Intent intent;
    private static  final  int REQUEST_CODE=1;
    GlobalVariable action_item_value,action_item_value2;
    String date,decide_edit="edit",cal_data,Allvege="",setdate,pictureurl,Select_vege_name,gmail;
    //找到UI工人的經紀人，這樣才能派遣工作  (找到顯示畫面的UI Thread上的Handler)
    private Handler mUI_Handler = new Handler();
    //宣告特約工人的經紀人
    private Handler mThreadHandler;
    //宣告特約工人
    private HandlerThread mThread;

    ImageView calendar_picture;
    Bitmap myBitmap,nopicture;
    URL url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_calendar);

        //聘請一個特約工人，有其經紀人派遣其工人做事 (另起一個有Handler的Thread)
        mThread = new HandlerThread("");
        //讓Worker待命，等待其工作 (開啟Thread)
        mThread.start();
        //找到特約工人的經紀人，這樣才能派遣工作 (找到Thread上的Handler)
        mThreadHandler=new Handler(mThread.getLooper());

        calendar_picture = findViewById(R.id.calendar_picture);



        edit =(Button)findViewById(R.id.edit);
        update = (Button)findViewById(R.id.update);
        cancel = (Button)findViewById(R.id.cancel);
        cv1 = (CalendarView)findViewById(R.id.CV01);
        tv1 = (TextView)findViewById(R.id.TV01);
        tv2 = (TextView)findViewById(R.id.TV02);
        act_tv = (TextView)findViewById(R.id.act_tv);
        action_item_value= (GlobalVariable)getApplicationContext();
        action_item_value2= (GlobalVariable)getApplicationContext();
        spi = (Spinner)findViewById(R.id.spinner);
        swi = (Switch) findViewById(R.id.switch1);
        Calendar calendar= Calendar.getInstance();
        date = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date());
        tv1.setText(date);

        swi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(calendar.this);
                    builder.setTitle("提示功能☆即將推出，敬請期待!");
                    builder.setMessage("查看引用的栽培日曆，參考種植該作物第幾天時該做什麼事!");
                    builder.setPositiveButton("好", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    android.app.AlertDialog dialog=builder.create();
                    dialog.show();
                } else {

                }
            }
        });


//        final String[] lunch = {"玉米","芭樂","番茄","小白菜"};
//        ArrayAdapter<String> lunchList = new ArrayAdapter<>(calendar.this,
//                R.layout.login2_select_dropdown_item,
//                lunch);
//
//        spi.setAdapter(lunchList);
        spi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(calendar.this, "你選的是" + lunch[position], Toast.LENGTH_SHORT).show();
                Select_vege_name=spi.getSelectedItem().toString();
                action_item_value.setSelect_vege_name(Select_vege_name);
                mThreadHandler.post(r3);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        listItems = getResources().getStringArray(R.array.action_item);
        checkedItems = new boolean[listItems.length];

        cv1.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int y, int m, int dm) {
                date = y+"/"+(m+1)+"/"+dm;
                tv1.setText(date);
                mThreadHandler.post(r3);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(calendar.this,calendar_memo.class);
                //給message起一個名字，並傳給另一個activity
                intent2.putExtra("EXTRA_DATE",date);
                intent2.putExtra("decide_edit",decide_edit);
                startActivity(intent2);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(calendar.this, calendar_memo2.class);
                intent.putExtra("EXTRA_DATE",date);
                startActivity(intent);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(calendar.this);
                builder.setTitle("注意!");
                builder.setMessage("確定要刪除"+date+"的內容嗎?");
                builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mThreadHandler.post(r1);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        intent = getIntent();
        //把傳送進來的String型別的Message的值賦給新的變數message
        String message = intent.getStringExtra("EXTRA_MESSAGE");
        //所勾選的活動
        String s = intent.getStringExtra("EXTRA_Activity");
        //把佈局檔案中的文字框和textview連結起來
        //在textview中顯示出來message
        tv2.setText(message);
        if((!tv2.getText().equals("")) || tv2.getText().equals("")) {
            act_tv.setText(s);
            Toast.makeText(calendar.this, "OK", Toast.LENGTH_SHORT).show();
        }
    }

    //工作名稱 r1 的工作內容

    private Runnable r1=new Runnable () {

        public void run() {
            webservice.Delete(date);
            //請經紀人指派工作名稱 r，給工人做
            mUI_Handler.post(r2);

        }

    };

    //工作名稱 r2 的工作內容

    private Runnable r2=new Runnable () {

        public void run() {
            tv2.setText("");
            act_tv.setText("");
            calendar_picture.setImageBitmap(nopicture);
        }

    };
    private Runnable r3=new Runnable () {

        public void run() {

            gmail=action_item_value.getUser_gmail();
            cal_data = webservice.select_cal(gmail,Select_vege_name,date);
            Log.v("test","cal_data: "+cal_data);
            //請經紀人指派工作名稱 r，給工人做
            mUI_Handler.post(r4);

        }

    };
    //工作名稱 r1 的工作內容

    private Runnable r4=new Runnable () {

        public void run() {

            if(!cal_data.equals("還未新增資料"))
            {
                sl = cal_data.split("%");
                if(sl[0].equals(""))
                {
                    act_tv.setText("");
                }
                else
                {
                    act_tv.setText(sl[0]);
                }
                if(sl[1].equals(""))
                {
                    tv2.setText("");
                }
                else
                {
                    tv2.setText(sl[1]);
                }
                if(sl[2].equals(""))
                {
                    calendar_picture.setImageBitmap(nopicture);
                }
                else
                {
                    pictureurl=sl[2];
                    try
                    {
                        url = new URL(pictureurl);
                        if(url.toString().contains("http"))
                        {
                            mThreadHandler.post(r5);
                        }

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }

            }
            else
            {
                tv2.setText("");
                act_tv.setText("");
                calendar_picture.setImageBitmap(nopicture);
                Toast.makeText(calendar.this, "未新增資料", Toast.LENGTH_SHORT).show();

            }
        }

    };




    private Runnable r5=new Runnable () {

        public void run() {
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                myBitmap = BitmapFactory.decodeStream(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //請經紀人指派工作名稱 r，給工人做
            mUI_Handler.post(r6);

        }

    };

    private Runnable r6=new Runnable () {

        public void run() {

            calendar_picture.setImageBitmap(myBitmap);
        }

    };

    private Runnable r7=new Runnable () {

        public void run() {

            String email=action_item_value.getUser_gmail();
            Allvege=webservice.Select_user_vege(email);
            mUI_Handler.post(r8);
        }

    };

    private Runnable r8=new Runnable () {

        public void run() {

            everyvege = Allvege.split("%");
            if (everyvege.equals("")) {
                String[] novege = {"未新增作物"};
                final String[] lunch = novege;
                ArrayAdapter<String> lunchList = new ArrayAdapter<>(calendar.this,
                        R.layout.login2_select_dropdown_item,
                        lunch);

                spi.setAdapter(lunchList);
            } else {
                final String[] lunch = everyvege;
                ArrayAdapter<String> lunchList = new ArrayAdapter<>(calendar.this,
                        R.layout.login2_select_dropdown_item,
                        lunch);

                spi.setAdapter(lunchList);
            }

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //移除工人上的工作
        if (mThreadHandler != null) {
            mThreadHandler.removeCallbacks(r1);
            mThreadHandler.removeCallbacks(r3);
            mThreadHandler.removeCallbacks(r5);
            mThreadHandler.removeCallbacks(r7);
        }
        //解聘工人 (關閉Thread)
        if (mThread != null) {
            mThread.quit();
        }
    }
}
