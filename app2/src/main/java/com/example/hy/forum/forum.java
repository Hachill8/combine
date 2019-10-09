package com.example.hy.forum;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.hy.R;

import java.util.ArrayList;
import java.util.List;

public class forum extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    TabItem tabDiscussion;
    TabItem tabExchange;
    Button bt1,bt2;
    RecyclerView recyclerView;
    forum_postadaper adapter;
    ImageButton add_new_post; //新增文章


    List<forum_post> postList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_forum);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);





        tabLayout = findViewById(R.id.tablayout);
        tabDiscussion = findViewById(R.id.tabdiscussion);
        tabExchange = findViewById(R.id.tabexchange);
        //viewPager = findViewById(R.id.viewpager);

        pagerAdapter = new forum_PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount()) ;
//        viewPager.setAdapter(pagerAdapter);
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        bt1=(Button) findViewById(R.id.BT1);
        bt2=(Button) findViewById(R.id.BT2);

        add_new_post=(ImageButton) findViewById(R.id.new_post);
        add_new_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(forum.this,forum_add_new_post.class);
                startActivity(a);
            }
        });

        postList=new ArrayList<>();
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        postList.add(
                new forum_post(
                        1,
                        "狠愛種",
                        "高麗菜一直被蟲咬",
                        "今天 18:55",
                        "18",
                        "31",
                        R.drawable.post_plant00,
                        R.drawable.user10));
        postList.add(
                new forum_post(
                        2,
                        "吼嘎在",
                        "辣椒怎麼種？",
                        "星期五 20:48",
                        "6",
                        "10",
                        R.drawable.post_plant01,
                        R.drawable.user10));


        adapter=new forum_postadaper(this,postList);
        recyclerView.setAdapter(adapter);
    }
}
