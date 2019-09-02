package com.example.hy.record;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hy.R;
import com.example.hy.calendar.calendar;
import com.example.hy.calendar.calendar_memo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class record_Information2 extends AppCompatActivity{
        CalendarView cv1;
        TextView tv1,tv2,mTv;
        String[] listItems;
        boolean[] checkedItems;
        private static  final  int REQUEST_CODE=1;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.record_information2);
            Log.v("test","2");
            cv1 = (CalendarView)findViewById(R.id.CV01);
            tv1 = (TextView)findViewById(R.id.TV01);
            tv2 = (TextView)findViewById(R.id.TV02);
            mTv = (TextView)findViewById(R.id.tv01);

            listItems = getResources().getStringArray(R.array.action_item);
            checkedItems = new boolean[listItems.length];


            cv1.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView view, int y, int m, int dm) {
                    String date = y+"年"+(m+1)+"月"+dm+"日";
                    tv1.setText(date);
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
