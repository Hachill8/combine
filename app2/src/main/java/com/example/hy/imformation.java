package com.example.hy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class imformation extends AppCompatActivity {
    private Button bt01,bt02;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imformation);
        bt01 = (Button)findViewById(R.id.BT_heart);
        bt01.setOnClickListener (new Button.OnClickListener()
        {
            boolean fg1=true;
            @Override
            public void onClick(View v)
            {
                if(fg1==true)
                {bt01.setBackgroundResource(R.drawable.icon04_3);fg1=false;}
                else if(fg1==false)
                {bt01.setBackgroundResource(R.drawable.icon04_2);fg1=true;}
            }
        }   );

        bt02 = (Button) findViewById(R.id.BT_back);
        bt02.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {}
        });

    }
}
