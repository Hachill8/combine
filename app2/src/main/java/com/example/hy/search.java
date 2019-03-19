package com.example.hy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class search extends AppCompatActivity
{
    private Button btnextPageBtn1,btnextPageBtn2,bt_filter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnextPageBtn1 = (Button)findViewById(R.id.BT_crop_name);
        btnextPageBtn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setClass(search.this, imformation.class);
                startActivity(intent);
            }
        });

        btnextPageBtn2 = (Button)findViewById(R.id.BT_crop_name2);
        btnextPageBtn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setClass(search.this, imformation.class);
                startActivity(intent);
            }
        });
        bt_filter = (Button)findViewById(R.id.BT_filter);

        bt_filter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setClass(search.this, imformation.class);//這行開始要修改
                startActivity(intent);
            }
        });

    }
}
