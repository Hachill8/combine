package com.example.hy.market;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hy.GlobalVariable;
import com.example.hy.R;
import com.example.hy.webservice;

public class market2 extends AppCompatActivity
{
    private int amount=1,count_product_num_in_cart=0;
    public TextView etAmount,product_num_in_cart;
    private Button btnDecrease;
    private Button btnIncrease;
    private Button back_market;
    private Button add_to_cart;
    private Button market_cart;
    //找到UI工人的經紀人，這樣才能派遣工作  (找到顯示畫面的UI Thread上的Handler)
    private Handler mUI_Handler = new Handler();
    //宣告特約工人的經紀人
    private Handler mThreadHandler;
    //宣告特約工人
    private HandlerThread mThread;
    GlobalVariable market_item;
    private String good_name,line="can't not found";

    //商品資訊
    private TextView Product_name1,Product_name2,Price,Product_dimension,Origin,Characteristic;
    String img_result; //圖片字串
    ImageView Product_img;

    String product_cart_info=""; //抓商品相關資料給購物車

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_market3);

        //各TextViewfind ID
        etAmount = (TextView) findViewById(R.id.etAmount);
        product_num_in_cart = (TextView) findViewById(R.id.product_num_in_cart);

        Product_name1 = (TextView)findViewById(R.id.product_name1);
        Product_name2 = (TextView)findViewById(R.id.product_name2);
        Price = (TextView)findViewById(R.id.price);
        Product_dimension = (TextView)findViewById(R.id.product_format);
        Origin = (TextView)findViewById(R.id.origin);
        Characteristic = (TextView)findViewById(R.id.characteristic);
        Product_img = (ImageView)findViewById(R.id.product_img);
        market_cart = (Button)findViewById(R.id.market_cart);
        market_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(market2.this,market_p4.class);
                startActivity(i);
            }
        });



        market_item = (GlobalVariable)getApplicationContext();
        good_name=market_item.getMarket_item();

        //聘請一個特約工人，有其經紀人派遣其工人做事 (另起一個有Handler的Thread)
        mThread = new HandlerThread("");
        //讓Worker待命，等待其工作 (開啟Thread)
        mThread.start();
        //找到特約工人的經紀人，這樣才能派遣工作 (找到Thread上的Handler)
        mThreadHandler=new Handler(mThread.getLooper());
        //請經紀人指派工作名稱 r，給工人做
        mThreadHandler.post(r1);


        //加商品數量
        btnIncrease = (Button) findViewById(R.id.btnIncrease);
        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = v.getId();
                if (i == R.id.btnIncrease) {
                    amount++;
                    etAmount.setText(amount + "");
                }
            }
        });
        //減商品數量
        btnDecrease = (Button) findViewById(R.id.btnDecrease);
        btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int i = v.getId();
                if (i == R.id.btnDecrease)
                {
                    if (amount > 1) {
                        amount--;
                        etAmount.setText(amount + "");
                    } else {
                        amount = 1;
                        etAmount.setText(amount + "");
                    }
                }
            }
        });

        //回MARKET
        back_market = (Button) findViewById(R.id.back_market);
        back_market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(market2.this, market.class);
                startActivity(a);
            }
        });

        //按下加入購物車
        add_to_cart = (Button) findViewById(R.id.add_to_cart);
        add_to_cart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int i = v.getId();
                if (i == R.id.add_to_cart)
                {
                    count_product_num_in_cart=count_product_num_in_cart+amount;
//                    product_num_in_cart.setText(count_product_num_in_cart+ "");
                    product_cart_info = line +"切"+amount;
                    Log.v("test","product_cart_info: "+product_cart_info);
                    //請經紀人指派工作名稱 r，給工人做
                    mThreadHandler.post(r1);
                }

            }
        });
    }




    //工作名稱 r1 的工作內容

    private Runnable r1=new Runnable () {

        public void run() {

            if(!good_name.equals("無") && product_cart_info.equals("")) {
                line = webservice.GoodInfo_WS(good_name);
            }

            if(!product_cart_info.equals(""))
            {
                product_cart_info = webservice.Good_in_cart(product_cart_info);
            }
            Log.v("test","line的內容:3   "+line);
            //請經紀人指派工作名稱 r，給工人做
            mUI_Handler.post(r2);

        }

    };

    //工作名稱 r2 的工作內容

    private Runnable r2=new Runnable () {

        public void run() {

            String can = "無";
            if (!line.equals("can't not found") && product_cart_info.equals("") )
            {
                Log.v("test","line的內容:2   "+line);
                String[] split_line = line.split("切");
                Log.v("test","line的內容:   "+line);
//                Log.v("test","Split line 的內容是: "+split_line[0]+"   和   "+split_line[1]+"   和   "+split_line[2]);
                Product_name1.setText(good_name);
                Product_name2.setText(good_name);
                Price.setText("$ "+split_line[0]);
                Product_dimension.setText(split_line[1]);
                Origin.setText(split_line[2]);
                Characteristic.setText(split_line[3]);
                img_result = split_line[5];     //split_line[4]是商品數量
                product_num_in_cart.setText(split_line[6]);
                //下載照片
                try {

                    Bitmap bitmap = null;
                    byte[] decode = Base64.decode(img_result, Base64.NO_CLOSE);
                    bitmap = BitmapFactory.decodeByteArray(decode, 0, decode.length);
                    Log.v("test", "bitmap: " + decode.toString());
                    Product_img.setImageBitmap(bitmap);


                } catch (Exception e) {
                    e.printStackTrace();
                    Log.v("test", "圖片錯誤: " + e.toString());
                }

                Log.v("test","product_num_in_cart: "+img_result);

                line = good_name+"切"+split_line[0]+"切"+good_name;
 //               商品數量.setText(split_line[4]);

            }
            else if(line.equals("can't not found") && product_cart_info.equals(""))
            {
                Product_name1.setText(good_name);
                Product_name2.setText(good_name);
                Price.setText(can);
                Product_dimension.setText(can);
                Origin.setText(can);
                Characteristic.setText(can);
                //               商品數量.setText(split_line[4]);
            }
            if(!product_cart_info.equals(""))
            {
                String[] split_info;
                if(product_cart_info.contains("切"))
                {
                    split_info = product_cart_info.split("切");
                    product_num_in_cart.setText(split_info[1]);
                    Toast.makeText(market2.this,
                            split_info[0],Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(market2.this,
                            product_cart_info,Toast.LENGTH_SHORT).show();
                }
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
