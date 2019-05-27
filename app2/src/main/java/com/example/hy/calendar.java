package com.example.hy;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class calendar extends AppCompatActivity {
    CalendarView cv1;
    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7;
    Button edit,addact;
    ImageButton imb1,imb2,imb3,imb4,imb5;
    private static  final  int REQUEST_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        cv1 = (CalendarView)findViewById(R.id.CV01);
        tv1 = (TextView)findViewById(R.id.TV01);
        tv2 = (TextView)findViewById(R.id.TV02);
        tv3 = (TextView)findViewById(R.id.TV03);
        tv4 = (TextView)findViewById(R.id.TV04);
        tv5 = (TextView)findViewById(R.id.TV05);
        tv6 = (TextView)findViewById(R.id.TV06);
        tv7 = (TextView)findViewById(R.id.TV07);
        edit =(Button)findViewById(R.id.edit);
        imb1 =(ImageButton) findViewById(R.id.IMB01);
        imb2 =(ImageButton) findViewById(R.id.IMB02);
        imb3 =(ImageButton) findViewById(R.id.IMB03);
        imb4 =(ImageButton) findViewById(R.id.IMB04);
        imb5 =(ImageButton) findViewById(R.id.IMB05);
        addact =(Button)findViewById(R.id.addact);

        cv1.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int y, int m, int dm) {
                String date = y+"年"+(m+1)+"月"+dm+"日";
                tv1.setText(date);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(calendar.this,calendar_memo.class);
                startActivity(intent);
            }
        });
        addact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(calendar.this,addactivity.class);
                startActivity(intent);
            }
        });
        Intent intent = getIntent();
        //把傳送進來的String型別的Message的值賦給新的變數message
        String message = intent.getStringExtra("EXTRA_MESSAGE");
        //把佈局檔案中的文字框和textview連結起來
        //在textview中顯示出來message
        tv2.setText(message);
        imb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText input = new EditText(calendar.this);
                AlertDialog dialog = new AlertDialog.Builder(calendar.this)
                        .setTitle("備註:")
                        .setView(input)
                        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String inputName = input.getText().toString();
                                Log.d("Main", "成功加入");
                                tv3.setText("#"+inputName);
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Log.d("Main", "click cancel");
                            }
                        })
                        .create();
                dialog.show();
            }
        });
        imb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText input = new EditText(calendar.this);
                AlertDialog dialog = new AlertDialog.Builder(calendar.this)
                        .setTitle("備註:")
                        .setView(input)
                        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String inputName = input.getText().toString();
                                Log.d("Main", "成功加入");
                                tv4.setText("#"+inputName);
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Log.d("Main", "click cancel");
                            }
                        })
                        .create();
                dialog.show();
            }
        });
        imb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText input = new EditText(calendar.this);
                AlertDialog dialog = new AlertDialog.Builder(calendar.this)
                        .setTitle("備註:")
                        .setView(input)
                        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String inputName = input.getText().toString();
                                Log.d("Main", "成功加入");
                                tv5.setText("#"+inputName);
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Log.d("Main", "click cancel");
                            }
                        })
                        .create();
                dialog.show();
            }
        });
        imb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText input = new EditText(calendar.this);
                AlertDialog dialog = new AlertDialog.Builder(calendar.this)
                        .setTitle("備註:")
                        .setView(input)
                        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String inputName = input.getText().toString();
                                Log.d("Main", "成功加入");
                                tv6.setText("#"+inputName);
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Log.d("Main", "click cancel");
                            }
                        })
                        .create();
                dialog.show();
            }
        });
        imb5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText input = new EditText(calendar.this);
                AlertDialog dialog = new AlertDialog.Builder(calendar.this)
                        .setTitle("備註:")
                        .setView(input)
                        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String inputName = input.getText().toString();
                                Log.d("Main", "成功加入");
                                tv7.setText("#"+inputName);
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Log.d("Main", "click cancel");
                            }
                        })
                        .create();
                dialog.show();
            }
        });

    }
    public void sendMessage(View v)
    {
        Intent intent = new Intent(calendar.this,calendar_memo.class);
        //宣告一個編輯框和佈局檔案中id為edit_message的編輯框連結起來。
        //把編輯框獲取的文字賦值給String型別的message
        String message = tv2.getText().toString();
        //給message起一個名字，並傳給另一個activity
        intent.putExtra("EXTRA_MESSAGE",message);
        //啟動意圖
        startActivity(intent);
    }
}
