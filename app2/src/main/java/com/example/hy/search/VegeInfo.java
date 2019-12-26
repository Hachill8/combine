package com.example.hy.search;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hy.AutoSplitTextView;
import com.example.hy.GlobalVariable;
import com.example.hy.R;
import com.example.hy.calendar.choose_calendar;
import com.example.hy.home.home2;
import com.example.hy.market.market2;
import com.example.hy.market.market_cardview;
import com.example.hy.user_setting.user_setting;
import com.example.hy.webservice;
import com.varunest.sparkbutton.SparkEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class VegeInfo extends AppCompatActivity {

    GlobalVariable vege
            ,vege_home,gl;     //首頁作物照片(暫時)
    GlobalVariable market_item; //傳遞商品名稱
    Button start_plant,choose_calendar;
    com.varunest.sparkbutton.SparkButton like_vege;
    Dialog variety_info;
    String line="can't not found", vegeinfo_name,setdate,gmail,likeornot,pictureurl;
    AutoSplitTextView step/**  小撇步  **/,
            container/**  容器 **/,
            soil/**  土壤 **/,
            place/**  放置場所**/,
            water/**  澆水 **/,
            fertilizer/**  肥料 **/,
            bug/**  病蟲害 **/,
            harvest/**  收穫標準 **/,
            vege_name /**  菜名 **/;

    String vegeinfo_goods_info="";
    List<market_cardview> cardviewList;
    ImageView imageview;
    String img_result; //圖片字串
    boolean fg=true;
    Bitmap myBitmap;
     ProgressDialog mLoadingDialog;
    URL url;


//     ImageAdapter imgadapter;
//     //加入圖片用
//
//     ViewPager viewPager;


    //找到UI工人的經紀人，這樣才能派遣工作  (找到顯示畫面的UI Thread上的Handler)
    private Handler mUI_Handler = new Handler();
    //宣告特約工人的經紀人
    private Handler mThreadHandler;
    //宣告特約工人
    private HandlerThread mThread;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate( savedInstanceState );
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView( R.layout.activity_vege_info );

        //cardview1 建立
        cardviewList = new ArrayList <>();

        imageview = (ImageView)findViewById (R.id.vege_img);

        // 各欄位宣告

        step =  findViewById(R.id.step);
        container =  findViewById(R.id.container);
        soil =findViewById(R.id.soil);
        place =  findViewById(R.id.place);
        water = findViewById(R.id.water);
        fertilizer =  findViewById(R.id.fertilizer);
        bug =  findViewById(R.id.bug);
        harvest =  findViewById(R.id.harvest);
        vege_name =  findViewById(R.id.vege_name);
        like_vege = findViewById(R.id.spark_button);
        setdate = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date());
        gl= (GlobalVariable)getApplicationContext();
        gmail=gl.getUser_gmail();

        //globalvariable變數
        vege = (GlobalVariable)getApplicationContext();
        vegeinfo_name=vege.getWord();

        market_item = (GlobalVariable) getApplicationContext();

        Log.v("test","vege name: "+vegeinfo_name);

        //聘請一個特約工人，有其經紀人派遣其工人做事 (另起一個有Handler的Thread)
        mThread = new HandlerThread("");
        //讓Worker待命，等待其工作 (開啟Thread)
        mThread.start();
        //找到特約工人的經紀人，這樣才能派遣工作 (找到Thread上的Handler)
        mThreadHandler=new Handler(mThread.getLooper());
        //請經紀人指派工作名稱 r，給工人做
        mThreadHandler.post(r1);
        //首頁作物照片(暫時)
        vege_home = (GlobalVariable)getApplicationContext();

        mLoadingDialog = new ProgressDialog(VegeInfo.this);
        showLoadingDialog("載入中...");
        variety_info=new Dialog(this);
        start_plant=(Button)findViewById(R.id.start_plant);
        start_plant.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                vege_home.setVege_image_home(vege_name.getText().toString());
              //  Intent b = new Intent(VegeInfo.this, home_add_vege.class);
                mThreadHandler.post(r3);
                Intent b = new Intent(VegeInfo.this, home2.class);
                startActivity(b);
                VegeInfo.this.finish();

            }
        } );

        choose_calendar=(Button)findViewById(R.id.choose_calendar);
        choose_calendar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(VegeInfo.this, choose_calendar.class);
                startActivity(i);

