package com.example.hy;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class home extends AppCompatActivity {

    Dialog person_info;
    ImageButton person_info_btn;
    CardView home_search_bar;
    Button test_fab_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        person_info=new Dialog(this);


        person_info_btn=(ImageButton) findViewById(R.id.person_info_btn);
        person_info_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ShowPopUp_person_info();
            }
        } );

        home_search_bar=(CardView) findViewById(R.id.home_search_bar);
        home_search_bar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent a = new Intent(home.this,search.class);
                startActivity(a);

            }
        } );




    }

    private void ShowPopUp_person_info()
    {

        person_info.getWindow().setBackgroundDrawable(new ColorDrawable( Color.TRANSPARENT));
        person_info.show();
    }



}
