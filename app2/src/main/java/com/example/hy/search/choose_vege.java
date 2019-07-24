package com.example.hy.search;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;


import com.example.hy.GlobalVariable;
import com.example.hy.R;

public class choose_vege extends AppCompatActivity implements View.OnClickListener
{

    CardView vege_card_view1,vege_card_view2,vege_card_view3;
    TextView vege1_id,vege2_id,vege3_id;
    GlobalVariable vege_name;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView( R.layout.activity_choose_vege );

        vege_card_view1=(CardView)findViewById( R.id.vege_card_view1 );
        vege_card_view2=(CardView)findViewById( R.id.vege_card_view2 );
        vege_card_view3=(CardView)findViewById( R.id.vege_card_view3 );
        vege1_id=(TextView)findViewById(R.id.vege1_id);
        vege2_id=(TextView)findViewById(R.id.vege2_id);
        vege3_id=(TextView)findViewById(R.id.vege3_id);


        vege_card_view1.setOnClickListener(this);
        vege_card_view2.setOnClickListener(this);
        vege_card_view3.setOnClickListener(this);

        vege_name  = (GlobalVariable)getApplicationContext();


    }

    @Override
    public void onClick(View v)
    {
        Intent i;


        switch (v.getId())
        {

            case R.id.vege_card_view1 :
                i = new Intent( this,VegeInfo.class );
                vege_name.setWord("高麗菜");

                startActivity( i );
                break;
            case R.id.vege_card_view2 :
                i = new Intent( this,VegeInfo.class );
                vege_name.setWord("番茄");
                startActivity( i );
                break;
            case R.id.vege_card_view3 :
                i = new Intent( this,VegeInfo.class );
                vege_name.setWord("小蘿蔔");
                startActivity( i );
                break;
             default:break;
        }
    }
}
