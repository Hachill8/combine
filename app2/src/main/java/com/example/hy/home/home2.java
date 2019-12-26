package com.example.hy.home;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hy.GlobalVariable;
import com.example.hy.R;
import com.example.hy.forum.forum;
import com.example.hy.market.market;
import com.example.hy.search.VegeInfo;
import com.example.hy.search.search;
import com.example.hy.user_setting.user_setting;
import com.example.hy.webservice;

import java.util.ArrayList;
import java.util.List;

public class home2 extends AppCompatActivity{

    BottomSheetDialog bottomSheetDialog;
    ImageButton record,calendar,discuss,store,setting,user,hat,edit_pot;
    Dialog banboo_hat_level;

    GlobalVariable GV; //首頁作物照片(暫時)

    Button taipei_life,taipei_land_lease,taipei_farmers,search_bt;

    List<home2_plant_img_cardview> cardviewList;
    //找到UI工人的經紀人，這樣才能派遣工作  (找到顯示畫面的UI Thread上的Handler)
    private android.os.Handler mUI_Handler = new android.os.Handler();
    //宣告特約工人的經紀人
    private Handler mThreadHandler;
    //宣告特約工人
    private HandlerThread mThread;

    String user_vege,gmail;
    RecyclerView recyclerView;
    ProgressDialog mLoadingDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate( savedInstanceState );
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView( R.layout.activity_home2 );

        banboo_hat_level=new Dialog(this);

        cardviewList = new ArrayList<>();

        recyclerView = findViewById(R.id.home2_recyclerview);
//        taipei_life=(Button) findViewById(R.id.taipei_life);
//        taipei_life.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                AlertDialog.Builder mBuilder = new AlertDialog.Builder(home2.this);
//                View mView = getLayoutInflater().inflate(R.layout.choose_taipei_life_info, null);
//                //Dialog的標題
//                mBuilder.setTitle("");
//
//
//                taipei_land_lease=(Button)mView.findViewById(R.id.taipei_land_lease);
//                taipei_land_lease.setOnClickListener( new View.OnClickListener()
//                {
//                    @Override
//                    public void onClick(View v)
//                    {
//                        Intent a = new Intent(home2.this,taipei_life_info_land_lease.class);
//                        startActivity(a);
//                    }
//                } );
//
//                taipei_farmers=(Button)mView.findViewById(R.id.taipei_farmers);
//                taipei_farmers.setOnClickListener( new View.OnClickListener()
//                {
//                    @Override
//                    public void onClick(View v)
//                    {
//                        Intent a = new Intent(home2.this,taipei_life_info_farmers.class);
//                        startActivity(a);
//                    }
//                } );
//
//                mBuilder.setView(mView);
//                final AlertDialog dialog = mBuilder.create();
//                dialog.show();
//
//                //感應關閉後的事件
//                dialog.setOnDismissListener(new DialogInterface.OnDismissListener()
//                {
//                    @Override
//                    public void onDismiss(DialogInterface dialog) { }
//                });
//            }
//        } );
        //聘請一個特約工人，有其經紀人派遣其工人做事 (另起一個有Handler的Thread)
        mThread = new HandlerThread("");


        GV = (GlobalVariable) getApplicationContext();
        gmail = GV.getUser_gmail();
        //讓Worker待命，等待其工作 (開啟Thread)
        mThread.start();
        //找到特約工人的經紀人，這樣才能派遣工作 (找到Thread上的Handler)
        mThreadHandler=new Handler(mThread.getLooper());
        mThreadHandler.post(home2_cardview_r1);
        mLoadingDialog = new ProgressDialog(home2.this);
        showLoadingDialog("載入中...");

