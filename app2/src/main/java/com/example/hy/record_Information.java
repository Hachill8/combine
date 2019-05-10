package com.example.hy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Paint;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



public class record_Information extends AppCompatActivity {
    int index = 0, ly=0,num=0,cy=570;
    boolean fg = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_information);

        List<record_Information_Cardview> cardviewList = new ArrayList<>();
        cardviewList.add(new record_Information_Cardview("幼苗期", "19/04/27~19/04/28", "澆水25次", "施肥1次", "間拔5次", R.drawable.imv03));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new CardAdapter(this, cardviewList));


//        final DrawView view = new DrawView(this);
//
//        view.setMinimumHeight(500);
//        view.setMinimumWidth(300);
//        //通知view組件重繪
//        view.invalidate();
//        RL.addView(view);
    }

//    public class DrawView extends View {
//
//        public DrawView(Context context) {
//            super(context);
//        }
//
//        @Override
//        protected void onDraw(Canvas canvas) {
//            super.onDraw(canvas);
//
//            Paint p = new Paint();
//            p.setColor(Color.LTGRAY);
//
//            p.setStyle(Paint.Style.STROKE);
//            p.setAntiAlias(true);
//            p.setStrokeWidth(5);
//            canvas.drawCircle(70, 60, 30, p);
//
//            if (fg == true) {
//
//                canvas.drawLine(70, ly, 70, ly + 450, p);
//                p.setAntiAlias(true);
//                p.setStyle(Paint.Style.STROKE);
//                p.setStrokeWidth(5);
//                canvas.drawCircle(70, cy, 30, p);
//            }
//            invalidate();
//        }
//    }

    public void draw(int i){
        ImageView imv1=new ImageView(this);
        ImageView imv2=new ImageView(this);
        ImageView imv3=new ImageView(this);
        // 取得 RelayiveLayout 物件
        RelativeLayout RL = (RelativeLayout) findViewById(R.id.canvas_draw);
        imv1.setImageResource(R.drawable.record_line1);
        imv2.setImageResource(R.drawable.record_circle);
        imv3.setImageResource(R.drawable.record_circle);
        imv3.setPadding(30,5,0,0);
        imv1.setPadding(43,100+ly,0,0);
        ly=ly+510;
        imv2.setPadding(30,ly+5,0,0);
        RL.addView(imv3);
        RL.addView(imv1);
        RL.addView(imv2);
    }


    private class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
        private Context context;
        public List<record_Information_Cardview> cardviewList;

        CardAdapter(Context context, List<record_Information_Cardview> cardviewList) {
            this.context = context;
            this.cardviewList = cardviewList;
        }

        @Override
        public CardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.record_information_cardview, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CardAdapter.ViewHolder viewHolder, int i) {
            final record_Information_Cardview cardview = cardviewList.get(i);
            viewHolder.periodId.setText(String.valueOf(cardview.getPeriod()));
            viewHolder.dateId.setText(String.valueOf(cardview.getDate()));
            viewHolder.tx1Id.setText(String.valueOf(cardview.getTx1()));
            viewHolder.tx2Id.setText(String.valueOf(cardview.getTx2()));
            viewHolder.tx3Id.setText(String.valueOf(cardview.getTx3()));
            viewHolder.plant_imvId.setImageResource(cardview.getImage());

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addItem(cardviewList.size());

//                    ImageView imageView = new ImageView(context);
//                    imageView.setImageResource(cardview.getImage());
//                    Toast toast = new Toast(context);
//                    toast.setView(imageView);
//                    toast.setDuration(Toast.LENGTH_SHORT);
//                    toast.show();
                }
            });
        }


        @Override
        public int getItemCount() {
            return cardviewList.size();
        }

        //Adapter 需要一個 ViewHolder，只要實作它的 constructor 就好，保存起來的view會放在itemView裡面
        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView plant_imvId;
            TextView periodId, dateId, tx1Id, tx2Id, tx3Id;

            ViewHolder(View itemView) {
                super(itemView);
                plant_imvId = (ImageView) itemView.findViewById(R.id.plant_imvId);
                periodId = (TextView) itemView.findViewById(R.id.periodId);
                dateId = (TextView) itemView.findViewById(R.id.dateId);
                tx1Id = (TextView) itemView.findViewById(R.id.tx1Id);
                tx2Id = (TextView) itemView.findViewById(R.id.tx2Id);
                tx3Id = (TextView) itemView.findViewById(R.id.tx3Id);
            }
        }

        public void addItem(int i) {

//            if(fg == false)
//            {
//                cy = cy + 480;
//                ly = ly + 480;
//            }
            draw(cardviewList.size());
            fg = true;
            num = cardviewList.size()-1;

            //add(位置,資料)
            cardviewList.add(cardviewList.size(), new record_Information_Cardview("幼苗期", "19/04/27~19/04/28", "澆水2次", "施肥1次", "間拔5次", R.drawable.imv03));
            notifyItemInserted(cardviewList.size());
        }
    }
}







