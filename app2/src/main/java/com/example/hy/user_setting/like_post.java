package com.example.hy.user_setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hy.DownloadImageTask;
import com.example.hy.GlobalVariable;
import com.example.hy.R;
import com.example.hy.forum.forum;
import com.example.hy.forum.forum_post;
import com.example.hy.forum.forum_post2;
import com.example.hy.webservice;

import java.util.ArrayList;
import java.util.List;

public class like_post extends AppCompatActivity {
    ImageButton back_user;
    Button go_forum;
    ImageView nopost_img;
    TextView nopost_tv;
    GlobalVariable Search_forum_string_item;
    RecyclerView recyclerView;
    forum_postadaper adapter;
    List<forum_post> postList;
    String[] split_cardview_info,split_all_like;
    String gmail,forum_cardview="",all_like_post;
    int k;

    //找到UI工人的經紀人，這樣才能派遣工作  (找到顯示畫面的UI Thread上的Handler)
    private Handler mUI_Handler = new Handler();
    //宣告特約工人的經紀人
    private Handler mThreadHandler;
    //宣告特約工人
    private HandlerThread mThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_like_post);

        //聘請一個特約工人，有其經紀人派遣其工人做事 (另起一個有Handler的Thread)
        mThread = new HandlerThread("");
        //讓Worker待命，等待其工作 (開啟Thread)
        mThread.start();
        //找到特約工人的經紀人，這樣才能派遣工作 (找到Thread上的Handler)
        mThreadHandler=new Handler(mThread.getLooper());

        back_user=findViewById(R.id.back_user);
        go_forum=findViewById(R.id.go_forum);
        nopost_img=findViewById(R.id.imageView);
        nopost_tv= findViewById(R.id.textView5);

        Search_forum_string_item = (GlobalVariable) getApplicationContext();
        gmail=Search_forum_string_item.getUser_gmail();

        back_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(like_post.this, user_setting.class);
                startActivity(intent);
            }
        });
        go_forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x=new Intent(like_post.this, forum.class);
                startActivity(x);
            }
        });

        postList=new ArrayList<>();

        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //       recyclerView.setHasFixedSize(true);
        adapter=new forum_postadaper(this,postList);

        mThreadHandler.post(r1);
    }

    Runnable r1 = new Runnable() {
        @Override
        public void run() {
            all_like_post=webservice.Select_all_like_post(gmail);
            Log.v("test123456","all:::::"+all_like_post);
            mThreadHandler.post(r3);
        }
    };

    Runnable r3 = new Runnable() {
        @Override
        public void run() {
            forum_cardview = webservice.user_like_forum_cardview(all_like_post);
            Log.v("test123456","WTH:::::"+forum_cardview);
            mUI_Handler.post(r4);
        }
    };

    Runnable r4 = new Runnable() {
        @Override
        public void run() {
            new Handler(Looper.getMainLooper()).post(new Runnable(){
                @Override
                public void run() {

                    String[] split_cardview = forum_cardview.split("ALL切");
                    split_all_like = all_like_post.split("%");

                    for(int i = 0 ; i < split_cardview.length ; i++) {
                        split_cardview_info = split_cardview[i].split("WS切");
                        if(!forum_cardview.equals("can't not found")) {
                        k = all_like_post.indexOf(split_cardview_info[1]);
                        Log.v("test123456","all:::::"+split_cardview_info[1]);
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
                        else{nopost_img.setVisibility(View.VISIBLE);nopost_tv.setVisibility(View.VISIBLE);go_forum.setVisibility(View.VISIBLE);}
                    }
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
            mThreadHandler.removeCallbacks(r3);
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
        public forum_postadaper.postviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater=LayoutInflater.from(mctx);
            View view=inflater.inflate(R.layout.forum_list_layout,viewGroup,false);
            return new forum_postadaper.postviewholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull forum_postadaper.postviewholder holder, int position) {
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
                    Search_forum_string_item.setForum_title_click(post.getId());
                    Intent i = new Intent(like_post.this, forum_post2.class);
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
