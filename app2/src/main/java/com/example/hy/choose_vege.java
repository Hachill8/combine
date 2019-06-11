package com.example.hy;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.WindowManager;

public class choose_vege extends AppCompatActivity implements View.OnClickListener
{

    CardView vege_card_view;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView( R.layout.activity_choose_vege );

        vege_card_view=(CardView)findViewById( R.id.vege_card_view );

        vege_card_view.setOnClickListener(this);



    }

    @Override
    public void onClick(View v)
    {
        Intent i;

        switch (v.getId())
        {

            case R.id.vege_card_view:i=new Intent( this,VegeInfo.class );startActivity( i );break;
            default:break;

        }
    }
}
