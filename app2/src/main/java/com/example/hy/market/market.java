package com.example.hy.market;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.hy.GlobalVariable;
import com.example.hy.R;
import com.example.hy.home.home2;
import com.example.hy.record.record;
import com.example.hy.record.record_Cardview;
import com.example.hy.record.record_Information2;
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
        market_item  = (GlobalVariable)getApplicationContext();
        Button market_to_cart = (Button)findViewById(R.id.market_to_cart);
        market_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(market.this,market_p4.class);
                startActivity(i);
                market.this.finish();
            }
        });

        Button search_market_bar_to_productinfo = (Button)findViewById(R.id.search_market_bar_to_productinfo);
        search_market_bar_to_productinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("test","TextView:"+Search_bar.getText());
                for(int i =0;i<split_line.length;i++)
                {
                    if(Search_bar.getText().toString().equals(split_line[i]) && (!Search_bar.getText().toString().equals("")))
                    {
                        market_item.setMarket_item(Search_bar.getText().toString());
                        Intent x=new Intent(market.this, market2.class);
                        startActivity(x);
                        market.this.finish();
                    }
                    else if(i+1==split_line.length)
                    {
                        Toast.makeText(market.this,"查無資料!",Toast.LENGTH_SHORT).show();
                    }
                }




            }
        });

        Search_bar = (AutoCompleteTextView) findViewById(R.id.search_market_bar);
        Search_bar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //點擊後抓searchview的文字並跳轉到作物資訊
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v("test","TextView:"+Search_bar.getText());
                market_item.setMarket_item(Search_bar.getText().toString());
                Intent x=new Intent(market.this, market2.class);
                startActivity(x);
                market.this.finish();
            }
        });

        //聘請一個特約工人，有其經紀人派遣其工人做事 (另起一個有Handler的Thread)
        mThread = new HandlerThread("");
        //讓Worker待命，等待其工作 (開啟Thread)
        mThread.start();
        //找到特約工人的經紀人，這樣才能派遣工作 (找到Thread上的Handler)
        mThreadHandler=new Handler(mThread.getLooper());
        mThreadHandler.post(r1);

        //cardview1 建立
        List<market_cardview> cardviewList = new ArrayList<>();
        cardviewList.add(new market_cardview(0,R.drawable.market_product_1,"白鐵移植鏝","85"));
        cardviewList.add(new market_cardview(1,R.drawable.market_product_2,"長型盆栽","199"));
        cardviewList.add(new market_cardview(2,R.drawable.market_product_3,"二合一澆水器","268"));
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.market2_recyclerview1);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(new market.CardAdapter(this, cardviewList));

        //cardview2 建立
        List<market_cardview> cardviewList2 = new ArrayList<>();
        cardviewList2.add(new market_cardview(0,R.drawable.market_product_4,"園藝工具組","228"));
        cardviewList2.add(new market_cardview(1,R.drawable.market_product_5,"靚土培養土","159"));
        cardviewList2.add(new market_cardview(2,R.drawable.market_product_6,"防刺園藝手套","178"));
        RecyclerView recyclerView2 = (RecyclerView) findViewById(R.id.market2_recyclerview2);
        recyclerView2.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        recyclerView2.setAdapter(new market.CardAdapter(this, cardviewList2));

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




    private class CardAdapter extends  RecyclerView.Adapter<market.CardAdapter.ViewHolder>{
        private Context context;
        public List<market_cardview> cardviewList;

        CardAdapter(Context context, List<market_cardview> cardviewList) {
            this.context = context;
            this.cardviewList = cardviewList;
        }

        @Override
        public market.CardAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.activity_market2_cardview,viewGroup,false);
            return new market.CardAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(market.CardAdapter.ViewHolder viewHolder, int i) {
            final market_cardview cardview = cardviewList.get(i);
            viewHolder.name.setText(String.valueOf(cardview.getName()));
            viewHolder.product_img.setImageResource(cardview.getImg());
            viewHolder.price.setText("NT$ "+cardview.getPrice());

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addItem(cardviewList.size());
                    market_item.setMarket_item(String.valueOf(cardview.getName()));
                    Intent intent = new Intent(market.this, market2.class);
                    startActivity(intent);
                    market.this.finish();
                }
            });
        }

        @Override
        public int getItemCount() {
            return cardviewList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            ImageView product_img;
            TextView name,price;

            ViewHolder(View itemView){
                super(itemView);
                product_img = (ImageView) itemView.findViewById(R.id.goto_product_detail);
                name = (TextView) itemView.findViewById(R.id.product_name);
                price = (TextView) itemView.findViewById(R.id.product_price);
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



