package com.example.hy.market;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hy.R;
import com.example.hy.record.record;
import com.example.hy.record.record_Cardview;
import com.example.hy.record.record_Information2;

import java.util.ArrayList;
import java.util.List;

public class market_p4 extends AppCompatActivity
{
    Spinner deliver_spinner,pay_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_market4);

        //spinner建立

        deliver_spinner = (Spinner) findViewById(R.id.shopping_cart_deliver_spinner);
        pay_spinner = (Spinner) findViewById(R.id.shopping_cart_pay_spinner);

        //給予對應item的資料
        ArrayAdapter <String> adapter1 = new ArrayAdapter<String>(market_p4.this,
                R.layout.market4_select_dropdown_item,                            //選項資料內容
                getResources().getStringArray(R.array.運送方式));   //自訂getView()介面格式(Spinner介面未展開時的View)
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(market_p4.this,
                R.layout.market4_select_dropdown_item,
                getResources().getStringArray(R.array.付款方式));

        //匯入item資料
        deliver_spinner.setAdapter(adapter1);
        pay_spinner.setAdapter(adapter2);


//        //cardview 建立
//
//        List <record_Cardview> cardviewList = new ArrayList <>();
//        cardviewList.add(new record_Cardview(0,"紅蘿蔔 19/08/06",R.drawable.carrot));
//        cardviewList.add(new record_Cardview(1,"空心菜 19/08/06",R.drawable.record_vege_4));
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.market4_shopping_cart_recyclerView);
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
//        recyclerView.setAdapter(new record.CardAdapter(this, cardviewList));

    }

//    private class CardAdapter extends  RecyclerView.Adapter<record.CardAdapter.ViewHolder>
//    {
//        private Context context;
//        public List<record_Cardview> cardviewList;
//
//        CardAdapter(Context context, List<record_Cardview> cardviewList) {
//            this.context = context;
//            this.cardviewList = cardviewList;
//        }
//
//        @Override
//        public record.CardAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//            View view = LayoutInflater.from(context).inflate(R.layout.record_cardview,viewGroup,false);
//            return new record.CardAdapter.ViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(record.CardAdapter.ViewHolder viewHolder, int i)
//        {
//            final record_Cardview cardview = cardviewList.get(i);
//            viewHolder.tx1.setText(String.valueOf(cardview.getName()));
//            viewHolder.plantId.setImageResource(cardview.getImage());
//            viewHolder.tx2.setText("19/08/06");
//
//            viewHolder.itemView.setOnClickListener(new View.OnClickListener()
//            {
//                @Override
//                public void onClick(View v)
//                {
//                    addItem(cardviewList.size());
//                    Intent intent = new Intent(market_p4.this, market2.class);
//                    startActivity(intent);
//                }
//            });
//        }
//
//        @Override
//        public int getItemCount() {
//            return cardviewList.size();
//        }
//
//        class ViewHolder extends RecyclerView.ViewHolder{
//            ImageView plantId;
//            TextView tx1,tx2;
//
//            ViewHolder(View itemView){
//                super(itemView);
//                plantId = (ImageView) itemView.findViewById(R.id.plantId);
//                tx1 = (TextView) itemView.findViewById(R.id.tx1);
//                tx2 = (TextView) itemView.findViewById(R.id.tx2);
//            }
//        }
//        public  void addItem(int i){
//            fg = true;
////            num = cardviewList.size()-1;
////            //add(位置,資料)
////            cardviewList.add(i, new record_Cardview(id,"小白菜", R.drawable.icon201));
////            id=id+1;
////            notifyItemInserted(i);
//        }
//    }



}
