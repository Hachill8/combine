package com.example.hy.search;

import android.app.ActionBar;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hy.GlobalVariable;
import com.example.hy.R;
import com.example.hy.custom_vegeinfo;
import com.example.hy.home.home2;
import com.example.hy.record.record;
import com.example.hy.record.record_Cardview;
import com.example.hy.record.record_Information2;
import com.example.hy.webservice;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class search extends AppCompatActivity  {
    ImageButton bt_filter,plus_vege,home;
    GlobalVariable vege_name,vege_item;
    String insert_vege_item="";
    String[] split_line={}; //搜尋的listview
    AutoCompleteTextView searchview;
    View v;
    Spinner mSpinner2;
    String select_month,getSelect_month="can't not found";
    //篩選
    Button select_item;
    boolean fg1=false; //確認點過select_item




    //找到UI工人的經紀人，這樣才能派遣工作  (找到顯示畫面的UI Thread上的Handler)
    private Handler mUI_Handler = new Handler();
    //宣告特約工人的經紀人
    private Handler mThreadHandler;
    //宣告特約工人
    private HandlerThread mThread;


    String[] split_line1    //切割篩選菜名
            ,split_line2    //圖片
            ,split_line3;   //分開作物名稱和圖片
    List<vege_cardview> cardviewList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.z_search);

        //cardview 建立
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vege_small);
        cardviewList = new ArrayList<>();
        cardviewList.add(new vege_cardview(0,"小白菜",bitmap,"#03~04、07~11月","#約30天可採收"));
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.record_vege_3);
        cardviewList.add(new vege_cardview(1,"空心菜",bitmap,"#04~08月","約30天可採收"));
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vege_ciu);
        cardviewList.add(new vege_cardview(2,"秋葵",bitmap,"#04~06月","#3~4個月可以採收"));
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vege_carrot);
        cardviewList.add(new vege_cardview(3,"紅蘿蔔",bitmap,"#08~02月","3~4個月可採收"));
        RecyclerView search_recyclerView = (RecyclerView) findViewById(R.id.search_recyclerView);
        search_recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL));
        search_recyclerView.setAdapter(new search.CardAdapter(this, cardviewList));



        //聘請一個特約工人，有其經紀人派遣其工人做事 (另起一個有Handler的Thread)
        mThread = new HandlerThread("");
        //讓Worker待命，等待其工作 (開啟Thread)
        mThread.start();
        //找到特約工人的經紀人，這樣才能派遣工作 (找到Thread上的Handler)
        mThreadHandler=new Handler(mThread.getLooper());



        //篩選
        bt_filter = (ImageButton) findViewById(R.id.BT_filter);
        bt_filter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(search.this);
                View mView = getLayoutInflater().inflate(R.layout.z_search_filter_layout, null);
                //Dialog的標題
                mBuilder.setTitle("");
                mSpinner2 = (Spinner) mView.findViewById(R.id.spinner2);

                //給予對應item的資料
                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(search.this,
                        R.layout.record_select_dropdown_item,
                        getResources().getStringArray(R.array.月份));
                //自訂getDropDownView()介面格式(Spinner介面展開時，View所使用的每個item格式)
                adapter2.setDropDownViewResource(R.layout.record_select_dropdown_item);
                //匯入item資料
                mSpinner2.setAdapter(adapter2);

                //篩選
                select_item = (Button) mView.findViewById(R.id.select_item);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                select_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        select_month=mSpinner2.getSelectedItem().toString();
                        Log.v("test", "ssssssss  " + select_month);
                        if(!select_month.equals("月份…")) {
                            select_month = select_month.replace("月", "");
                            Log.v("test", "111111111111  " + select_month);
                            //請經紀人指派工作名稱 r，給工人做
                            mThreadHandler.post(r1);
                        }
                        dialog.dismiss();
                    }
                });
                //感應關閉後的事件
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {

                    }
                });

                // 調整Dialog從哪開始
                Window dialogWindow = dialog.getWindow();
                dialogWindow.setGravity(Gravity.RIGHT | Gravity.TOP);

                // 去除四角黑色背景
                dialogWindow.setBackgroundDrawable(new BitmapDrawable());

                /* 將Dialog用螢幕大小百分比方式設置 */
                WindowManager m = getWindowManager();
                Display d = m.getDefaultDisplay(); // 取得螢幕寬和高
                WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 取得對話框目前數值
                p.height = (int) (d.getHeight() * 0.8); // 高度設為螢幕的0.6
                p.width = (int) (d.getWidth() * 0.75);  // 寬度設為螢幕的0.65
                dialogWindow.setAttributes(p);

            }
        });


        vege_name  = (GlobalVariable)getApplicationContext();
        vege_item  = (GlobalVariable)getApplicationContext();
        insert_vege_item = vege_item.getVege_item();



        searchview = (AutoCompleteTextView) findViewById(R.id.search_view);
        searchview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //點擊後抓searchview的文字並跳轉到作物資訊
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v("test","TextView:"+searchview.getText());
                vege_name.setWord(searchview.getText().toString());
                Intent x=new Intent(search.this,VegeInfo.class);
                startActivity(x);
            }
        });
        listview(v);

        //編輯自訂作物
        plus_vege= (ImageButton) findViewById(R.id.BT_plus);
        plus_vege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(search.this, custom_vegeinfo.class);
                startActivity(intent);
            }
        });

        //進入首頁
        home= (ImageButton) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(search.this, home2.class);
                startActivity(intent);
            }
        });

    }



    Runnable r1=new Runnable () {
        public void run() {
            getSelect_month =  webservice.Search_select(select_month);
            //請經紀人指派工作名稱 r，給工人做
            mUI_Handler.post(r2);
        }

    };
        //工作名稱 r2 的工作內容
    Runnable r2=new Runnable () {
        public void run() {

            if (!getSelect_month.equals("can't not found"))
            {
                // String[] split_line3;
                split_line3 = getSelect_month.split("切"); //切割作物名和圖片編碼
                split_line1 = split_line3[0].split("%");   //切割各作物
                split_line2 = split_line3[1].split("圖");  //切割各圖片編碼
                for(int num = 0; num < split_line1.length;num++)
                {
                    for(int i = 0; i < split_line1[num].length();i++)
                    {
                        if(split_line1[num].substring(i,i+1).equals(" "))
                        {
                            split_line1[num]=split_line1[num].substring(0,i);
                            Log.v("test","切割後的字串:"+split_line1[num]);
                            break;
                        }
                    }
                    for(int i = 0; i < split_line2[num].length();i++)
                    {
                        if(split_line2[num].substring(i,i+1).equals(" "))
                        {
                            split_line2[num]=split_line2[num].substring(0,i);
                            Log.v("test","切割後的字串:"+split_line2[num]);
                            break;
                        }
                    }
                }
            }

            int size1 = cardviewList.size();
            for(int i=0;i < size1;i++)
            {
                Log.v("test","cardviewList.size(): "+cardviewList.size());
                cardviewList.remove(0);
            }

            for(int num = 0; num < split_line1.length;num++)
            {
                Bitmap bitmap=null;
                byte[] decode = Base64.decode(split_line2[num],Base64.NO_CLOSE);
                bitmap = BitmapFactory.decodeByteArray(decode, 0, decode.length);
                cardviewList.add(new vege_cardview(num,split_line1[num], bitmap,"#03~04、07~11月","#1~3個月可以採收"));
            }

            RecyclerView search_recyclerView = (RecyclerView) findViewById(R.id.search_recyclerView);
            search_recyclerView.setLayoutManager(new StaggeredGridLayoutManager(split_line1.length, StaggeredGridLayoutManager.HORIZONTAL));
            search_recyclerView.setAdapter(new search.CardAdapter(search.this, cardviewList));
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




    //searchview的列表
    public void listview(View view)
    {

        if (!insert_vege_item.equals("")) {
            split_line = insert_vege_item.split("%");
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
        }
        searchview.setAdapter(new ArrayAdapter<>(search.this,
                android.R.layout.simple_list_item_1, split_line));
    }


    private class CardAdapter extends  RecyclerView.Adapter<search.CardAdapter.ViewHolder>{
        private Context context;
        public List<vege_cardview> cardviewList;

        CardAdapter(Context context, List<vege_cardview> cardviewList) {
            this.context = context;
            this.cardviewList = cardviewList;
        }

        @Override
        public search.CardAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.z_search_cardview,viewGroup,false);
            return new search.CardAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(search.CardAdapter.ViewHolder viewHolder, int i) {

            final vege_cardview cardview = cardviewList.get(i);

            viewHolder.vege.setText(String.valueOf(cardview.getName()));
            viewHolder.image.setImageBitmap(cardview.getImage());
            viewHolder.tag1.setText(String.valueOf(cardview.getTag1()));
            viewHolder.tag2.setText(String.valueOf(cardview.getTag2()));

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(search.this, VegeInfo.class);
                    vege_name.setWord(cardview.getName());
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return cardviewList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            ImageView image;
            TextView vege,tag1,tag2;

            ViewHolder(View itemView){
                super(itemView);
                image = (ImageView) itemView.findViewById(R.id.image);
                vege = (TextView) itemView.findViewById(R.id.vege);
                tag1 = (TextView) itemView.findViewById(R.id.tag1);
                tag2 = (TextView) itemView.findViewById(R.id.tag2);
            }
        }
        public  void addItem(int i){
            cardviewList.size();

//            fg = true;
//            num = cardviewList.size()-1;
//            //add(位置,資料)
//            cardviewList.add(i, new record_Cardview(id,"小白菜", R.drawable.icon201));
//            id=id+1;
//            notifyItemInserted(i);
        }
    }




}

