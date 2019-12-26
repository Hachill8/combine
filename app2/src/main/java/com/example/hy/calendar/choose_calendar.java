package com.example.hy.calendar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hy.AutoSplitTextView;
import com.example.hy.DownloadImageTask;
import com.example.hy.GlobalVariable;
import com.example.hy.R;
import com.example.hy.webservice;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class choose_calendar extends AppCompatActivity {

    List<choose_calendar_cardview> cardviewList;
    //找到UI工人的經紀人，這樣才能派遣工作  (找到顯示畫面的UI Thread上的Handler)
    private Handler mUI_Handler = new Handler();
    //宣告特約工人的經紀人
    private Handler mThreadHandler;
    //宣告特約工人
    private HandlerThread mThread;
    String choose_calendar_home_list_string="";
    GlobalVariable choose_calendar_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView( R.layout.activity_choose_calendar );

        //聘請一個特約工人，有其經紀人派遣其工人做事 (另起一個有Handler的Thread)
        mThread = new HandlerThread("");
        //讓Worker待命，等待其工作 (開啟Thread)
        mThread.start();
        //找到特約工人的經紀人，這樣才能派遣工作 (找到Thread上的Handler)
        mThreadHandler=new Handler(mThread.getLooper());
        mThreadHandler.post(r1);

        choose_calendar_info = (GlobalVariable) getApplicationContext();


        cardviewList = new ArrayList<>();
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(),
                R.drawable.calendar_one),
                bitmap2 = BitmapFactory.decodeResource(getResources(),
                        R.drawable.calendar_two),
                bitmap3 = BitmapFactory.decodeResource(getResources(),
                        R.drawable.calendar_three);
//        RecyclerView recyclerView = findViewById(R.id.activity_choose_calendar_home_recyclerview);
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
////        cardviewList.add(new choose_calendar_cardview(0,"","","壽豐",bitmap1));
////        cardviewList.add(new choose_calendar_cardview(1,"","","吉安",bitmap2));
////        cardviewList.add(new choose_calendar_cardview(2,"","","花蓮",bitmap3));
//        recyclerView.setAdapter(new choose_calendar.CardAdapter(choose_calendar.this, cardviewList));
    }

    Runnable r1 = new Runnable() {
        @Override
        public void run() {
            choose_calendar_home_list_string = webservice.choose_calendar_home_list();
            mThreadHandler.post(r2);

        }
    };

    Runnable r2 = new Runnable() {
        @Override
        public void run() {
            new Handler(Looper.getMainLooper()).post(new Runnable(){
                @Override
                public void run() {
                    String[] all,split_img,split;
                    List<String> split_gmail=new ArrayList<>();
                    List<String> split_id_string=new ArrayList<>();
                    List<String> split_vege=new ArrayList<>();
                    if (!choose_calendar_home_list_string.equals(""))
                    {
                        all = choose_calendar_home_list_string.split("分開");
                        split = all[0].split("%");
                        int index =0;
                        for (int i = 0;i < split.length/3;i++)
                        {
                            split_gmail.add(split[index]);
                            split_id_string.add(split[index+1]);
                            split_vege.add(split[index+2]);
                            index = index + 3;
                            Log.v("test","     gmail: "+split_gmail.get(i)+"     id: "+split_id_string.get(i)+"     vege: "+split_vege.get(i));
                        }
                        split_img = all[1].split("切");
                        Bitmap myBitmap=null;
                        for(int num = split_vege.size()-1; num >= 0 ;num --)
                        {
                           Log.v("test","choose_calendar_cardview的num: "+num);
                            //choose_calendar_cardview(String id_string,String gmail,String URL,String place,String vege)
                            cardviewList.add(new choose_calendar_cardview(split_id_string.get(num),split_gmail.get(num),split_img[num],"壽豐",split_vege.get(num)));
                        }

                        RecyclerView recyclerView = findViewById(R.id.activity_choose_calendar_home_recyclerview);
                        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
                        recyclerView.setAdapter(new choose_calendar.CardAdapter(choose_calendar.this, cardviewList));
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
        public List<choose_calendar_cardview> cardviewList;

        CardAdapter(Context context, List<choose_calendar_cardview> cardviewList) {
            this.context = context;
            this.cardviewList = cardviewList;
        }

        @Override
        public CardAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.activity_choose_calendar_home_cardview,viewGroup,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CardAdapter.ViewHolder viewHolder, int i) {
            final choose_calendar_cardview cardview = cardviewList.get(i);
            Drawable drawable = new BitmapDrawable() ;
           // viewHolder.downloadImageTask.execute(cardview.getURL());
            Glide.with(choose_calendar.this).load(cardview.getURL()).into(viewHolder.img);
            viewHolder.place.setText(cardview.getplace());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    choose_calendar_info.setChoose_calendar_info(cardview.getId_string(),cardview.getgmail(),cardview.getvege());
                    Intent intent = new Intent(choose_calendar.this, choose_calendar_info.class);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return cardviewList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{

            TextView place;
            DownloadImageTask downloadImageTask ;
            ImageView img;
            ViewHolder(View itemView){
                super(itemView);
               // downloadImageTask = new DownloadImageTask((ImageView)itemView.findViewById(R.id.choose_calendar_home_img));
                img = itemView.findViewById(R.id.choose_calendar_home_img);
                place =  itemView.findViewById(R.id.choose_calendar_home_place);

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
