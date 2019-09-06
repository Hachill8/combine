package com.example.hy.forum;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hy.R;

public class postpage extends AppCompatActivity {
    TextView title,discription,heartnum,messnum,thumnum;
    ImageView topimg,heart,comment,thumbup,userimg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_page);
        title=findViewById(R.id.textView5);
        discription=findViewById(R.id.textView7);
        heartnum=findViewById(R.id.textView8);
        messnum=findViewById(R.id.textView9);
        thumnum=findViewById(R.id.textView12);
        topimg=findViewById(R.id.imageView);
        heart=findViewById(R.id.imageView2);
        comment=findViewById(R.id.imageView3);
        thumbup=findViewById(R.id.imageView4);
        userimg=findViewById(R.id.imageView6);

    }
}