//                AlertDialog.Builder builder = new AlertDialog.Builder(VegeInfo.this);
//                builder.setTitle("栽培日曆☆即將推出，敬請期待!");
//                builder.setMessage("可參考相近地區使用者的栽培日曆，查看該作物的栽種事項");
//                builder.setPositiveButton("好", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                AlertDialog dialog=builder.create();
//                dialog.show();
            }
        } );
        mThreadHandler.post(r7);
        like_vege.setEventListener(new SparkEventListener(){
            @Override
            public void onEvent(ImageView button, boolean buttonState) {
                if (buttonState) {
                    // 此判斷為第一次使用者點擊時
                    if(likeornot.equals("未查詢到資料")) {
                        if (fg == true) {
                            fg = false;likeornot="on";
                            mThreadHandler.post(r5);
                        }
                    }
                } else {
                    if(likeornot.substring(0,2).equals("on"))
                    {
                        if (fg == false) {
                            fg = true;likeornot="未查詢到資料";
                            mThreadHandler.post(r9);
                        }
                    }
                }
            }

            @Override
            public void onEventAnimationEnd(ImageView button, boolean buttonState) {
            }

            @Override
            public void onEventAnimationStart(ImageView button, boolean buttonState) {

            }

        });

    }

    //工作名稱 r1 的工作內容

    private Runnable r1=new Runnable () {

        public void run() {

            if(!vegeinfo_name.equals("No_message")) {
                line = webservice.VegeInfo_WS(vegeinfo_name);

                img_result = webservice.downImage(vegeinfo_name);
                Log.v("test","111122223333: "+line);
            }
            vegeinfo_goods_info = webservice.VegeInfo_Goods(vegeinfo_name);
            Log.v("test","vegeinfo goods info : "+vegeinfo_goods_info);

            //請經紀人指派工作名稱 r，給工人做
            mUI_Handler.post(r2);

        }

    };

    //工作名稱 r2 的工作內容

    private Runnable r2=new Runnable () {

        public void run() {

            String can = "can't not found";
            if (!line.equals("can't not found"))
            {
                            String[] split_line = line.split("%");

                            vege_name.setText(vegeinfo_name);
                            step.setText(split_line[0]);
                            container.setText(split_line[1]);
                            soil.setText(split_line[2]);
                            place.setText(split_line[3]);
                            water.setText(split_line[4]);
                            fertilizer.setText(split_line[5]);
                            bug.setText(split_line[6]);
                            harvest.setText(split_line[7]);
                if(split_line[8].contains("http")) {
                    pictureurl = split_line[8];
                    try {
                        url = new URL(pictureurl);
                        if (url.toString().contains("http")) {
                            mThreadHandler.post(r11);
                        }

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
                        }
            else if (line.equals(can))
            {
                            vege_name.setText(vegeinfo_name);
                            step.setText(can);
                            container.setText(can);
                            soil.setText(can);
                            place.setText(can);
                            water.setText(can);
                            fertilizer.setText(can);
                            bug.setText(can);
                            harvest.setText(can);
                        }
                        //下載照片
            if(!img_result.equals("")) {
                try {

                    Bitmap bitmap = null;
                    byte[] decode = Base64.decode(img_result, Base64.NO_CLOSE);
                    bitmap = BitmapFactory.decodeByteArray(decode, 0, decode.length);
                    Log.v("test", "bitmap: " + decode.toString());
                    imageview.setImageBitmap(bitmap);

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.v("test", "錯誤: " + e.toString());
                }
            }
            if(!vegeinfo_goods_info.equals("can't not found"))
            {
                String[] goods_all,goods_name,goods_price,goods_img;
                goods_all = vegeinfo_goods_info.split("分開");
                goods_name = goods_all[0].split("%");
                goods_price = goods_all[1].split("%");
                goods_img = goods_all[2].split("圖");
                for(int i = 0 ; i < goods_name.length;i++)
                {
                    Bitmap bitmap=null;
                    try {
                        byte[] decode = Base64.decode(goods_img[i],Base64.NO_CLOSE);
                        bitmap = BitmapFactory.decodeByteArray(decode, 0, decode.length);
                        dismissLoadingDialog();

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.v("test","錯誤: "+e.toString());
                    }


                    cardviewList.add(new market_cardview(i, bitmap,goods_name[i],goods_price[i]));
                }
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.market2_recyclerview1);
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(goods_name.length, StaggeredGridLayoutManager.VERTICAL));
                recyclerView.setAdapter(new VegeInfo.CardAdapter(VegeInfo.this, cardviewList));
            }



        }

    };

    private Runnable r3=new Runnable () {

        public void run() {

            webservice.Insert_vege(vegeinfo_name,gmail);

        }

    };
    private Runnable r5 = new Runnable() {
        @Override
        public void run() {
            webservice.Insert_like_vege(vegeinfo_name,likeornot,gmail);
        }
    };
    private Runnable r7 = new Runnable() {
        @Override
        public void run() {
            //post_name、post_title、gmail進去WS查出這個使用者是否有收藏這個作物
            likeornot=webservice.Select_like_vege(vegeinfo_name,gmail);
            Log.v("test123456", "QQQQ:::" +likeornot+gmail);
            mUI_Handler.post(r8);
        }
    };
    private Runnable r8 = new Runnable() {
        @Override
        public void run() {
            if(likeornot.substring(0,2).equals("on"))
            {fg=false;like_vege.setChecked(true);}
            else if(likeornot.equals("未查詢到資料"))
            {fg=true;like_vege.setChecked(false);}
        }
    };
    private Runnable r9 = new Runnable() {
        @Override
        public void run() {
            webservice.Delete_like_vege(vegeinfo_name,gmail);
        }
    };

    private Runnable r11=new Runnable () {

        public void run() {
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                myBitmap = BitmapFactory.decodeStream(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //請經紀人指派工作名稱 r，給工人做
            mUI_Handler.post(r12);

        }

    };

    private Runnable r12=new Runnable () {

        public void run() {

            imageview .setImageBitmap(myBitmap);
            dismissLoadingDialog();
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


    private class CardAdapter extends  RecyclerView.Adapter<VegeInfo.CardAdapter.ViewHolder>
    {
        private Context context;
        public List <market_cardview> cardviewList;

        CardAdapter(Context context, List<market_cardview> cardviewList) {
            this.context = context;
            this.cardviewList = cardviewList;
        }

        @Override
        public VegeInfo.CardAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.activity_market2_cardview,viewGroup,false);
            return new VegeInfo.CardAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final VegeInfo.CardAdapter.ViewHolder viewHolder, int i) {
            final market_cardview cardview = cardviewList.get(i);
            viewHolder.name.setText(String.valueOf(cardview.getName()));
            viewHolder.product_img.setImageBitmap(cardview.getImage());
            viewHolder.price.setText("NT$ "+cardview.getPrice());

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //              addItem(cardviewList.size());
                    Log.v("test","cardview.getName:"+cardview.getName());
                    market_item.setMarket_item(cardview.getName());
                    Intent intent = new Intent(VegeInfo.this, market2.class);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount()
        { return cardviewList.size(); }

        class ViewHolder extends RecyclerView.ViewHolder
        {
            ImageView product_img;
            TextView name,price;
            ViewHolder(View itemView)
            {
                super(itemView);
                product_img = (ImageView) itemView.findViewById(R.id.goto_product_detail);
                name = (TextView) itemView.findViewById(R.id.product_name);
                price = (TextView) itemView.findViewById(R.id.product_price);
            }
        }

        public  void addItem(int i)
        {
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

