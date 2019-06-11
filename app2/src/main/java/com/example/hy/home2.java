package com.example.hy;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class home2 extends AppCompatActivity implements View.OnClickListener {

    LinearLayout menuLinearLayout,second_menuLinearLayout;
    RelativeLayout recordLayout, calendarLayout, discussLayout,storeLayout,settingLayout,userLayout;
    BottomSheetDialog bottomSheetDialog;
    ImageButton record,calendar,discuss,store,setting,user,hat,edit_pot;
    Dialog banboo_hat_level;

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
                Intent a = new Intent(home2.this,edit_pot.class);
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
            menuLinearLayout = view.findViewById( R.id.menuLinearLayout );
            recordLayout = view.findViewById( R.id.recordLayout );
            calendarLayout = view.findViewById( R.id.calendarLayout );
            discussLayout = view.findViewById( R.id.discussLayout );
            storeLayout = view.findViewById( R.id.storeLayout );
            settingLayout = view.findViewById( R.id.settingLayout );
            userLayout = view.findViewById( R.id.userLayout );

            record= view.findViewById( R.id.record );
            record.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Intent a = new Intent(home2.this,search.class);
                    startActivity(a);
                }
            } );
            calendar= view.findViewById( R.id.calendar );
            calendar.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Intent a = new Intent(home2.this,search.class);
                    startActivity(a);
                }
            } );
            discuss= view.findViewById( R.id.discuss );
            discuss.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Intent a = new Intent(home2.this,search.class);
                    startActivity(a);
                }
            } );
            store= view.findViewById( R.id.store );
            store.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Intent a = new Intent(home2.this,search.class);
                    startActivity(a);
                }
            } );
            setting= view.findViewById( R.id.setting );
            setting.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Intent a = new Intent(home2.this,search.class);
                    startActivity(a);
                }
            } );
            user= view.findViewById( R.id.user );
            user.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Intent a = new Intent(home2.this,user_setting.class);
                    startActivity(a);
                }
            } );


            recordLayout.setOnClickListener( this );
            calendarLayout.setOnClickListener( this );
            discussLayout.setOnClickListener( this );
            storeLayout.setOnClickListener( this );
            settingLayout.setOnClickListener( this );
            userLayout.setOnClickListener( this );

            bottomSheetDialog = new BottomSheetDialog( this );
            bottomSheetDialog.setContentView( view );

            //設置bottomsheet圓角
            bottomSheetDialog.getWindow().findViewById(R.id.design_bottom_sheet)
                    .setBackgroundResource(android.R.color.transparent);
        }

    }



    public void showDialog(View view)
    {
        bottomSheetDialog.show();
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.recordLayout:
                Intent a = new Intent(home2.this,search.class);
                startActivity(a);
                bottomSheetDialog.dismiss();
                break;

            case R.id.calendarLayout:
                bottomSheetDialog.dismiss();
                Intent b = new Intent(home2.this,search.class);
                startActivity(b);
                break;

            case R.id.discussLayout:
                bottomSheetDialog.dismiss();
                Intent c = new Intent(home2.this,search.class);
                startActivity(c);
                break;

            case R.id.storeLayout:
                bottomSheetDialog.dismiss();
                Intent d = new Intent(home2.this,search.class);
                startActivity(d);
                break;

            case R.id.settingLayout:
                bottomSheetDialog.dismiss();
                Intent e = new Intent(home2.this,search.class);
                startActivity(e);
                break;

            case R.id.userLayout:
                bottomSheetDialog.dismiss();
                Intent f = new Intent(home2.this,search.class);
                startActivity(f);
                break;

        }
    }
}
