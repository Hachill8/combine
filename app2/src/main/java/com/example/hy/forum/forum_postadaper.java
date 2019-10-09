package com.example.hy.forum;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hy.R;

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
        View view=inflater.inflate(R.layout.forum_list_layout,null);
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
        holder.imageView.setImageDrawable(mctx.getResources().getDrawable(post.getImage()));
        holder.userimg.setImageDrawable(mctx.getResources().getDrawable(post.getUserimg()));
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }



    class postviewholder extends RecyclerView.ViewHolder {

        ImageView imageView,userimg;
        TextView textViewtitle,textViewdesc,time,heartnum,commentnum;


        public postviewholder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.plant);
            textViewtitle=itemView.findViewById(R.id.user_name);
            textViewdesc=itemView.findViewById(R.id.post);
            userimg=itemView.findViewById((R.id.user_img));
            time=itemView.findViewById(R.id.post_time);
            heartnum=itemView.findViewById(R.id.post_heart_num);
            commentnum=itemView.findViewById(R.id.post_chat_num);
        }


    }




}
