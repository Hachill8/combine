package com.example.hy.forum;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.hy.GlobalVariable;
import com.example.hy.R;
import com.example.hy.webservice;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogRecord;

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
    AutoCompleteTextView search_forum;
    GlobalVariable Search_forum_string_item;
    List<forum_post> postList;
    String Search_forum_string_list,forum_cardview="";

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
        mThreadHandler.post(r1);

        Search_forum_string_item = (GlobalVariable) getApplicationContext();

        search_forum = findViewById(R.id.search_forum);
        search_forum.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //點擊後抓文字並跳轉
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Search_forum_string_item.setSearch_forum_string(search_forum.getText().toString());
                Intent x = new Intent(forum.this,forum_post2.class);
                startActivity(x);
                search_forum = null; //要重置
            }
        });


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
        adapter=new forum_postadaper(this,postList);
    }

    Runnable r1 = new Runnable() {
        @Override
        public void run() {
            Search_forum_string_list = webservice.Search_forum_list();
            forum_cardview = webservice.forum_cardview();
            mThreadHandler.post(r2);
        }
    };

    Runnable r2 = new Runnable() {
        @Override
        public void run() {
            new Handler(Looper.getMainLooper()).post(new Runnable(){
                @Override
                public void run() {
                    String[] split=Search_forum_string_list.split("我是切割線");
                    search_forum.setAdapter(new ArrayAdapter<>(forum.this,android.R.layout.simple_list_item_1,split));

                    String[] split_cardview = forum_cardview.split("ALL切");
                    for(int i = 0 ; i < split_cardview.length ; i++)
                    {
                        String[] split_cardview_info  = split_cardview[i].split("WS切");
                        //user + "WS切" + title + "WS切" + time + "WS切" + heartnum + "WS切" + commentnum + "WS切" + post_img
                        postList.add(
                                new forum_post(
                                        i,
                                        split_cardview_info[0],
                                        split_cardview_info[1],
                                        split_cardview_info[2],
                                        split_cardview_info[3],
                                        split_cardview_info[4],
                                        split_cardview_info[5],
                                        R.drawable.user10));
                    }


                    recyclerView.setAdapter(adapter);
                }
            });
        }
    };



    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除工人上的工作
        if (mThreadHandler != null) {
            mThreadHandler.removeCallbacks(r1);
        }
        //解聘工人 (關閉Thread)
        if (mThread != null) {
            mThread.quit();
        }
    }

}