        edit_pot=(ImageButton) findViewById(R.id.edit_pot);
        edit_pot.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(home2.this);
                builder.setTitle("編輯盆栽☆即將推出，敬請期待!");
                builder.setMessage("查看所有盆栽，點擊可更改盆栽編號");
                builder.setPositiveButton("好", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog=builder.create();
                dialog.show();
            }
        } );


        hat=(ImageButton) findViewById(R.id.hat);
        hat.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ShowPopUp_level();
            }
        } );

        createBottomSheetDialog();

        search_bt=(Button) findViewById(R.id.search_bt);
        search_bt.setVisibility(View.VISIBLE);






            search_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(home2.this,search.class);
                    startActivity(intent);
                }
            });


    }

    Runnable  home2_cardview_r1 = new Runnable() {
        @Override
        public void run() {
            user_vege = webservice.Select_user_vege(gmail);

            mThreadHandler.post(home2_cardview_r2);


        }
    };

    Runnable  home2_cardview_r2 = new Runnable() {
        @Override
        public void run() {
            new Handler(Looper.getMainLooper()).post(new Runnable(){
                @Override
                public void run() {

                    Log.v("test","user_vege: "+user_vege);
                    if(user_vege.equals("can't not found"))
                    {
                        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
                        cardviewList.add(new home2_plant_img_cardview(0,"",R.drawable.gender));
                        cardviewList.add(new home2_plant_img_cardview(1,"",R.drawable.home_picture));
                        recyclerView.setAdapter(new home2.CardAdapter(home2.this, cardviewList));
                    }
                    else
                    {
                        search_bt.setVisibility(View.GONE);
                        cardviewList.clear();
                        String[] split=user_vege.split("%");
                        Log.v("test","user vege split length: "+split.length);
                        //首頁植物圖片判斷
                        for(int i = 0;i < split.length;i++)
                        {

                            switch (split[i]) {
                                case "紅蘿蔔":
                                    cardviewList.add(new home2_plant_img_cardview(i, split[i], R.drawable.vege_carrot_pot));
                                    break;
                                case "空心菜":
                                    cardviewList.add(new home2_plant_img_cardview(i, split[i], R.drawable.vege_kon_pot));
                                    break;
                                case "秋葵":
                                    cardviewList.add(new home2_plant_img_cardview(i, split[i], R.drawable.vege_ciu_pot));
                                    break;
                                case "小白菜":
                                    cardviewList.add(new home2_plant_img_cardview(i, split[i], R.drawable.vege_small_pot));
                                    break;
                                case "大白菜":
                                    cardviewList.add(new home2_plant_img_cardview(i, split[i], R.drawable.vege_chinese_cabbage_pot));
                                    break;
                                case "青花菜":
                                    cardviewList.add(new home2_plant_img_cardview(i, split[i], R.drawable.vege_broccoli_pot));
                                    break;
                                case "茄子":
                                    cardviewList.add(new home2_plant_img_cardview(i, split[i], R.drawable.vege_eggplant_pot));
                                    break;
                                case "高麗菜":
                                    cardviewList.add(new home2_plant_img_cardview(i, split[i], R.drawable.vege_cabbage_pot));
                                    break;
                                default:
                                    cardviewList.add(new home2_plant_img_cardview(i, split[i], R.drawable.no_vege_picture));
                                    break;
                            }
                            Log.v("test","for 的 i : "+i);
                        }
                        Log.v("test","cardviewList的長度是: "+ cardviewList.size());
                        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                        recyclerView.setAdapter(new home2.CardAdapter(home2.this, cardviewList));
                    }
                    dismissLoadingDialog();


                }
            });

        }
    };



    private void ShowPopUp_level()
    {
        banboo_hat_level.setContentView(R.layout.banboo_hat_level);
        banboo_hat_level.getWindow().setBackgroundDrawable(new ColorDrawable( Color.TRANSPARENT));
        banboo_hat_level.show();
    }

    private void createBottomSheetDialog()
    {

        if (bottomSheetDialog == null) {
            View view = LayoutInflater.from( this ).inflate( R.layout.bottom_sheet, null );

            record= view.findViewById( R.id.record );
            record.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Intent a = new Intent(home2.this, com.example.hy.record.record.class);
                    startActivity(a);
                }
            } );
            calendar= view.findViewById( R.id.calendar );
            calendar.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Intent a = new Intent(home2.this, com.example.hy.calendar.calendar.class);
                    startActivity(a);
                }
            } );
            discuss= view.findViewById( R.id.discuss );
            discuss.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Intent a = new Intent(home2.this, forum.class);
                    startActivity(a);
                }
            } );
            store= view.findViewById( R.id.store );
            store.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Intent a = new Intent(home2.this, market.class);
                    startActivity(a);
                }
            } );
            setting= view.findViewById( R.id.setting );
            setting.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Intent a = new Intent(home2.this, search.class);
                    startActivity(a);
                }
            } );
            user= view.findViewById( R.id.user );
            user.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Intent a = new Intent(home2.this, user_setting.class);
                    startActivity(a);
                }
            } );


            bottomSheetDialog = new BottomSheetDialog( this );
            bottomSheetDialog.setContentView( view );

            //設置bottomsheet圓角
            bottomSheetDialog.getWindow().findViewById(R.id.design_bottom_sheet)
                    .setBackgroundResource(android.R.color.transparent);
        }

    }




    public void showDialog(View view)
    {
        bottomSheetDialog.show();
    }


    private class CardAdapter extends  RecyclerView.Adapter<CardAdapter.ViewHolder>
    {
        private Context context;
        public List<home2_plant_img_cardview> cardviewList;

        CardAdapter(Context context, List<home2_plant_img_cardview> cardviewList) {
            this.context = context;
            this.cardviewList = cardviewList;
        }

        @Override
        public CardAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.activity_home2_plant_img,viewGroup,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CardAdapter.ViewHolder viewHolder, int i) {
            final home2_plant_img_cardview cardview = cardviewList.get(i);
            if(!cardview.getName().equals(""))
            {
                viewHolder.plant_name.setText(cardview.getId()+"  "+cardview.getName());
            }
            viewHolder.plant_img.setImageResource(cardview.getImage());

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
            ImageView plant_img;
            TextView plant_name;

            ViewHolder(View itemView){
                super(itemView);
                plant_img = itemView.findViewById(R.id.home2_plant_img);
                plant_name = itemView.findViewById(R.id.home2_plant_name);

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
