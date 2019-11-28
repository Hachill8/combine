package com.example.hy.forum;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.hy.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class forum_postadaper extends RecyclerView.Adapter<forum_postadaper.postviewholder>  {

    private Context mctx;
    private List<forum_post> postList;

    String s="";
    public forum_postadaper(Context mctx, List<forum_post> postList) {
        this.mctx = mctx;
        this.postList = postList;
    }

    @Override
    public postviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(mctx);
        View view=inflater.inflate(R.layout.forum_list_layout,viewGroup,false);
        return new postviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull postviewholder holder, int position) {
        final forum_post post=postList.get(position);

        holder.textViewtitle.setText(post.getTitle());
        holder.textViewdesc.setText(post.getShortdesc());
        holder.time.setText(post.getTime());
        holder.commentnum.setText(post.getCommentnumnum());
        holder.heartnum.setText(post.getHeartnum());
        holder.userimg.setImageDrawable(mctx.getResources().getDrawable(post.getUserimg()));
        holder.downloadImageTask.execute(post.getImage_string());

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }



    class postviewholder extends RecyclerView.ViewHolder {

        ImageView userimg;
        TextView textViewtitle,textViewdesc,time,heartnum,commentnum;
        DownloadImageTask downloadImageTask ;

        public postviewholder(@NonNull View itemView) {
            super(itemView);


            textViewtitle=itemView.findViewById(R.id.user_name);
            textViewdesc=itemView.findViewById(R.id.post);
            userimg=itemView.findViewById((R.id.user_img));
            time=itemView.findViewById(R.id.post_time);
            heartnum=itemView.findViewById(R.id.post_heart_num);
            commentnum=itemView.findViewById(R.id.post_chat_num);
            downloadImageTask = new DownloadImageTask((ImageView)itemView.findViewById(R.id.plant));

        }


    }

}
class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.v("test", "forum圖片錯誤: "+e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}