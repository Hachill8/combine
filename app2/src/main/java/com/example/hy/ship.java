package com.example.hy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

import com.example.hy.market.market;

import java.time.Instant;

public class ship extends AppCompatActivity{

    private Button btn_go_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_ship );

        btn_go_detail=(Button) findViewById( R.id.btn_go_detail );

        btn_go_detail.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent a = new Intent( ship.this, ship_detail.class);
                startActivity(a);
            }
        } );
    }


}
