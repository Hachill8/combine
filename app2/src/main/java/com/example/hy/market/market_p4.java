package com.example.hy.market;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hy.R;
import com.example.hy.record.record;
import com.example.hy.record.record_Cardview;
import com.example.hy.record.record_Information2;
import com.example.hy.webservice;

import java.util.ArrayList;
import java.util.List;

public class market_p4 extends AppCompatActivity
{
    //Spinner deliver_spinner,pay_spinner;
    //找到UI工人的經紀人，這樣才能派遣工作  (找到顯示畫面的UI Thread上的Handler)
    private Handler mUI_Handler = new Handler();
    //宣告特約工人的經紀人
    private Handler mThreadHandler;
    //宣告特約工人
    private HandlerThread mThread;
    String product_info="can't not found";
    String[] split_line , //把一大堆資訊切成每個cardview資訊
            split_line2 ;//細切cardview資訊
    //cardview 建立
    List <market_p4_cardview> cardviewList = new ArrayList <>();
    RecyclerView recyclerView;
    TextView product_amount_sum, //共幾個商品
            product_price_sum,   //商品共多少錢
            product_all_price,   //商品錢+運費
            fare,                //運費
            cart_title;          //購物車title

    int getProduct_amount_sum = 0, //共幾個商品
            getProduct_price_sum = 0,   //商品共多少錢
            getProduct_all_price = 0;   //商品錢+運費

    int remove_cardview_id=0; //從購物車刪除
    String remove_ans=""; //刪除的狀況
    Button to_pay;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_market4);

        recyclerView = (RecyclerView) findViewById(R.id.market4_shopping_cart_recyclerView);
        to_pay = findViewById(R.id.to_pay);
        to_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(market_p4.this,market_to_pay.class);
                startActivity(i);
            }
        });
        product_amount_sum = (TextView) findViewById(R.id.product_amount_sum);
        product_price_sum = (TextView) findViewById(R.id.product_price_sum);
        product_all_price = (TextView) findViewById(R.id.product_all_price);
        fare = (TextView) findViewById(R.id.fare);
        cart_title = (TextView) findViewById(R.id.cart_title);
        Button back_2_market = (Button) findViewById(R.id.back_2_market);
        back_2_market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(market_p4.this,market.class);
                startActivity(i);
                market_p4.this.finish();
            }
        });

//        //spinner建立
//        deliver_spinner = (Spinner) findViewById(R.id.shopping_cart_deliver_spinner);
//        pay_spinner = (Spinner) findViewById(R.id.shopping_cart_pay_spinner);

//        //給予對應item的資料
//        ArrayAdapter <String> adapter1 = new ArrayAdapter<String>(market_p4.this,
//                R.layout.market4_select_dropdown_item,                            //選項資料內容
//                getResources().getStringArray(R.array.運送方式));   //自訂getView()介面格式(Spinner介面未展開時的View)
//        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(market_p4.this,
//                R.layout.market4_select_dropdown_item,
//                getResources().getStringArray(R.array.付款方式));

//        //匯入item資料
//        deliver_spinner.setAdapter(adapter1);
//        pay_spinner.setAdapter(adapter2);

        //聘請一個特約工人，有其經紀人派遣其工人做事 (另起一個有Handler的Thread)
        mThread = new HandlerThread("");
        //讓Worker待命，等待其工作 (開啟Thread)
        mThread.start();
        //找到特約工人的經紀人，這樣才能派遣工作 (找到Thread上的Handler)
        mThreadHandler=new Handler(mThread.getLooper());


        mThreadHandler.post(r1);

    }

    private class CardAdapter  extends  RecyclerView.Adapter<market_p4.CardAdapter.ViewHolder>
    {
        private Context context;
        public List<market_p4_cardview> cardviewList;

        CardAdapter(Context context, List<market_p4_cardview> cardviewList) {
            this.context = context;
            this.cardviewList = cardviewList;
        }

        @Override
        public market_p4.CardAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.activity_market4_cardview,viewGroup,false);
            return new market_p4.CardAdapter.ViewHolder(view);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(market_p4.CardAdapter.ViewHolder viewHolder, int i)
        {
            final market_p4_cardview cardview = cardviewList.get(i);
            final int dele_cardviewlist = i;
            viewHolder.name.setText(String.valueOf(cardview.getName()));
            viewHolder.product_img.setImageBitmap(cardview.getImage());
            viewHolder.sum.setText("小計：NT$ "+String.valueOf(Integer.valueOf(cardview.getPrice())*Integer.valueOf(cardview.getAmount())));
            viewHolder.price.setText("單價：NT$ "+String.valueOf(cardview.getPrice()));
            viewHolder.amount.setText("數量: "+cardview.getAmount());
            viewHolder.remove_product_in_cart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    remove_cardview_id = cardview.getId();
                    if(cardviewList.size() > 0)
                    {
                        Log.v("test","dele_cardviewlist "+dele_cardviewlist);
                        getProduct_all_price = getProduct_all_price - (Integer.valueOf(cardview.getPrice())*Integer.valueOf(cardview.getAmount()));
                        getProduct_amount_sum = getProduct_amount_sum - Integer.valueOf(cardview.getAmount()) ;
                        getProduct_price_sum = getProduct_price_sum - (Integer.valueOf(cardview.getPrice())*Integer.valueOf(cardview.getAmount()));
                        if(cardviewList.size()==1)
                        {
                            fare.setText("運費：NT$ 0");
                            getProduct_all_price = 0;
                        }
                        cardviewList.remove(dele_cardviewlist);
                        recyclerView.setAdapter(new market_p4.CardAdapter(market_p4.this, cardviewList));
                    }

                    mThreadHandler=new Handler(mThread.getLooper());
                    mThreadHandler.post(r3);

                }
            });




            viewHolder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
