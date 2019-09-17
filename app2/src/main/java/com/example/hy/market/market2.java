package com.example.hy.market;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.hy.R;

public class market2 extends AppCompatActivity
{
    private Button back_2_market;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_market3);

        back_2_market=(Button) findViewById(R.id.back_2_market);
        back_2_market.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent a = new Intent(market2.this,market.class);
                startActivity(a);
            }
        } );

    }

    public void click(View view)
    {
        Intent a = new Intent(market2.this,market.class);
        startActivity(a);
    }
}