//    private void createView()
//    {
//        fg=false;
//        ImageButton b1 = new ImageButton(this);
//        TextView period = new TextView(this);
//        TextView date = new TextView(this);
//        ImageView img = new ImageView(this);
//        TextView tx1 = new TextView(this);
//        TextView tx2 = new TextView(this);
//        TextView tx3 = new TextView(this);
//
//
//        b1.setBackgroundResource(R.drawable.record_back);
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                index = index +200;
//                Toast toast = Toast.makeText(record_Information.this,"index: "+index,Toast.LENGTH_LONG);
//                fg=true;
//                toast.show();
//            }
//        });
//        plantinfo(b1,120,260,0,0);
//        period.setText("幼苗期");
//        period.setTextSize(20);
//        period.setBackgroundResource(R.drawable.period);
//
////        period.setOutlineProvider(new ViewOutlineProvider() {
////            @Override
////            public void getOutline(View view, Outline outline) {
////                outline.setRoundRect(10,10,10,10,10);
////            }
////        });
//
//        plantinfo(period,160,300,0,0);
//
//
//        date.setText("19/04/20 ~ 19/04/24");
//        date.setTextSize(20);
//        date.setBackgroundResource(R.drawable.period);
//        plantinfo(date,370,300,0,0);
//
//
//        img.setImageResource(R.drawable.imv03);
//        plantinfo(img,190,400,0,0);
//
//
//        tx1.setText("澆水25次");
//        tx1.setWidth(300);
//        tx1.setHeight(60);
//        tx1.setTextSize(15);
//        tx1.setTextColor(getResources().getColor(R.color.white));
//        tx1.setBackgroundResource(R.drawable.record_textview);
//        tx1.setPadding(3,0,0,0);            //疑似沒作用
//        plantinfo(tx1,620,430,0,0);
//
//
//        tx2.setText("施肥6次");
//        tx2.setWidth(300);
//        tx2.setHeight(60);
//        tx2.setTextSize(15);
//        tx2.setTextColor(getResources().getColor(R.color.white));
//        tx2.setBackgroundResource(R.drawable.record_textview);
//        tx2.setPadding(3,0,0,0);
//        plantinfo(tx2,620,500,0,0);
//
//
//        tx3.setText("間拔2次");
//        tx3.setWidth(300);
//        tx3.setHeight(60);
//        tx3.setTextSize(15);
//        tx3.setTextColor(getResources().getColor(R.color.white));
//        tx3.setBackgroundResource(R.drawable.record_textview);
//        tx3.setPadding(3,0,0,0);
//        plantinfo(tx3,620,570,0,0);
//
//
//
//        RL.addView(b1);
//        RL.addView(period);
//        RL.addView(date);
//        RL.addView(img);
//        RL.addView(tx1);
//        RL.addView(tx2);
//        RL.addView(tx3);
//    }
//
//    private void plantinfo(View view,int left,int top,int right,int bottom)
//    {
//        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
//        layoutParams.setMargins(left+index,top+index,right,bottom);
//
//        Toast toast = Toast.makeText(record_Information.this,"index2: "+index,Toast.LENGTH_LONG);
//
//        toast.show();
//        view.setLayoutParams(layoutParams);
//    }