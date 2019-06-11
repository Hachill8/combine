package com.example.hy;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class VegeInfo extends AppCompatActivity {

    Button start_plant,vege_info;
    Dialog variety_info;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate( savedInstanceState );
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView( R.layout.activity_vege_info );

        ViewPager viewPager=findViewById( R.id.viewpager );
        ImageAdapter adapter=new ImageAdapter( this );
        viewPager.setAdapter( adapter );

        variety_info=new Dialog(this);

        start_plant=(Button)findViewById(R.id.start_plant);
        start_plant.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent b = new Intent(VegeInfo.this,home2.class);
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



}
