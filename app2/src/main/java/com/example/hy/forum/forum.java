package com.example.hy.forum;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
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

import com.example.hy.DownloadImageTask;
import com.example.hy.GlobalVariable;
import com.example.hy.R;
import com.example.hy.user_setting.user_setting;
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
    ImageView post_heart;
    AutoCompleteTextView search_forum;
    GlobalVariable  Search_forum_string_item;
    List<forum_post> postList;
    String Search_forum_string_list,forum_cardview="",gmail,all_like;
    String[] split_cardview_info,split_all_like;
    int k;

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
        gmail=Search_forum_string_item.getUser_gmail();

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
//        tabExchange.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(forum.this);
//                builder.setTitle("交換區☆即將推出，敬請期待!");
//                builder.setMessage("提供使用者作物、工具、種子等等...的交換，達到不浪費食物及工具的再利用");
//                builder.setPositiveButton("好", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                AlertDialog dialog=builder.create();
//                dialog.show();
//            }
//        });

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
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
 //       recyclerView.setHasFixedSize(true);
        adapter=new forum_postadaper(this,postList);


    }

    Runnable r1 = new Runnable() {
        @Override
        public void run() {
            Search_forum_string_list = webservice.Search_forum_list();
            forum_cardview = webservice.forum_cardview();
            all_like=webservice.Select_all_like_post(gmail);
            mUI_Handler.post(r2);
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
                    split_all_like = all_like.split("%");
                    for(int i = 0 ; i < split_cardview.length ; i++) {
                        split_cardview_info = split_cardview[i].split("WS切");
                        k = all_like.indexOf(split_cardview_info[1]);
                        Log.v("test123456","all:::::"+split_cardview_info[1]+k+all_like);
                            //user + "WS切" + title + "WS切" + time + "WS切" + heartnum + "WS切" + commentnum + "WS切" + post_img
                            if (k>=0) {
                                postList.add(
                                        new forum_post(
                                                i,
                                                split_cardview_info[0],
                                                split_cardview_info[1],
                                                split_cardview_info[2],
                                                R.drawable.like_fill,
                                                split_cardview_info[3].substring(0, 1),
                                                split_cardview_info[4],
                                                split_cardview_info[5],
                                                R.drawable.user10));

                            } else {
                                postList.add(
                                        new forum_post(
                                                i,
                                                split_cardview_info[0],
                                                split_cardview_info[1],
                                                split_cardview_info[2],
                                                R.drawable.post_heart,
                                                split_cardview_info[3].substring(0, 1),
                                                split_cardview_info[4],
                                                split_cardview_info[5],
                                                R.drawable.user10));

                            }
                            recyclerView.setAdapter(adapter);
                    }
                }
            });


        }
    };





    public void Intent_to_post(String title_click)
    {
        Search_forum_string_item.setForum_title_click(title_click);
        Intent x = new Intent(forum.this,forum_post2.class);
        startActivity(x);
    }

    public  void Intent_to_post2(String title_click)
    {


    }


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

    private class forum_postadaper extends RecyclerView.Adapter<forum_postadaper.postviewholder>  {

        private Context mctx;
        private List<forum_post> postList;
        String s="";
        public forum_postadaper(Context mctx, List<forum_post> postList) {
            this.mctx = mctx;
            this.postList = postList;
        }

        @Override
        public postviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater=LayoutInflater.from(mctx);
            View view=inflater.inflate(R.layout.forum_list_layout,viewGroup,false);
            return new postviewholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull postviewholder holder, int position) {
            final forum_post post=postList.get(position);

            holder.textViewtitle.setText(post.getTitle());
            holder.textViewdesc.setText(post.getShortdesc());
            holder.time.setText(post.getTime());
            holder.heart_img.setImageDrawable(mctx.getResources().getDrawable(post.getHeart_img()));
            holder.commentnum.setText(post.getCommentnumnum());
            holder.heartnum.setText(post.getHeartnum());
            holder.userimg.setImageDrawable(mctx.getResources().getDrawable(post.getUserimg()));
            holder.downloadImageTask.execute(post.getImage_string());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Search_forum_string_item.setForum_title_click(post.getShortdesc());
                    Intent i = new Intent(forum.this,forum_post2.class);
                    startActivity(i);
                }
            });

        }

        @Override
        public int getItemCount() {
            return postList.size();
        }



        class postviewholder extends RecyclerView.ViewHolder {

            ImageView userimg,heart_img;
            TextView textViewtitle,textViewdesc,time,heartnum,commentnum;
            DownloadImageTask downloadImageTask ;

            public postviewholder(@NonNull View itemView) {
                super(itemView);


                textViewtitle=itemView.findViewById(R.id.user_name);
                textViewdesc=itemView.findViewById(R.id.post);
                userimg=itemView.findViewById((R.id.user_img));
                time=itemView.findViewById(R.id.post_time);
                heart_img=itemView.findViewById(R.id.post_heart);
                heartnum=itemView.findViewById(R.id.post_heart_num);
                commentnum=itemView.findViewById(R.id.post_chat_num);
                downloadImageTask = new DownloadImageTask((ImageView)itemView.findViewById(R.id.plant));

            }


        }

    }

}
