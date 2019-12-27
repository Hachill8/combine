package com.example.hy.market;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hy.R;
import com.example.hy.webservice;

import java.util.ArrayList;
import java.util.List;

public class market_order_info_ready_to_ship extends Fragment implements SwipeRefreshLayout.OnRefreshListener
{

    RecyclerView recyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    List<market_order_info_cardview> cardviewList;
    //找到UI工人的經紀人，這樣才能派遣工作  (找到顯示畫面的UI Thread上的Handler)
    private android.os.Handler mUI_Handler = new android.os.Handler();
    //宣告特約工人的經紀人
    private Handler mThreadHandler;
    //宣告特約工人
    private HandlerThread mThread;
    market_order_info_postadaper adapter;
    String order_all="";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.market_order_info_ready_to_ship, container, false);


        //聘請一個特約工人，有其經紀人派遣其工人做事 (另起一個有Handler的Thread)
        mThread = new HandlerThread("");

        //讓Worker待命，等待其工作 (開啟Thread)
        mThread.start();
        //找到特約工人的經紀人，這樣才能派遣工作 (找到Thread上的Handler)
        mThreadHandler=new Handler(mThread.getLooper());


        mSwipeRefreshLayout =  view.findViewById(R.id.swiperefreshlayout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        cardviewList=new ArrayList<>();
        adapter = new market_order_info_ready_to_ship.market_order_info_postadaper(getContext(), cardviewList);

        recyclerView=view.findViewById(R.id.market_order_info_recyclerview);

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                Log.v("test","mSwipeRefreshLayout.setRefreshing(true);");
            }
        });

        mThreadHandler.post(order_info_r1);


        return view;
    }

    Runnable order_info_r1 = new Runnable() {
        @Override
        public void run() {

            order_all = webservice.market_order_cardview();
            Log.v("test","order_all: "+order_all);
            mThreadHandler.post(order_info_r2);
        }
    };



    Runnable order_info_r2 = new Runnable() {
        @Override
        public void run() {
            new Handler(Looper.getMainLooper()).post(new Runnable(){
                @Override
                public void run() {
                    String[] split_all = order_all.split("圖");
                    String[] split_order;
                    String split_order_name;
                    for (int i = 0 ; i < split_all.length;i++)
                    {
                        split_order = split_all[i].split("切");
                        split_order_name = split_order[0].replace("%","\n");
                        Bitmap bitmap=null;
                        byte[] decode = Base64.decode(split_order[3],Base64.NO_CLOSE);
                        bitmap = BitmapFactory.decodeByteArray(decode, 0, decode.length);
                        //public market_order_info_cardview (int id, String name, String price,String num, Bitmap image)
                        cardviewList.add(new market_order_info_cardview(i,split_order_name,split_order[1],split_order[2],bitmap));
                    }
                    mSwipeRefreshLayout.setRefreshing(false);
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
            mThreadHandler.removeCallbacks(order_info_r1);
        }
        //解聘工人 (關閉Thread)
        if (mThread != null) {
            mThread.quit();
        }
    }




    @Override
    public void onRefresh() {

        cardviewList.clear();
        mThreadHandler.post(order_info_r1);
    }


    private class market_order_info_postadaper extends  RecyclerView.Adapter<market_order_info_ready_to_ship.market_order_info_postadaper.ViewHolder>{
        private Context context;
        public List<market_order_info_cardview> cardviewList;

        market_order_info_postadaper(Context context, List<market_order_info_cardview> cardviewList) {
            this.context = context;
            this.cardviewList = cardviewList;
        }

        @Override
        public market_order_info_ready_to_ship.market_order_info_postadaper.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.market_order_info_goods_cardview,viewGroup,false);
            return new market_order_info_ready_to_ship.market_order_info_postadaper.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(market_order_info_ready_to_ship.market_order_info_postadaper.ViewHolder holder, int i) {
            final market_order_info_cardview cardview = cardviewList.get(i);
            holder.goods_price.setText(cardview.getPrice());
            holder.goods_num.setText(cardview.getNum());
            holder.goods_context.setText(cardview.getName());
            holder.goods_img.setImageBitmap(cardview.getImage());
        }

        @Override
        public int getItemCount() {
            return cardviewList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            ImageView goods_img;
            TextView goods_num,goods_price,goods_context;

            ViewHolder(View itemView){
                super(itemView);
                goods_img = itemView.findViewById(R.id.order_goods_img);
                goods_context = itemView.findViewById(R.id.order_goods_context);
                goods_num = itemView.findViewById(R.id.order_goods_num);
                goods_price = itemView.findViewById(R.id.order_goods_price);
            }
        }
        public  void addItem(int i){
//            fg = true;
//            num = cardviewList.size()-1;
//            //add(位置,資料)
//            cardviewList.add(i, new record_Cardview(id,"小白菜", R.drawable.icon201));
//            id=id+1;
//            notifyItemInserted(i);
        }
    }






}
