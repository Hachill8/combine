package com.example.hy.calendar;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hy.AutoSplitTextView;
import com.example.hy.DownloadImageTask;
import com.example.hy.GlobalVariable;
import com.example.hy.R;
import com.example.hy.search.VegeInfo;
import com.example.hy.webservice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class choose_calendar_info extends AppCompatActivity {

    List<choose_calendar_cardview> cardviewList;
    //找到UI工人的經紀人，這樣才能派遣工作  (找到顯示畫面的UI Thread上的Handler)
    private Handler mUI_Handler = new Handler();
    //宣告特約工人的經紀人
    private Handler mThreadHandler;
    //宣告特約工人
    private HandlerThread mThread;
    GlobalVariable choose_calendar_info;
    String choose_calendar_string;
    ProgressDialog mLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView( R.layout.activity_choose_calendar_info );

        mLoadingDialog = new ProgressDialog(choose_calendar_info.this);
        showLoadingDialog("載入中...");
        choose_calendar_info = (GlobalVariable) getApplicationContext() ;

        //聘請一個特約工人，有其經紀人派遣其工人做事 (另起一個有Handler的Thread)
        mThread = new HandlerThread("");
        //讓Worker待命，等待其工作 (開啟Thread)
        mThread.start();
        //找到特約工人的經紀人，這樣才能派遣工作 (找到Thread上的Handler)
        mThreadHandler=new Handler(mThread.getLooper());
        mThreadHandler.post(r1);


        cardviewList = new ArrayList<>();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.vege_info);
    }

    Runnable r1 = new Runnable() {
        @Override
        public void run() {
            String[] split=choose_calendar_info.getChoose_calendar_info().split("%");
            Log.v("test","split for ALL: " + choose_calendar_info.getChoose_calendar_info());
            Log.v("test","split for everyone: " + split[0]+"           "+split[1]+"           "+split[2]);
            choose_calendar_string = webservice.choose_calendar_info_cardview(split[0],split[1],split[2]);
            mUI_Handler.post(r2);

        }
    };

    Runnable r2 = new Runnable() {
        @Override
        public void run() {
            new Handler(Looper.getMainLooper()).post(new Runnable(){
                @Override
                public void run() {
                    String[] all,split,start_date;
                    if (!choose_calendar_string.equals(""))
                    {
                        all = choose_calendar_string.split("切");
                        start_date = all[0].split("%");
                        for(int num = 0; num < all.length ;num ++)
                        {
                            split = all[num].split("%");

                            Log.v("test","split:"+split[0]+"         "+split[1]+"         "+split[2]);
                            //算天數
                            long temp = 0;
                            try{
                                SimpleDateFormat sim = new SimpleDateFormat("yyyy/MM/dd");//定義日期時間格式，一定要進行ParseException的例外處理
                                Date start = sim.parse(start_date[0]);
                                long startTime = start.getTime();//取得時間的unix時間
                                Date end = sim.parse(split[0]);//取得目前即時的時間
                                long endTime = end.getTime();//取得時間的unix時間
                                temp = (endTime-startTime)/(1000*60*60*24)+1;
                                Log.v("test","天數: "+temp);
                            }catch(Exception e){
                                Log.v("test","天數錯誤: "+e);
                            }

                            //cardviewList.add(new choose_calendar_cardview(0,"2","2019-12-06","快長",bitmap));
                            cardviewList.add(new choose_calendar_cardview(num,String.valueOf(temp),split[0],split[1],split[2]));
                        }

                        RecyclerView recyclerView = findViewById(R.id.choose_calendar_info_recyclerview);
                        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
                        recyclerView.setAdapter(new choose_calendar_info.CardAdapter(choose_calendar_info.this, cardviewList));
                        dismissLoadingDialog();
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
        }
        //解聘工人 (關閉Thread)
        if (mThread != null) {
            mThread.quit();
        }
    }



    private class CardAdapter extends  RecyclerView.Adapter<CardAdapter.ViewHolder>
    {
        private Context context;
        List<choose_calendar_cardview> cardviewList;

        CardAdapter(Context context, List<choose_calendar_cardview> cardviewList) {
            this.context = context;
            this.cardviewList = cardviewList;
        }

        @Override
        public CardAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.activity_choose_calendar_cardview,viewGroup,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CardAdapter.ViewHolder viewHolder, int i) {
            final choose_calendar_cardview cardview = cardviewList.get(i);
            viewHolder.day.setText(String.valueOf(cardview.getDay()));
            if(!cardview.getImage().equals("無圖片"))
            {
                //viewHolder.downloadImageTask.execute(cardview.getImage());
                Glide.with(choose_calendar_info.this).load(cardview.getImage()).into(viewHolder.img);
            }
            else
            {
                Glide.with(choose_calendar_info.this).load(R.drawable.gender).into(viewHolder.img);
            }

            viewHolder.time.setText(String.valueOf((cardview.getTime())));
            viewHolder.message.setText(String.valueOf((cardview.getMessage())));
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    addItem(cardviewList.size());
//                    record_name.setRecord_vege_name(cardview.getName());
//                    Intent intent = new Intent(record.this, record_Information2.class);
//                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return cardviewList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            DownloadImageTask downloadImageTask;
            ImageView img;
            TextView day,time;
            AutoSplitTextView message;

            ViewHolder(View itemView){
                super(itemView);
                //downloadImageTask = new DownloadImageTask((ImageView)itemView.findViewById(R.id.choose_calendar_img));
                img = itemView.findViewById(R.id.choose_calendar_img);
                day =  itemView.findViewById(R.id.choose_calendar_day);
                time =  itemView.findViewById(R.id.choose_calendar_time);
                message =  itemView.findViewById(R.id.choose_calendar_message);
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
    private void showLoadingDialog(String message){
        message = "載入中...";
        mLoadingDialog.setMessage(message);
        if(mLoadingDialog==null){
            mLoadingDialog = new ProgressDialog(this);
            mLoadingDialog.setMessage(message);
        }
        mLoadingDialog.show();
    }

    private void dismissLoadingDialog() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }
}