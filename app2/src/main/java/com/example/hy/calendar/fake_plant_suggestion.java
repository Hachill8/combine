package com.example.hy.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.hy.R;

public class fake_plant_suggestion extends AppCompatActivity {

    CalendarView cv1;
    TextView tv1,tv2,mTv;
    ImageButton imab2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fake_plant_suggestion);

        cv1 = (CalendarView)findViewById(R.id.fakeCV01);
        tv2 = (TextView)findViewById(R.id.fakeTV02);
        mTv = (TextView)findViewById(R.id.faketv01);
        imab2 = (ImageButton) findViewById(R.id.imageButton2);

        imab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fake_plant_suggestion.this, calendar.class);
                startActivity(intent);
            }
        });

    }
}
