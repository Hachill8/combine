package com.example.hy.search;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


import com.example.hy.GlobalVariable;
import com.example.hy.R;
import com.example.hy.home.home2;

public class choose_vege extends AppCompatActivity //implements View.OnClickListener
{

    CardView vege_card_view1,vege_card_view2,vege_card_view3,vege_card_view4;
    TextView vege1_id,vege2_id,vege3_id,vege4_id;
    GlobalVariable vege_name;
    Button To_search;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate( savedInstanceState );

        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView( R.layout.forum_add_new_post );

//        vege_card_view1=(CardView)findViewById( R.id.vege_card_view1 );
//        vege_card_view2=(CardView)findViewById( R.id.vege_card_view2 );
//        vege_card_view3=(CardView)findViewById( R.id.vege_card_view3 );
//        vege_card_view4=(CardView)findViewById( R.id.vege_card_view4 );
//        vege1_id=(TextView)findViewById(R.id.vege1_id);
//        vege2_id=(TextView)findViewById(R.id.vege2_id);
//        vege3_id=(TextView)findViewById(R.id.vege3_id);
//        vege4_id=(TextView)findViewById(R.id.vege4_id);
//
//        To_search=(Button)findViewById(R.id.to_search);
//
//        vege_card_view1.setOnClickListener(this);
//        vege_card_view2.setOnClickListener(this);
//        vege_card_view3.setOnClickListener(this);
//        vege_card_view4.setOnClickListener(this);
//        To_search.setOnClickListener(this);
//
//
//        vege_name  = (GlobalVariable)getApplicationContext();
//
//
//    }
//
//    @Override
//    public void onClick(View v)
//    {
//        Intent i;
//
//        if(v==To_search)
//        {
//            Intent a= new Intent(choose_vege.this, home2.class);
//            startActivity(a);
//        }
//
//
//        switch (v.getId())
//        {
//
//            case R.id.vege_card_view1 :
//                i = new Intent( this,VegeInfo.class );
//                vege_name.setWord("高麗菜");
//
//                startActivity( i );
//                break;
//            case R.id.vege_card_view2 :
//                i = new Intent( this,VegeInfo.class );
//                vege_name.setWord("空心菜");
//                startActivity( i );
//                break;
//            case R.id.vege_card_view3 :
//                i = new Intent( this,VegeInfo.class );
//                vege_name.setWord("秋葵");
//                startActivity( i );
//                break;
//            case R.id.vege_card_view4 :
//                i = new Intent( this,VegeInfo.class );
//                vege_name.setWord("紅蘿蔔");
//                startActivity( i );
//                break;
//             default:break;
//        }
    }
}
