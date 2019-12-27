package com.example.hy.forum;


import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hy.GlobalVariable;
import com.example.hy.R;

import com.example.hy.webservice;

import java.util.List;


public class forum extends AppCompatActivity implements ViewPager.OnPageChangeListener,
        TabLayout.OnTabSelectedListener {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    ImageButton add_new_post; //新增文章
    AutoCompleteTextView search_forum;
    GlobalVariable Search_forum_string_item;
    String Search_forum_string_list="";
    String[] split_id;
    private forum_discussionFragment discussionFragment = new forum_discussionFragment();
    private forum_exchangeFragment exchangeFragment = new forum_exchangeFragment();

    //找到UI工人的經紀人，這樣才能派遣工作  (找到顯示畫面的UI Thread上的Handler)
    private android.os.Handler mUI_Handler = new android.os.Handler();
    //宣告特約工人的經紀人
    private Handler mThreadHandler;
    //宣告特約工人
    private HandlerThread mThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_forum);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);

        //聘請一個特約工人，有其經紀人派遣其工人做事 (另起一個有Handler的Thread)
        mThread = new HandlerThread("");

        //讓Worker待命，等待其工作 (開啟Thread)
        mThread.start();
        //找到特約工人的經紀人，這樣才能派遣工作 (找到Thread上的Handler)
        mThreadHandler=new Handler(mThread.getLooper());
        mThreadHandler.post(select_r1);


        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewPager);
        //注册监听
        viewPager.addOnPageChangeListener(this);
        tabLayout.addOnTabSelectedListener(this);

        //添加适配器，在viewPager里引入Fragment
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return discussionFragment;
                    case 1:
                        return exchangeFragment;
                }
                return null;
            }
            @Override
            public int getCount() {
                return 2;
            }
        });

        add_new_post=(ImageButton) findViewById(R.id.new_post);
        add_new_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(forum.this,forum_add_new_post.class);
                startActivity(a);
            }
        });

        Search_forum_string_item = (GlobalVariable) getApplicationContext();

        //搜尋
        search_forum = findViewById(R.id.search_forum);
        search_forum.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //點擊後抓文字並跳轉
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Search_forum_string_item.setForum_title(search_forum.getText().toString());

                Intent x = new Intent(forum.this,forum_post2.class);
                search_forum.setText("");
                search_forum = null; //要重置
                startActivity(x);


            }
        });


        pagerAdapter = new forum_PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount()) ;

    }



    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    Runnable  select_r1 = new Runnable() {
        @Override
        public void run() {
            Search_forum_string_list = webservice.Search_forum_list();

            mThreadHandler.post(select_r2);
        }
    };

    Runnable  select_r2 = new Runnable() {
        @Override
        public void run() {
            new Handler(Looper.getMainLooper()).post(new Runnable(){
                @Override
                public void run() {
                    String[] split_all = Search_forum_string_list.split("分開");
                    split_id = split_all[1].split("%");

                    String[] split=split_all[0].split("我是切割線");
                    search_forum.setAdapter(new ArrayAdapter<>(forum.this,android.R.layout.simple_list_item_1,split));
                }
            });

        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除工人上的工作
        if (mThreadHandler != null) {
            mThreadHandler.removeCallbacks(select_r1 );

        }
        //解聘工人 (關閉Thread)
        if (mThread != null) {
            mThread.quit();
        }

    }


    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }





}
