package com.example.hy.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.example.hy.R;


public class taipei_life_info_farmers extends AppCompatActivity
{
    Button farmers1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taipei_life_info_farmers);

        farmers1=(Button) findViewById(R.id.farmers1);
        farmers1.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent a = new Intent(taipei_life_info_farmers.this, taipei_life_farmers_infop2.class);
                startActivity(a);
            }
        } );
    }

}