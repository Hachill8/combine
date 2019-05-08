package com.example.hy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

public class calendar extends AppCompatActivity {
    CalendarView cv1;
    TextView tv1,tv2;
    Button edit,addact;
    private static  final  int REQUEST_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        cv1 = (CalendarView)findViewById(R.id.CV01);
        tv1 = (TextView)findViewById(R.id.TV01);
        tv2 = (TextView)findViewById(R.id.TV2);
        edit =(Button)findViewById(R.id.edit);
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
                Intent intent = new Intent(calendar.this,cal_edittext.class);
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
    }
}
