package com.example.hy.calendar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hy.AutoSplitTextView;
import com.example.hy.R;

import java.util.ArrayList;
import java.util.List;

public class choose_calendar_info extends AppCompatActivity {

    List<choose_calendar_cardview> cardviewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_choose_calendar_info );

        cardviewList = new ArrayList<>();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.vege_info);

        RecyclerView recyclerView = findViewById(R.id.choose_calendar_info_recyclerview);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        cardviewList.add(new choose_calendar_cardview(0,"1","2019-12-11 02:05","青菜底家啦!AAAAAAAAAAAAAAAAAAAAAAAAAAAAA",bitmap));
        cardviewList.add(new choose_calendar_cardview(0,"2","2019-12-11 02:05","青菜底家啦!",bitmap));
        cardviewList.add(new choose_calendar_cardview(0,"3","2019-12-11 02:05","青菜底家啦!",bitmap));
        cardviewList.add(new choose_calendar_cardview(0,"4","2019-12-11 02:05","青菜底家啦!",bitmap));
        cardviewList.add(new choose_calendar_cardview(0,"5","2019-12-11 02:05","青菜底家啦!",bitmap));
        cardviewList.add(new choose_calendar_cardview(0,"6","2019-12-11 02:05","青菜底家啦!",bitmap));
        cardviewList.add(new choose_calendar_cardview(0,"7","2019-12-11 02:05","青菜底家啦!",bitmap));
        recyclerView.setAdapter(new choose_calendar_info.CardAdapter(choose_calendar_info.this, cardviewList));
    }


    private class CardAdapter extends  RecyclerView.Adapter<CardAdapter.ViewHolder>
    {
        private Context context;
        public List<choose_calendar_cardview> cardviewList;

        CardAdapter(Context context, List<choose_calendar_cardview> cardviewList) {
            this.context = context;
            this.cardviewList = cardviewList;
        }

        @Override
        public CardAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.activity_choose_calendar_cardview,viewGroup,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CardAdapter.ViewHolder viewHolder, int i) {
            final choose_calendar_cardview cardview = cardviewList.get(i);
            viewHolder.day.setText(String.valueOf(cardview.getDay()));
            viewHolder.img.setImageBitmap(cardview.getImage());
            viewHolder.time.setText(String.valueOf((cardview.getTime())));
            viewHolder.message.setText(String.valueOf((cardview.getMessage())));
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
            ImageView img;
            TextView day,time;
            AutoSplitTextView message;

            ViewHolder(View itemView){
                super(itemView);
                img =  itemView.findViewById(R.id.choose_calendar_img);
                day =  itemView.findViewById(R.id.choose_calendar_day);
                time =  itemView.findViewById(R.id.choose_calendar_time);
                message =  itemView.findViewById(R.id.choose_calendar_message);
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
}