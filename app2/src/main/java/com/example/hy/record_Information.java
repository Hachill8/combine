package com.example.hy;

import android.graphics.Canvas;
import android.graphics.Outline;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class record_Information extends AppCompatActivity
{
    RelativeLayout RL;
    int index = 0;
    boolean fg=true;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_information);

        // 取得 RinearLayout 物件
        RL = (RelativeLayout) findViewById(R.id.record_information_RL);

        if(fg==true) {
            createView();
        }
    }

    private void createView()
    {
        fg=false;
        ImageButton b1 = new ImageButton(this);
        TextView period = new TextView(this);
        TextView date = new TextView(this);
        ImageView img = new ImageView(this);
        TextView tx1 = new TextView(this);
        TextView tx2 = new TextView(this);
        TextView tx3 = new TextView(this);


        b1.setBackgroundResource(R.drawable.record_back);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = index +200;
                Toast toast = Toast.makeText(record_Information.this,"index: "+index,Toast.LENGTH_LONG);
                fg=true;
                toast.show();
            }
        });
        plantinfo(b1,120,260,0,0);
        period.setText("幼苗期");
        period.setTextSize(20);
        period.setBackgroundResource(R.drawable.period);

//        period.setOutlineProvider(new ViewOutlineProvider() {
//            @Override
//            public void getOutline(View view, Outline outline) {
//                outline.setRoundRect(10,10,10,10,10);
//            }
//        });

        plantinfo(period,160,300,0,0);


        date.setText("19/04/20 ~ 19/04/24");
        date.setTextSize(20);
        date.setBackgroundResource(R.drawable.period);
        plantinfo(date,370,300,0,0);


        img.setImageResource(R.drawable.imv03);
        plantinfo(img,190,400,0,0);


        tx1.setText("澆水25次");
        tx1.setWidth(300);
        tx1.setHeight(60);
        tx1.setTextSize(15);
        tx1.setTextColor(getResources().getColor(R.color.white));
        tx1.setBackgroundResource(R.drawable.record_textview);
        tx1.setPadding(3,0,0,0);            //疑似沒作用
        plantinfo(tx1,620,430,0,0);


        tx2.setText("施肥6次");
        tx2.setWidth(300);
        tx2.setHeight(60);
        tx2.setTextSize(15);
        tx2.setTextColor(getResources().getColor(R.color.white));
        tx2.setBackgroundResource(R.drawable.record_textview);
        tx2.setPadding(3,0,0,0);
        plantinfo(tx2,620,500,0,0);


        tx3.setText("間拔2次");
        tx3.setWidth(300);
        tx3.setHeight(60);
        tx3.setTextSize(15);
        tx3.setTextColor(getResources().getColor(R.color.white));
        tx3.setBackgroundResource(R.drawable.record_textview);
        tx3.setPadding(3,0,0,0);
        plantinfo(tx3,620,570,0,0);



        RL.addView(b1);
        RL.addView(period);
        RL.addView(date);
        RL.addView(img);
        RL.addView(tx1);
        RL.addView(tx2);
        RL.addView(tx3);
    }

    private void plantinfo(View view,int left,int top,int right,int bottom)
    {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(left+index,top+index,right,bottom);

        Toast toast = Toast.makeText(record_Information.this,"index2: "+index,Toast.LENGTH_LONG);

        toast.show();
        view.setLayoutParams(layoutParams);
    }

}