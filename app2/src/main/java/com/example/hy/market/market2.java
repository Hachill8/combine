package com.example.hy.market;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hy.R;

public class market2 extends AppCompatActivity
{
    private static final String TAG = "AmountView";
    private int amount=1;
    private TextView etAmount;
    private Button btnDecrease;
    private Button btnIncrease;

    //****以下為原code***//
    private Button back_2_market;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_market3);
        etAmount = (TextView)findViewById(R.id.etAmount);
        btnIncrease=(Button)findViewById(R.id.btnIncrease);
        btnIncrease.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int i = v.getId();
                if (i == R.id.btnIncrease)
                {amount++; etAmount.setText(amount+"");}
            }
        });

        btnDecrease=(Button)findViewById(R.id.btnDecrease);
        btnDecrease.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int i = v.getId();
                if (i == R.id.btnDecrease)
                {
                    if (amount > 1)
                    { amount--; etAmount.setText(amount+""); }
                    else
                    { amount=1; etAmount.setText(amount+""); }
                }
            }
        });

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
