package com.example.hy.market;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.hy.GlobalVariable;
import com.example.hy.R;
import com.example.hy.home.home2;
import com.example.hy.search.VegeInfo;
import com.example.hy.search.search;
import com.example.hy.search.vege_cardview;
import com.example.hy.webservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class market extends AppCompatActivity
{

    //找到UI工人的經紀人，這樣才能派遣工作  (找到顯示畫面的UI Thread上的Handler)
    private Handler mUI_Handler = new Handler();
    //宣告特約工人的經紀人
    private Handler mThreadHandler;
    //宣告特約工人
    private HandlerThread mThread;
    private AutoCompleteTextView Search_bar; //搜尋商品
    String good_name="can't not found"; //商品字串
    String[] split_line={}; //搜尋的listview
    GlobalVariable market_item; //傳遞商品名稱

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_market2);

        Button goto_product_detail_1 = (Button) findViewById(R.id.goto_product_detail_1);
        goto_product_detail_1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent a = new Intent(market.this,market2.class);
                startActivity(a);
            }
        } );


        //聘請一個特約工人，有其經紀人派遣其工人做事 (另起一個有Handler的Thread)
        mThread = new HandlerThread("");
        //讓Worker待命，等待其工作 (開啟Thread)
        mThread.start();
        //找到特約工人的經紀人，這樣才能派遣工作 (找到Thread上的Handler)
        mThreadHandler=new Handler(mThread.getLooper());
        mThreadHandler.post(r1);

        market_item  = (GlobalVariable)getApplicationContext();
        Search_bar = (AutoCompleteTextView) findViewById(R.id.search_market_bar);
        Search_bar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //點擊後抓searchview的文字並跳轉到作物資訊
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v("test","TextView:"+Search_bar.getText());
                market_item.setMarket_item(Search_bar.getText().toString());
                Intent x=new Intent(market.this, market2.class);
                startActivity(x);
            }
        });

    }


    Runnable r1=new Runnable () {
        public void run() {
            good_name =  webservice.Goodname_list(good_name);
            //請經紀人指派工作名稱 r，給工人做
            mUI_Handler.post(r2);
        }

    };
    //工作名稱 r2 的工作內容
    Runnable r2=new Runnable () {
        public void run() {

            if (!good_name.equals("can't not found"))
            {
                split_line=good_name.split("%");   //searchview的列表

                for(int num = 0; num < split_line.length;num++)
                {
                    for(int i = 0; i < split_line[num].length();i++)
                    {
                        if(split_line[num].substring(i,i+1).equals(" "))
                        {
                            split_line[num]=split_line[num].substring(0,i);
                            Log.v("test","切割後的字串:"+split_line[num]);
                            break;
                        }
                    }
                }
                Search_bar.setAdapter(new ArrayAdapter<>(market.this,
                        android.R.layout.simple_list_item_1, split_line));
            }
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



