package com.example.hy.forum;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hy.DownloadImageTask;
import com.example.hy.GlobalVariable;
import com.example.hy.R;
import com.example.hy.webservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class forum_discussionFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    RecyclerView recyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    forum_postadaper adapter;

    ImageView post_heart;

    GlobalVariable Search_forum_string_item;
    List<forum_post> postList ;
    String forum_cardview="",gmail,all_like;
    String[] split_cardview_info,split_all_like;
    int k=-1;
    Button new_post_sort,hot_post_sort;
    boolean sort=true;

    //找到UI工人的經紀人，這樣才能派遣工作  (找到顯示畫面的UI Thread上的Handler)
    private android.os.Handler mUI_Handler = new android.os.Handler();
    //宣告特約工人的經紀人
    private Handler mThreadHandler;
    //宣告特約工人
    private HandlerThread mThread;




    public forum_discussionFragment() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.forum_discussion_fragment, container, false);
        //聘請一個特約工人，有其經紀人派遣其工人做事 (另起一個有Handler的Thread)
        mThread = new HandlerThread("");

        //讓Worker待命，等待其工作 (開啟Thread)
        mThread.start();
        //找到特約工人的經紀人，這樣才能派遣工作 (找到Thread上的Handler)
        mThreadHandler=new Handler(mThread.getLooper());

        new_post_sort = view.findViewById(R.id.BT2);
        new_post_sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sort=false;
                hot_post_sort.setBackground(getResources().getDrawable( R.drawable.shape_button));
                new_post_sort.setBackground(getResources().getDrawable( R.drawable.shape_button2));

                postList.clear();
                mSwipeRefreshLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(true);
                        Log.v("test","mSwipeRefreshLayout.setRefreshing(true);");
                    }
                });
                mThreadHandler.post(post_sort);
            }
        });
        hot_post_sort = view.findViewById(R.id.BT1);
        hot_post_sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hot_post_sort.setBackground(getResources().getDrawable( R.drawable.shape_button2));
                new_post_sort.setBackground(getResources().getDrawable( R.drawable.shape_button));

                sort=true;
                postList.clear();
                mSwipeRefreshLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(true);
                        Log.v("test","mSwipeRefreshLayout.setRefreshing(true);");
                    }
                });
                mThreadHandler.post(post_sort);
            }
        });


        Search_forum_string_item = (GlobalVariable) getActivity().getApplicationContext();
        gmail=Search_forum_string_item.getUser_gmail();

        mSwipeRefreshLayout =  view.findViewById(R.id.swiperefreshlayout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        postList=new ArrayList<>();
        adapter=new forum_postadaper(getContext(),postList);
        recyclerView=view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));


        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                Log.v("test","mSwipeRefreshLayout.setRefreshing(true);");
            }
        });
        mThreadHandler.post(post_sort);


        // Inflate the layout for this fragment
        return view;
    }

    Runnable post_sort = new Runnable() {
        @Override
        public void run() {
                forum_cardview = webservice.forum_cardview(sort);
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
                    String[] split_cardview = forum_cardview.split("ALL切");
                    if(!all_like.equals("找不到"))
                    {
                        split_all_like = all_like.split("%");
                    }

                    for(int i = 0 ; i < split_cardview.length ; i++) {
                        split_cardview_info = split_cardview[i].split("WS切");
                        if(!all_like.equals("找不到"))
                        {
                            k = all_like.indexOf(split_cardview_info[6]);
                        }

                        Log.v("test123456"," split_all_like: "+k);
                        //user + "WS切" + title + "WS切" + time + "WS切" + heartnum + "WS切" + commentnum + "WS切" + post_img
                        if (k>=0) {
                            postList.add(
                                    new forum_post(
                                            Integer.valueOf(split_cardview_info[6]),
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
                                            Integer.valueOf(split_cardview_info[6]),
                                            split_cardview_info[0],
                                            split_cardview_info[1],
                                            split_cardview_info[2],
                                            R.drawable.forum_post_like,
                                            split_cardview_info[3].substring(0, 1),
                                            split_cardview_info[4],
                                            split_cardview_info[5],
                                            R.drawable.user10));
                        }
                    }
                    mSwipeRefreshLayout.setRefreshing(false);
                    Log.v("test","mSwipeRefreshLayout.setRefreshing(false);");
                    recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
                    recyclerView.setAdapter(adapter);

                }
            });
        }
    };


    public void onDestroy() {
        super.onDestroy();
        //移除工人上的工作
        if (mThreadHandler != null) {
            mThreadHandler.removeCallbacks(post_sort);
        }
        //解聘工人 (關閉Thread)
        if (mThread != null) {
            mThread.quit();
        }
    }

    @Override
    public void onRefresh() {
        postList.clear();
        mThreadHandler.post(post_sort);
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
            Glide.with(getActivity()).load(post.getImage_string()).into(holder.plant_img);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Search_forum_string_item.setForum_title_click(post.getId());
                    Intent i = new Intent(getActivity(),forum_post2.class);
                    startActivity(i);
                }
            });

        }

        @Override
        public int getItemCount() {
            return postList.size();
        }



        class postviewholder extends RecyclerView.ViewHolder {

            ImageView userimg,heart_img,plant_img;
            TextView textViewtitle,textViewdesc,time,heartnum,commentnum;

            public postviewholder(@NonNull View itemView) {
                super(itemView);


                textViewtitle=itemView.findViewById(R.id.user_name);
                textViewdesc=itemView.findViewById(R.id.post);
                userimg=itemView.findViewById((R.id.user_img));
                time=itemView.findViewById(R.id.post_time);
                heart_img=itemView.findViewById(R.id.post_heart);
                heartnum=itemView.findViewById(R.id.post_heart_num);
                commentnum=itemView.findViewById(R.id.post_chat_num);
                plant_img = itemView.findViewById(R.id.plant);

            }


        }

    }

}
