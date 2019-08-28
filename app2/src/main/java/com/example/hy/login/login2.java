package com.example.hy.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hy.R;
import com.example.hy.select_model;

public class login2 extends AppCompatActivity  {
    Button btntomodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("test","進入login2最上面");
        super.onCreate(savedInstanceState);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login2);
        Log.v("test","進入login2.xml");


        btntomodel = (Button) findViewById(R.id.btntomodel);
        Log.v("test","btntomodel");
        btntomodel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("test","onClick");
                Intent a = new Intent(login2.this, select_model.class);
                startActivity(a);

            }
        });

    }

}
