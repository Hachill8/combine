package com.example.hy.login;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hy.R;
import com.example.hy.select_model;

import static com.example.hy.R.drawable.login2_button_action;

public class login2 extends AppCompatActivity  {
    Button btntomodel,Man,Woman,One_year,Two_year,Three_year;
    int gender=0, //判斷目前選到哪個性別
            experience=0; //判斷目前種植經驗多久
    Spinner County,Age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("test","進入login2最上面");
        super.onCreate(savedInstanceState);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login2);
        Log.v("test","進入login2.xml");


        btntomodel = (Button) findViewById(R.id.btntomodel);
        btntomodel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("test","onClick");
                Intent a = new Intent(login2.this, select_model.class);
                startActivity(a);

            }
        });


        Man = (Button) findViewById(R.id.man);
        Woman = (Button) findViewById(R.id.woman);
        One_year = (Button) findViewById(R.id.one_year);
        Two_year = (Button) findViewById(R.id.two_year);
        Three_year = (Button) findViewById(R.id.three_year);

        Man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gender==2)
                {
                    Woman.setBackground(getResources().getDrawable( R.drawable.gender ));
                    Man.setBackground(getResources().getDrawable( R.drawable.login2_button_action));
                }
                else
                {
                    Man.setBackground(getResources().getDrawable( R.drawable.login2_button_action));
                }
                gender=1;
            }
        });

        Woman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gender==1)
                {
                    Man.setBackground(getResources().getDrawable( R.drawable.gender ));
                    Woman.setBackground(getResources().getDrawable( R.drawable.login2_button_action));
                }
                else
                {
                    Woman.setBackground(getResources().getDrawable( R.drawable.login2_button_action));
                }
                gender=2;
            }
        });

        One_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(experience==2 || experience==3)
                {
                    Two_year.setBackground(getResources().getDrawable( R.drawable.gender ));
                    Three_year.setBackground(getResources().getDrawable( R.drawable.gender ));
                    One_year.setBackground(getResources().getDrawable((login2_button_action)));
                }
                else
                {
                    One_year.setBackground(getResources().getDrawable((login2_button_action)));
                }
                experience=1;
            }
        });

        Two_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(experience==1 || experience==3)
                {
                    One_year.setBackground(getResources().getDrawable( R.drawable.gender ));
                    Three_year.setBackground(getResources().getDrawable( R.drawable.gender ));
                    Two_year.setBackground(getResources().getDrawable((login2_button_action)));
                }
                else
                {
                    Two_year.setBackground(getResources().getDrawable((login2_button_action)));
                }
                experience=2;
            }
        });

        Three_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(experience==2 || experience==1)
                {
                    Two_year.setBackground(getResources().getDrawable( R.drawable.gender ));
                    One_year.setBackground(getResources().getDrawable( R.drawable.gender ));
                    Three_year.setBackground(getResources().getDrawable((login2_button_action)));
                }
                else
                {
                    Three_year.setBackground(getResources().getDrawable((login2_button_action)));
                }
                experience=3;
            }
        });


        County = (Spinner) findViewById(R.id.county);
        Age = (Spinner) findViewById(R.id.age);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(login2.this,
                R.layout.login2_select_dropdown_item,                            //選項資料內容
                getResources().getStringArray(R.array.county_item));
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(login2.this,
                R.layout.login2_select_dropdown_item,                            //選項資料內容
                getResources().getStringArray(R.array.age_item));

        adapter1.setDropDownViewResource(R.layout.login2_select_dropdown_item);



        County.setAdapter(adapter1);
        Age.setAdapter(adapter2);




    }

}
