package com.example.hy;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hy.login.login2;
import com.example.hy.search.search;
import com.example.hy.user_setting.like_post;
import com.example.hy.user_setting.user_setting;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import cz.msebera.android.httpclient.Header;

public class custom_vegeinfo extends AppCompatActivity {

    //找到UI工人的經紀人，這樣才能派遣工作  (找到顯示畫面的UI Thread上的Handler)
    private Handler mUI_Handler = new Handler();
    //宣告特約工人的經紀人
    private Handler mThreadHandler;
    //宣告特約工人
    private HandlerThread mThread;
    private final int REQUEST_PICK_IMAGE = 1;
    DownloadImageTask downloadImageTask;
    ProgressDialog mLoadingDialog;
    ImageView madd_img;
    ImageButton back_user;
    EditText mvege_name,medit_step,medit_container,medit_soil,medit_place,medit_water,medit_fertilizer,
            medit_bug,medit_harvest;
    String add_img,vege_name,edit_step,edit_container,edit_soil,edit_place,edit_water,edit_fertilizer,edit_bug,edit_harvest,gmail,url_String="";
    URL url;
    Bitmap myBitmap;
    Button confirm,cancel;
    Intent intent;
    GlobalVariable gl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_custom_vegeinfo);

        //聘請一個特約工人，有其經紀人派遣其工人做事 (另起一個有Handler的Thread)
        mThread = new HandlerThread("");
        //讓Worker待命，等待其工作 (開啟Thread)
        mThread.start();
        //找到特約工人的經紀人，這樣才能派遣工作 (找到Thread上的Handler)
        mThreadHandler=new Handler(mThread.getLooper());

        madd_img=findViewById(R.id.add_img);
        mLoadingDialog = new ProgressDialog(custom_vegeinfo.this);
        downloadImageTask = new DownloadImageTask(madd_img);
        mvege_name=findViewById(R.id.vege_name);
        medit_step=findViewById(R.id.edit_step);
        medit_container=findViewById(R.id.edit_container);
        medit_soil=findViewById(R.id.edit_soil);
        medit_place=findViewById(R.id.edit_place);
        medit_water=findViewById(R.id.edit_water);
        medit_fertilizer=findViewById(R.id.edit_fertilizer);
        medit_bug=findViewById(R.id.edit_bug);
        medit_harvest=findViewById(R.id.edit_harvest);
        confirm= findViewById(R.id.confirm);
        cancel=findViewById(R.id.cancel);
        back_user=findViewById(R.id.back_user);
        gl = (GlobalVariable)getApplicationContext();
        gmail=gl.getUser_gmail();

        back_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(custom_vegeinfo.this, user_setting.class);
                startActivity(intent);
                custom_vegeinfo.this.finish();
            }
        });
        madd_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertImage();

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(custom_vegeinfo.this, search.class);
                startActivity(intent);
                custom_vegeinfo.this.finish();

            }
        });

        vege_name=mvege_name.getText().toString();
        edit_step=medit_step.getText().toString();
        edit_container=medit_container.getText().toString();
        edit_soil=medit_soil.getText().toString();
        edit_place=medit_place.getText().toString();
        edit_water=medit_water.getText().toString();
        edit_fertilizer=medit_fertilizer.getText().toString();
        edit_bug=medit_bug.getText().toString();
        edit_harvest=medit_harvest.getText().toString();

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent =new Intent(custom_vegeinfo.this, search.class);
                if(mvege_name.getText().toString().equals("") || medit_step.getText().toString().equals("")||medit_container.getText().toString().equals("")||
                        medit_water.getText().toString().equals("")||medit_harvest.getText().toString().equals(""))
                {
                    Toast.makeText(custom_vegeinfo.this,"資料輸入未完整 !",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    mThreadHandler.post(r1);
                }

            }
        });


    }

    private Runnable r1=new Runnable () {

        public void run() {

            webservice.Insert_custom_vege("1",url_String,mvege_name.getText().toString(),medit_step.getText().toString(),medit_container.getText().toString(),
                    medit_soil.getText().toString(),medit_place.getText().toString(),medit_water.getText().toString(),medit_fertilizer.getText().toString(),medit_bug.getText().toString(),medit_harvest.getText().toString(),gmail);
            Log.v("test","JJJJJJJJJ: "+url_String+"/"+medit_step.getText().toString()+"/"+medit_container.getText().toString());
            mUI_Handler.post(r2);
        }

    };

    private Runnable r2=new Runnable () {

        public void run() {

            startActivity(intent);
            custom_vegeinfo.this.finish();
        }

    };

    private Runnable r3=new Runnable () {

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
            mUI_Handler.post(r4);

        }

    };

    private Runnable r4=new Runnable () {

        public void run() {

            madd_img.setImageBitmap(myBitmap);
            dismissLoadingDialog();
        }

    };



    private void InsertImage(){
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
        showLoadingDialog("載入中...");
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

    private void imgurUpload(final String image){ //插入圖片
        String urlString = "https://imgur-apiv3.p.mashape.com/3/image/";
        String mashapeKey = "c36b64ce86msh2cf38451f64a923p16a49bjsn3f0ccc47d896"; //設定自己的 Mashape Key
        String clientId = "ca76d0896a29008"; //設定自己的 Clinet ID
        String titleString = ""; //設定圖片的標題

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
                if (!response.optBoolean("success") || !response.has("data")) {
                    Log.d("editor", "response: "+response.toString());
                    return;
                }
                JSONObject data = response.optJSONObject("data");
                //Log.d("editor","link: "+data.optString("link"));
                url_String = data.optString("link","");
                int width = data.optInt("width",0);
                int height = data.optInt("height",0);

                Log.v("test","link: "+url_String);
                try {
                    url = new URL(url_String);
                    mThreadHandler.post(r3);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject error) {
                //Log.d("editor","error: "+error.toString());
                if (error.has("data")) {
                    JSONObject data = error.optJSONObject("data");
                    android.support.v7.app.AlertDialog dialog = new android.support.v7.app.AlertDialog.Builder(custom_vegeinfo.this)
                            .setTitle("Error: " + statusCode + " " + e.getMessage())
                            .setMessage(data.optString("error",""))
                            .setPositiveButton("確定", null)
                            .create();
                    dialog.show();
                }
            }
        });
    }



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
