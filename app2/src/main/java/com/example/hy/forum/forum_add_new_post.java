package com.example.hy.forum;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.example.hy.GlobalVariable;
import com.example.hy.R;
import com.example.hy.webservice;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cz.msebera.android.httpclient.Header;

public class forum_add_new_post extends AppCompatActivity
{
    EditText post_title,post_content;
    private final int REQUEST_PICK_IMAGE = 1;
    Context mcontext;
    ProgressDialog mLoadingDialog;
    ImageButton upload_img,add_new;
    GlobalVariable gl;

    //找到UI工人的經紀人，這樣才能派遣工作  (找到顯示畫面的UI Thread上的Handler)
    private Handler mUI_Handler = new Handler();
    //宣告特約工人的經紀人
    private Handler mThreadHandler;
    //宣告特約工人
    private HandlerThread mThread;
    String line;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate( savedInstanceState );
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView( R.layout.forum_add_new_post );

        post_title = (EditText) findViewById(R.id.post_title);
        post_content = (EditText) findViewById(R.id.post_content);
        mLoadingDialog = new ProgressDialog(forum_add_new_post.this);
        upload_img = (ImageButton) findViewById(R.id.upload_img);
        ImageButton back_to_forum = findViewById(R.id.back_to_forum);
        back_to_forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent i = new Intent(forum_add_new_post.this, forum.class);
                startActivity(i);
                forum_add_new_post.this.finish();
                //System.exit(0);
            }
        });

        gl = (GlobalVariable)getApplicationContext();

        //聘請一個特約工人，有其經紀人派遣其工人做事 (另起一個有Handler的Thread)
        mThread = new HandlerThread("");
        //讓Worker待命，等待其工作 (開啟Thread)
        mThread.start();
        //找到特約工人的經紀人，這樣才能派遣工作 (找到Thread上的Handler)
        mThreadHandler=new Handler(mThread.getLooper());

        upload_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textInsertImage();
            }
        });

        add_new = (ImageButton) findViewById(R.id.add_new);
        add_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //請經紀人指派工作名稱 r，給工人做
                mThreadHandler.post(r1);
            }
        });

    }

    //工作名稱 r1 的工作內容

    private Runnable r1=new Runnable () {

        public void run() {

            if(!post_content.equals("無") && !post_title.equals("")) {
                // string user, string post_title, string post_content,string post_time
                line = webservice.Add_post_test(post_title.getText().toString(), post_content.getText().toString());
            }
            //請經紀人指派工作名稱 r，給工人做
            mUI_Handler.post(r2);
        }

    };

    //工作名稱 r2 的工作內容

    private Runnable r2=new Runnable () {

        public void run() {

            Intent a = new Intent(forum_add_new_post.this,forum_post2.class);
            gl.setForum_content(post_content.getText().toString());
            gl.setForum_title(post_title.getText().toString());
            if(post_title.getText().toString().equals("") || post_content.getText().toString().equals(""))
            {
                Toast.makeText(forum_add_new_post.this,"請輸入標題和內容 !",Toast.LENGTH_SHORT).show();
            }
            else
            {
                startActivity(a);
                //關閉activity
                forum_add_new_post.this.finish();
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

    private void textInsertImage(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) { return; }

        switch(requestCode){
            case REQUEST_PICK_IMAGE:
                getSelectImage(data); //處理選取圖片的程式 寫在後面
                break;
        }
    }



    //選擇了要插入的圖片後，在onActivityResult會執行這個
    private void getSelectImage(Intent data){

        //從 onActivityResult 傳入的data中，取得圖檔路徑
        Uri selectedImage = null;
        selectedImage = data.getData();

        ContentResolver cr = this.getContentResolver(); //抽象資料的接口
        Bitmap bitmap = null;
        try {
            assert selectedImage != null;
            bitmap = BitmapFactory.decodeStream(cr.openInputStream(selectedImage));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



//        Log.v("test","imagePath: "+imagePath);
//        //使用圖檔路徑產生調整過大小的Bitmap
//        Bitmap bitmap = getResizedBitmap(imagePath); //程式寫在後面

        //將 Bitmap 轉為 base64 字串
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        byte[] bitmapData = bos.toByteArray();
        String imageBase64 = Base64.encodeToString(bitmapData, Base64.DEFAULT);
        //Log.d("editor",imageBase64);

        //將圖檔上傳至 Imgur，將取得的圖檔網址插入文字輸入框
        imgurUpload(imageBase64); //程式寫在後面
    }






    //    當圖檔非常大時 (寬度>MAX_WIDTH*2)
    //    先用方法一產生不那麼大的檔 (寬度<=MAX_WIDTH*2)
    //    再用方法二縮至指定的大小 (寬度<=MAX_WIDTH)

    private Bitmap getResizedBitmap(String imagePath) {
        final int MAX_WIDTH = 1024; // 新圖的寬要小於等於這個值

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; //只讀取寬度和高度
        BitmapFactory.decodeFile(imagePath, options);
        int width = options.outWidth, height = options.outHeight;
        Log.v("test","照片的 Width: "+width+"    Height: "+height);
        // 求出要縮小的 scale 值，必需是2的次方，ex: 1,2,4,8,16...
        int scale = 1;
        while(width > MAX_WIDTH*2){
            width /= 2;
            height /= 2;
            scale *= 2;
        }

        // 使用 scale 值產生縮小的圖檔
        BitmapFactory.Options scaledOptions = new BitmapFactory.Options();
        scaledOptions.inSampleSize = scale;
        Bitmap scaledBitmap = BitmapFactory.decodeFile(imagePath, scaledOptions);

        float resize = 1; //縮小值 resize 可為任意小數
        if(width>MAX_WIDTH){
            resize = ((float) MAX_WIDTH) / width;
        }

        Matrix matrix = new Matrix(); // 產生縮圖需要的參數 matrix
        matrix.postScale(resize, resize); // 設定寬與高的縮放比例

        // 產生縮小後的圖
        return Bitmap.createBitmap(scaledBitmap, 0, 0, width, height, matrix, true);
    }



    private void imgurUpload(final String image){ //插入圖片
        String urlString = "https://imgur-apiv3.p.mashape.com/3/image/";
        String mashapeKey = "c36b64ce86msh2cf38451f64a923p16a49bjsn3f0ccc47d896"; //設定自己的 Mashape Key
        String clientId = "ca76d0896a29008"; //設定自己的 Clinet ID
        String titleString = ""; //設定圖片的標題
        showLoadingDialog("上傳中...");

        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("X-Mashape-Key", mashapeKey);
        client.addHeader("Authorization", "Client-ID "+clientId);
        client.addHeader("Content-Type", "application/x-www-form-urlencoded");
        RequestParams params = new RequestParams();
        params.put("title", titleString);
        params.put("image", image);
        client.post(urlString, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                dismissLoadingDialog();
                if (!response.optBoolean("success") || !response.has("data")) {
                    Log.d("editor", "response: "+response.toString());
                    return;
                }
                JSONObject data = response.optJSONObject("data");
                //Log.d("editor","link: "+data.optString("link"));
                String link = data.optString("link","");
                int width = data.optInt("width",0);
                int height = data.optInt("height",0);
                //String bbcode = "[img="+width+"x"+height+"]"+link+"[/img]";
                //String bbcode ="["+link+"]";
                String bbcode ="<img src=\"" + link + "\">";
                textInsertString(bbcode); //將文字插入輸入框的程式 寫在後面
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject error) {
                dismissLoadingDialog();
                //Log.d("editor","error: "+error.toString());
                if (error.has("data")) {
                    JSONObject data = error.optJSONObject("data");
                    AlertDialog dialog = new AlertDialog.Builder(mcontext)
                            .setTitle("Error: " + statusCode + " " + e.getMessage())
                            .setMessage(data.optString("error",""))
                            .setPositiveButton("確定", null)
                            .create();
                    dialog.show();
                }
            }
        });
    }







    @SuppressLint("SetTextI18n")
    private void textInsertString(String insertString){
        post_content.setText(post_content.getText()+"\n"+insertString+"\n");


        Log.v("test","post_content.getText 1:"+"\n"+"\n"+post_content.getText()+"\n");


    }




    private void showLoadingDialog(String message){
        if(message==null){
            message = "載入中...";
        }
        if(mLoadingDialog==null){
            mLoadingDialog = new ProgressDialog(this);
            mLoadingDialog.setMessage(message);
        }
        mLoadingDialog.show();
    }

    private void dismissLoadingDialog(){
        if(mLoadingDialog!=null) {
            mLoadingDialog.dismiss();
        }
    }




}
