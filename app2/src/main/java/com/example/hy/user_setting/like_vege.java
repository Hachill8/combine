package com.example.hy.user_setting;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hy.DownloadImageTask;
import com.example.hy.GlobalVariable;
import com.example.hy.R;
import com.example.hy.forum.forum;
import com.example.hy.forum.forum_post;
import com.example.hy.forum.forum_post2;
import com.example.hy.like_vege_cardview;
import com.example.hy.search.VegeInfo;
import com.example.hy.search.search;
import com.example.hy.search.vege_cardview;
import com.example.hy.webservice;

import java.util.ArrayList;
import java.util.List;

public class like_vege extends AppCompatActivity {
    ImageButton back_user;
    ImageView novege_img;
    TextView novege_tv;
    Button go_search;
    List<like_vege_cardview> vegeList;
    RecyclerView recyclerView;
    like_vegeadaper adapter;
    String gmail,alllikevege,alldata;
    GlobalVariable gl;

    String[] split_line1    //切割篩選菜名
            ,split_line2    //圖片
            ,split_line3    //分開作物名稱和圖片
            ,split_line4    //hashtag1
            ,split_line5;   //hashtag2

    //找到UI工人的經紀人，這樣才能派遣工作  (找到顯示畫面的UI Thread上的Handler)
    private Handler mUI_Handler = new Handler();
    //宣告特約工人的經紀人
    private Handler mThreadHandler;
    //宣告特約工人
    private HandlerThread mThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_like_vege);

        //聘請一個特約工人，有其經紀人派遣其工人做事 (另起一個有Handler的Thread)
        mThread = new HandlerThread("");
        //讓Worker待命，等待其工作 (開啟Thread)
        mThread.start();
        //找到特約工人的經紀人，這樣才能派遣工作 (找到Thread上的Handler)
        mThreadHandler=new Handler(mThread.getLooper());

        back_user=findViewById(R.id.back_user);
        novege_img=findViewById(R.id.imageView);
        novege_tv= findViewById(R.id.textView5);
        go_search=findViewById(R.id.go_search);

        gl=(GlobalVariable)getApplicationContext();
        gmail=gl.getUser_gmail();
        back_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(like_vege.this, user_setting.class);
                startActivity(intent);
            }
        });
        go_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x=new Intent(like_vege.this, search.class);
                startActivity(x);
            }
        });

        vegeList=new ArrayList<>();
        mThreadHandler.post(r1);

    }

    private Runnable r1=new Runnable () {

        public void run() {
            alllikevege=webservice.Select_all_like_vege(gmail);
            //請經紀人指派工作名稱 r，給工人做
            mThreadHandler.post(r3);
        }

    };

    private Runnable r3=new Runnable () {

        public void run() {
            alldata=webservice.Select_user_like_vege(alllikevege);
            //請經紀人指派工作名稱 r，給工人做
            mUI_Handler.post(r4);

        }

    };

    private Runnable r4=new Runnable () {
        @Override
        public void run() {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    if (!alldata.equals("can't not found")) {
                        // String[] split_line3;
                        split_line3 = alldata.split("切"); //切割作物名和圖片編碼
                        split_line1 = split_line3[0].split("%");   //切割各作物
                        split_line2 = split_line3[1].split("圖");  //切割各圖片編碼
                        split_line4 = split_line3[2].split("%");   //hashtag1
                        split_line5 = split_line3[3].split("%");   //hashtag2

                        for (int i = 0; i < split_line4.length; i++) {
                            String[] split_line6 = split_line4[i].split("、");
                            List<Integer> index = new ArrayList<Integer>();
                            for (int j = 0; j < split_line6.length; j++) {
                                if (split_line6[j].contains("0") && !split_line6[j].contains("10")) {
                                    index.add(Integer.valueOf(split_line6[j].substring(1, 2)));
                                } else {
                                    index.add(Integer.valueOf(split_line6[j]));
                                }
                            }
                            int middle = 0;
                            split_line4[i] = "";
                            index.add(100); //給最大值避免比較時出錯
                            for (int k = 0; k < split_line6.length; k++) {
                                if (index.get(k) + 1 != index.get(k + 1) && index.get(k) - 11 != index.get(k + 1)) {
                                    if (split_line4[i].equals("")) {
                                        split_line4[i] = split_line6[0] + "~" + split_line6[k] + "月";
                                        middle = k;
                                    } else if (!split_line4[i].equals("")) {
                                        split_line4[i] = split_line4[i] + "、" + split_line6[middle + 1] + "~" + split_line6[k] + "月";
                                    }

                                }
                            }
                        }


                        int size1 = vegeList.size();
                        for (int i = 0; i < size1; i++) {
                            Log.v("test", "cardviewList.size(): " + vegeList.size());
                            vegeList.remove(0);
                        }

                        for (int num = 0; num < split_line1.length; num++) {
                            Bitmap bitmap = null;
                            byte[] decode = Base64.decode(split_line2[num], Base64.NO_CLOSE);
                            bitmap = BitmapFactory.decodeByteArray(decode, 0, decode.length);

                            vegeList.add(new like_vege_cardview(num, split_line1[num], bitmap, "#" + split_line4[num], "#" + split_line5[num]));

                        }
                        recyclerView = findViewById(R.id.recyclerView);
                        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
                        recyclerView.setAdapter(new like_vegeadaper(like_vege.this, vegeList));
                    }
                    else{novege_img.setVisibility(View.VISIBLE);novege_tv.setVisibility(View.VISIBLE);go_search.setVisibility(View.VISIBLE);}

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
            mThreadHandler.removeCallbacks(r3);
        }
        //解聘工人 (關閉Thread)
        if (mThread != null) {
            mThread.quit();
        }
    }

    private class like_vegeadaper extends RecyclerView.Adapter<like_vegeadaper.vegeviewholder>  {

        private Context mctx;
        private List<like_vege_cardview> vegeList;
        String s="";
        public like_vegeadaper(Context mctx, List<like_vege_cardview> vegeList) {
            this.mctx = mctx;
            this.vegeList = vegeList;
        }

        @Override
        public like_vegeadaper.vegeviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater=LayoutInflater.from(mctx);
            View view=inflater.inflate(R.layout.like_vege_cardview,viewGroup,false);
            return new like_vegeadaper.vegeviewholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull like_vegeadaper.vegeviewholder holder, int position) {
            final like_vege_cardview vege=vegeList.get(position);

            holder.vege.setText(String.valueOf(vege.getName()));
            holder.image.setImageBitmap(vege.getImage());
            holder.tag1.setText(String.valueOf(vege.getTag1()));
            holder.tag2.setText(String.valueOf(vege.getTag2()));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(like_vege.this, VegeInfo.class);
                    gl.setWord(vege.getName());
                    startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return vegeList.size();
        }



        class vegeviewholder extends RecyclerView.ViewHolder {
            ImageView image;
            TextView vege,tag1,tag2;

            public vegeviewholder(@NonNull View itemView) {
                super(itemView);
                image = (ImageView) itemView.findViewById(R.id.image);
                vege = (TextView) itemView.findViewById(R.id.vege);
                tag1 = (TextView) itemView.findViewById(R.id.tag1);
                tag2 = (TextView) itemView.findViewById(R.id.tag2);

            }


        }

    }

}
