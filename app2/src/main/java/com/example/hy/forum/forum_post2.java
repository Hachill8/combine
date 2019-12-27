package com.example.hy.forum;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Html;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.hy.GlobalVariable;
import com.example.hy.R;
import com.example.hy.record.record;
import com.example.hy.webservice;
import com.varunest.sparkbutton.SparkEventListener;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class forum_post2 extends AppCompatActivity {

    TextView post_content_tv,post_title_tv,post_time,post_name,post_like_tv;
    GlobalVariable gl;
    String content_post,post_title,post_like_value;
    FloatingActionButton post_message;
    EditText add_message;
    Button postTV_to_forum;
    com.varunest.sparkbutton.SparkButton post_like,keep_post;
    String[] split;
    String post_all="",OKOK,likeornot,gmail,user_like,post_id;
    int likenum;
    boolean fg=true,post_like_fg=false;
    //找到UI工人的經紀人，這樣才能派遣工作  (找到顯示畫面的UI Thread上的Handler)
    private Handler mUI_Handler = new Handler();
    //宣告特約工人的經紀人
    private Handler mThreadHandler;
    //宣告特約工人
    private HandlerThread mThread;
    String line;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.forum_post);

        post_content_tv = (TextView) findViewById(R.id.post_content_txv);
        post_title_tv = (TextView) findViewById(R.id.post_title_txv);
        post_name = findViewById(R.id.user_name);
        post_message = findViewById(R.id.post_message);
        post_time = (TextView) findViewById(R.id.post_time);
        postTV_to_forum = (Button) findViewById(R.id.postTV_to_forum);
        keep_post = findViewById(R.id.keep_post);
        keep_post.setEventListener(new SparkEventListener(){
            @Override
            public void onEvent(ImageView button, boolean buttonState) {
                if (buttonState) {

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
        post_like = findViewById(R.id.spark_button);
        post_like_tv =  findViewById(R.id.like_tv);
        postTV_to_forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(forum_post2.this, forum.class);
                startActivity(i);
                forum_post2.this.finish();
            }
        });

        //聘請一個特約工人，有其經紀人派遣其工人做事 (另起一個有Handler的Thread)
        mThread = new HandlerThread("");
        //讓Worker待命，等待其工作 (開啟Thread)
        mThread.start();
        //找到特約工人的經紀人，這樣才能派遣工作 (找到Thread上的Handler)
        mThreadHandler=new Handler(mThread.getLooper());


        //文章內容
        gl = (GlobalVariable) getApplicationContext();
        gmail = gl.getUser_gmail();
//        if(gl.getForum_title_click() > -1)
//        {
//            mThreadHandler.post(r1);
//        }
//        else
//        {
            content_post = gl.getForum_content();
            post_title_tv.setText(gl.getForum_title());
            mThreadHandler.post(r1);
//            //文章時間
//            Calendar mT = Calendar.getInstance();
//            CharSequence sT = DateFormat.format("yyyy-MM-dd kk:mm:ss", mT.getTime());    // kk:24小時制, hh:12小時制
//            post_time.setText(sT.toString());
//            Log.v("test", "content_post::::: " + content_post);
//            content_post = content_post.replace("\n", "<br/>");
//
//            //文章圖片
//            MyImageGetter myImageGetter = new MyImageGetter();
//            post_content_tv.setText(Html.fromHtml(content_post, myImageGetter, null));
 //       }




        //cardview 建立
        //public forum_post_message_cardview(int id,String name,int image,String time,String message,int B_num)
        final List<forum_post_message_cardview> cardviewList = new ArrayList<>();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.message_recyclerView);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(new CardAdapter(this, cardviewList));


        //留言的Dialog
        post_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(forum_post2.this);
                View mView = getLayoutInflater().inflate(R.layout.forum_post_message, null);
                //Dialog的標題
                mBuilder.setTitle("");

                add_message = (EditText) mView.findViewById(R.id.insert_post_message);

                //內建可用Button的方式
                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mThreadHandler.post(r10);
                        String add_message_str = add_message.getText().toString();
                        int j = cardviewList.size();
                        Calendar mCal = Calendar.getInstance();
                        CharSequence s = DateFormat.format("yyyy-MM-dd kk:mm:ss", mCal.getTime());    // kk:24小時制, hh:12小時制
                        String date = s.toString();
                        cardviewList.add(new forum_post_message_cardview(j, "猴嗨森", R.drawable.user10, date, add_message_str, "B" + (j + 1)));
                    }
                });

                //內建可用Button的方式
                mBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();


                dialog.show();
                // 調整Dialog從哪開始
                Window dialogWindow = dialog.getWindow();
                dialogWindow.setGravity(Gravity.CENTER);

