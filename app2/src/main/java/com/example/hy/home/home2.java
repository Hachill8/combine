package com.example.hy.home;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.hy.GlobalVariable;
import com.example.hy.R;
import com.example.hy.forum.forum;
import com.example.hy.market.market;
import com.example.hy.search.search;
import com.example.hy.user_setting.user_setting;
import com.example.hy.webservice;

public class home2 extends AppCompatActivity{

    LinearLayout menuLinearLayout,second_menuLinearLayout;
    RelativeLayout recordLayout, calendarLayout, discussLayout,storeLayout,settingLayout,userLayout;
    BottomSheetDialog bottomSheetDialog;
    ImageButton record,calendar,discuss,store,setting,user,hat,edit_pot;
    Dialog banboo_hat_level;
    String insert_vege_item="";
    GlobalVariable vege_item
            ,vege_home; //首頁作物照片(暫時)
    ImageView Vege_image_home;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView( R.layout.activity_home2 );

        banboo_hat_level=new Dialog(this);


        edit_pot=(ImageButton) findViewById(R.id.edit_pot);
        edit_pot.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent a = new Intent(home2.this, com.example.hy.edit_pot.class);
                startActivity(a);
            }
        } );


        hat=(ImageButton) findViewById(R.id.hat);
        hat.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ShowPopUp_level();
            }
        } );

        createBottomSheetDialog();

        vege_item = (GlobalVariable)getApplicationContext();
        insert_vege_item();


        vege_home = (GlobalVariable)getApplicationContext();
        Vege_image_home = (ImageView) findViewById(R.id.vege_image_home);
        if(!vege_home.getVege_image_home().equals("無"))
        {
            Vege_image_home.setImageDrawable(getResources().getDrawable( R.drawable.vege_carrot_pot ));
        }

    }

    private void ShowPopUp_level()
    {
        banboo_hat_level.setContentView(R.layout.banboo_hat_level);
        banboo_hat_level.getWindow().setBackgroundDrawable(new ColorDrawable( Color.TRANSPARENT));
        banboo_hat_level.show();
    }

    private void createBottomSheetDialog()
    {

        if (bottomSheetDialog == null) {
            View view = LayoutInflater.from( this ).inflate( R.layout.bottom_sheet, null );

            record= view.findViewById( R.id.record );
            record.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Intent a = new Intent(home2.this, com.example.hy.record.record.class);
                    startActivity(a);
                }
            } );
            calendar= view.findViewById( R.id.calendar );
            calendar.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Intent a = new Intent(home2.this, com.example.hy.calendar.calendar.class);
                    startActivity(a);
                }
            } );
            discuss= view.findViewById( R.id.discuss );
            discuss.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Intent a = new Intent(home2.this, forum.class);
                    startActivity(a);
                }
            } );
            store= view.findViewById( R.id.store );
            store.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Intent a = new Intent(home2.this, market.class);
                    startActivity(a);
                }
            } );
            setting= view.findViewById( R.id.setting );
            setting.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    vege_item.setVege_item(insert_vege_item);
                    Intent a = new Intent(home2.this, search.class);
                    startActivity(a);
                }
            } );
            user= view.findViewById( R.id.user );
            user.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Intent a = new Intent(home2.this, user_setting.class);
                    startActivity(a);
                }
            } );


            bottomSheetDialog = new BottomSheetDialog( this );
            bottomSheetDialog.setContentView( view );

            //設置bottomsheet圓角
            bottomSheetDialog.getWindow().findViewById(R.id.design_bottom_sheet)
                    .setBackgroundResource(android.R.color.transparent);
        }

    }


    public void insert_vege_item() {
        Thread thread = new Thread() {
            public void run() {
                insert_vege_item = webservice.Vegename_list("s");
            }
        };
        thread.start();
        Log.v("test","1."+insert_vege_item+"2");
    }

    public void showDialog(View view)
    {
        bottomSheetDialog.show();
    }

}