//                    addItem(cardviewList.size());
//                    Intent intent = new Intent(market_p4.this, market2.class);
//                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return cardviewList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{

            ImageView product_img;
            TextView name,price,sum,amount;
            Button remove_product_in_cart;

            ViewHolder(View itemView){
                super(itemView);
                product_img = (ImageView) itemView.findViewById(R.id.goto_product_detail_1);
                name = (TextView) itemView.findViewById(R.id.product_name);
                price = (TextView) itemView.findViewById(R.id.product_price1);
                sum = (TextView) itemView.findViewById(R.id.sum1);
                amount = (TextView) itemView.findViewById(R.id.etAmount);
                remove_product_in_cart = (Button) itemView.findViewById(R.id.remove_product_in_cart);

//                btnIncrease = (Button) itemView.findViewById(R.id.btnIncrease);
//                btnDecrease = (Button) itemView.findViewById(R.id.btnDecrease);
            }
        }
        public  void addItem(int i){
//            fg = true;
////            num = cardviewList.size()-1;
////            //add(位置,資料)
////            cardviewList.add(i, new record_Cardview(id,"小白菜", R.drawable.icon201));
////            id=id+1;
////            notifyItemInserted(i);
        }
    }




    //工作名稱 r1 的工作內容

    Runnable r1=new Runnable () {

        public void run() {

            product_info = webservice.Cart_cardview("");
            //請經紀人指派工作名稱 r，給工人做
            if(!product_info.equals("can't not found"))
            {
                mUI_Handler.post(r2);
            }

        }

    };

    //工作名稱 r2 的工作內容

    Runnable r2=new Runnable () {

        public void run() {

                split_line = product_info.split("圖");
                Log.v("test","cardviewList.size(): "+cardviewList.size());


                for (int i = 0; i < split_line.length; i++)
                {
                    split_line2 = String.valueOf(split_line[i]).split("切");
                    //id +"切" +good + "切" + price + "切" + num + "切" + returnStr +"圖"
                    Bitmap bitmap = null;
                    byte[] decode = Base64.decode(split_line2[4], Base64.NO_CLOSE);
                    bitmap = BitmapFactory.decodeByteArray(decode, 0, decode.length);
                    cardviewList.add(new market_p4_cardview(Integer.valueOf(split_line2[0]), split_line2[1], split_line2[2] ,bitmap, split_line2[3]));
                    //(int id,String name,String price,int image,String amount)
                    getProduct_amount_sum = getProduct_amount_sum + Integer.valueOf(split_line2[3]);
                    getProduct_price_sum = getProduct_price_sum + (Integer.valueOf(split_line2[2]) * Integer.valueOf(split_line2[3]));
                    Log.v("test","id是: "+split_line2[0]);

                }
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
                recyclerView.setAdapter(new market_p4.CardAdapter(market_p4.this, cardviewList));

                getProduct_all_price = getProduct_price_sum + 80;
                product_amount_sum.setText("共計 "+getProduct_amount_sum+" 件商品");
                cart_title.setText("購物車("+getProduct_amount_sum+")");
                product_price_sum.setText("商品總計：NT$ "+getProduct_price_sum);
                product_all_price.setText("應付金額：NT$ "+getProduct_all_price);
                fare.setText("運費：NT$ 80");


        }

    };


    Runnable r3=new Runnable () {

        public void run() {
            if (remove_cardview_id > 0)
            {
                remove_ans = webservice.Cart_cardview_dele(String.valueOf(remove_cardview_id));
            }
            //請經紀人指派工作名稱 r，給工人做
            mUI_Handler.post(r4);
        }

    };

    Runnable r4=new Runnable () {

        public void run() {
            product_amount_sum.setText("共計 "+getProduct_amount_sum+" 件商品");
            cart_title.setText("購物車("+getProduct_amount_sum+")");
            product_price_sum.setText("商品總計：NT$ "+getProduct_price_sum);
            product_all_price.setText("應付金額：NT$ "+getProduct_all_price);
            if(remove_cardview_id > 0)
            {
                Toast.makeText(market_p4.this,remove_ans,Toast.LENGTH_SHORT).show();
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
        if (mThreadHandler != null) {
            mThreadHandler.removeCallbacks(r3);
        }
        //解聘工人 (關閉Thread)
        if (mThread != null) {
            mThread.quit();
        }
    }



}