//              // 去除四角黑色背景
//              dialogWindow.setBackgroundDrawable(new BitmapDrawable());

                /* 將Dialog用螢幕大小百分比方式設置 */
                WindowManager m = getWindowManager();
                Display d = m.getDefaultDisplay(); // 取得螢幕寬和高
                WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 取得對話框目前數值
                p.height = (int) (d.getHeight() * 0.2); // 高度設為螢幕的0.8
                p.width = (int) (d.getWidth() * 0.85);  // 寬度設為螢幕的0.75
                dialogWindow.setAttributes(p);
            }

        });

        post_like.setEventListener(new SparkEventListener(){
            @Override
            public void onEvent(ImageView button, boolean buttonState) {
                    if (post_like_fg) {
                         likenum = likenum - 1;
                         post_like_fg=false;
                         mThreadHandler.post(r3);
                         Log.v("test", "ID: " + split[5] + "  gmail2: " + gmail+"  likenum: "+likenum);
                         mThreadHandler.post(user_like_post);
                     } else {
                         likenum = likenum + 1;
                         post_like_fg=true;
                         mThreadHandler.post(r3);
                         Log.v("test", "ID: " + split[5] + "  gmail: " + gmail+"  likenum: "+likenum);
                         mThreadHandler.post(user_like_post);

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

    private Runnable r10 = new Runnable() {
        @Override
        public void run() {
            line = webservice.Add_message(add_message.getText().toString());
}
    };


    private Runnable r1 = new Runnable() {
        @Override
        public void run() {
//            if(gl.getForum_title_click() > -1) {
//                post_all = webservice.forum_post_view(gl.getForum_title_click());
//                Log.v("test","if~~~~~~~~~~~~~~~   "+gl.getForum_title_click());
//                gl.setForum_title_click(-1);
//
//            }
//            else
//            {
                post_all = webservice.forum_post_view2(gl.getForum_title());
                Log.v("test","else~~~~~~~~~~~~~~        "+gl.getForum_title());
  //          }

            mUI_Handler.post(r2);
        }
    };

    private Runnable r2 = new Runnable() {
        @Override
        public void run() {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    //title ,content,time,name
                    Log.v("test","post_all"+post_all);
                    split = post_all.split("%");
                    post_title_tv.setText(split[0]);
                    post_time.setText(split[2]);
                    Log.v("test", "content_post::::: " + content_post);
                    content_post = split[1];
                    post_id=split[5];
                    content_post = content_post.replace("\n", "<br/>");
                    if(content_post.contains("https"))
                    {
                        content_post = content_post.substring(0,content_post.indexOf("https")-1)+"<img src=\""+content_post.substring(content_post.indexOf("https"),content_post.indexOf(".jpg")+4) + "\">";
                    }

                    post_name.setText(split[3]);
                    //文章圖片
                    MyImageGetter myImageGetter = new MyImageGetter();
                    post_content_tv.setText(Html.fromHtml(content_post, myImageGetter, null));

                    post_like_tv.setText(split[4].substring(0,1));
                    likenum=Integer.valueOf(split[4].substring(0,1));
                    Log.v("test123456", "LIKE" +split[4].substring(0,1)+"結束");
                    mThreadHandler.post(r7);

                }
            });
        }
    };
    private Runnable r3 = new Runnable() {
        @Override
        public void run() {
            //針對某篇文章進行收藏，WS改的是這篇文章發布者的收藏數
            webservice.Update_post_like(split[3],split[0],Integer.toString(likenum));
            Log.v("test123456", "aLL" +split[3]+split[0]+likenum);
            mUI_Handler.post(r4);
        }
    };
    private Runnable r4 = new Runnable() {
        @Override
        public void run() {
            Log.v("test123456", "aLL" +likenum);
            post_like_tv.setText(String.valueOf(likenum));

        }
    };
    private Runnable r5 = new Runnable() {
        @Override
        public void run() {
            webservice.Insert_keep_post(split[3],split[0],likeornot,gmail);
            Log.v("test","Insert like post: ("+split[3]+"  ,  "+split[0]+"  ,  "+likeornot+"  ,  "+gmail+")");
        }
    };
    private Runnable r7 = new Runnable() {
        @Override
        public void run() {
            //post_name、post_title、gmail進去WS查出這個使用者是否有收藏這篇文章
            likeornot=webservice.Select_keep_post(split[3],split[0],gmail);
            Log.v("test123456","(String post_name,String post_title,String gmail) : "+ split[3]+"   "+split[0]+"   "+gmail);
            Log.v("test123456", "QQQQ:::" +likeornot+gmail);
            user_like = webservice.Select_all_like_post(gmail);
            Log.v("test","user like : "+user_like);
            mUI_Handler.post(r8);
        }
    };
    private Runnable r8 = new Runnable() {
        @Override
        public void run() {
            //判斷是否收藏
            if(likeornot.substring(0,2).equals("on"))
            {fg=false;keep_post.setChecked(true);}
            else if(likeornot.equals("未查詢到資料"))
            {fg=true;keep_post.setChecked(false);}

            //判斷是否有按讚
            if(user_like.contains(split[5]))
            {
                post_like_fg = true;
                post_like.setChecked(true);

            }
            else
            {
                post_like_fg = false;
                post_like.setChecked(false);

            }
        }
    };
    private Runnable r9 = new Runnable() {
        @Override
        public void run() {
            webservice.Delete_keep_post(split[3],split[0],gmail);
        }
    };

    private Runnable user_like_post = new Runnable() {
        @Override
        public void run() {
            if(post_like_fg)
            {
                webservice.Add_user_like(Integer.valueOf(split[5]),gmail);
            }
            else {
                webservice.Delete_user_like(Integer.valueOf(split[5]),gmail);
            }
        }
    };



//    //查看ImageGetter接口
//    //public static interface ImageGetter {
//    //    public Drawable getDrawable(String source);
//    //}
//    //实现DrawableWrapper
    class MyDrawableWrapper extends BitmapDrawable {
        private Drawable drawable;

        MyDrawableWrapper() {
        }

        @Override
        public void draw(Canvas canvas) {
            if (drawable != null)
                drawable.draw(canvas);
        }

        public Drawable getDrawable() {
            return drawable;
        }

        public void setDrawable(Drawable drawable) {
            this.drawable = drawable;
        }
    }

    class MyImageGetter implements Html.ImageGetter {
        @Override
        public Drawable getDrawable(String source) {
            MyDrawableWrapper myDrawable = new MyDrawableWrapper();
            Drawable drawable = getResources().getDrawable(R.drawable.gender);
            drawable.setBounds(
                    0,
                    0,
                    drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight());
            myDrawable.setDrawable(drawable);
            Glide.with(forum_post2.this)
                    .asBitmap()
                    .load(source)
                    .into(new BitmapTarget(myDrawable));
            return myDrawable;
        }
    }

    class BitmapTarget extends SimpleTarget<Bitmap> {
        private final MyDrawableWrapper myDrawable;

        public BitmapTarget(MyDrawableWrapper myDrawable) {
            this.myDrawable = myDrawable;
        }

        @Override
        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
            Drawable drawable = new BitmapDrawable(getResources(), resource);
            //获取原图大小
            int width = drawable.getIntrinsicWidth();
            int height = drawable.getIntrinsicHeight();
            DisplayMetrics dm = new DisplayMetrics();
            //取得視窗屬性
            getWindowManager().getDefaultDisplay().getMetrics(dm);
            //視窗的寬度
            int screenWidth = dm.widthPixels;
            //視窗高度
            int screenHeight = dm.heightPixels;
            float x = (float) (screenWidth * 0.8) / width; //0.8倍的螢幕寬和圖片寬比
            //自定义drawable的高宽, 缩放图片大小最好用matrix变化，可以保证图片不失真

            drawable.setBounds(0, 0, (int) (screenWidth * 0.8), (int) (height * x));
            myDrawable.setBounds(0, 0, (int) (screenWidth * 0.8), (int) (height * x));
            myDrawable.setDrawable(drawable);
            post_content_tv.setText(post_content_tv.getText());
            post_content_tv.invalidate();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        //移除工人上的工作
        if (mThreadHandler != null) {
            mThreadHandler.removeCallbacks(r1);
            mThreadHandler.removeCallbacks(r3);
            mThreadHandler.removeCallbacks(r5);
            mThreadHandler.removeCallbacks(r7);
            mThreadHandler.removeCallbacks(r9);
        }
        //解聘工人 (關閉Thread)
        if (mThread != null) {
            mThread.quit();
        }
    }


    //留言的cardview
    private class CardAdapter extends RecyclerView.Adapter<forum_post2.CardAdapter.ViewHolder> {
        private Context context;
        public List<forum_post_message_cardview> cardviewList;

        CardAdapter(Context context, List<forum_post_message_cardview> cardviewList) {
            this.context = context;
            this.cardviewList = cardviewList;
        }

        @Override
        public forum_post2.CardAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.forum_post_message_cardview, viewGroup, false);
            return new forum_post2.CardAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(forum_post2.CardAdapter.ViewHolder viewHolder, int i) {
            final forum_post_message_cardview cardview = cardviewList.get(i);
            viewHolder.user_name.setText(String.valueOf(cardview.getName()));
            viewHolder.user_img.setImageResource(cardview.getImage());
            viewHolder.message_time.setText(String.valueOf(cardview.getTime()));
            viewHolder.message.setText(String.valueOf(cardview.getMessage()));
            viewHolder.B_num.setText(String.valueOf(cardview.getB_num()));
//            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    addItem(cardviewList.size());
//                    Intent intent = new Intent(record.this, record_Information2.class);
//                    startActivity(intent);
//                }
//            });
        }

        @Override
        public int getItemCount() {
            return cardviewList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView user_img;
            TextView user_name, message_time, B_num, message;

            ViewHolder(View itemView) {
                super(itemView);
                user_img = (ImageView) itemView.findViewById(R.id.user_img);
                user_name = (TextView) itemView.findViewById(R.id.user_name);
                message_time = (TextView) itemView.findViewById(R.id.message_time);
                B_num = (TextView) itemView.findViewById(R.id.B_num);
                message = (TextView) itemView.findViewById(R.id.message);
            }

            public void addItem(int i) {

//            num = cardviewList.size()-1;
//            //add(位置,資料)
//            cardviewList.add(i, new record_Cardview(id,"小白菜", R.drawable.icon201));
//            id=id+1;
//            notifyItemInserted(i);
            }
        }


    }
}
