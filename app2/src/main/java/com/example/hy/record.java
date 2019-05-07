package com.example.hy;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class record extends AppCompatActivity {
    boolean fg = false;
    int num = 0,id=2;
    Button showDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        //cardview 建立

        List<record_Cardview> cardviewList = new ArrayList<>();
        cardviewList.add(new record_Cardview(0,"高麗菜",R.drawable.example_2));
        cardviewList.add(new record_Cardview(1,"高麗菜",R.drawable.example_2));
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(new record.CardAdapter(this, cardviewList));


        //Dialog 建立
        showDialog = (Button) findViewById(R.id.Select_item);

        showDialog.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(record.this);
                View mView = getLayoutInflater().inflate(R.layout.record_select, null);
                //Dialog的標題
                mBuilder.setTitle("");

                final Spinner mSpinner1 = (Spinner) mView.findViewById(R.id.spinner1);
                Spinner mSpinner2 = (Spinner) mView.findViewById(R.id.spinner2);

                //給予對應item的資料
                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(record.this,
                        R.layout.record_select_dropdown_item,                            //選項資料內容
                        getResources().getStringArray(R.array.農地編號));   //自訂getView()介面格式(Spinner介面未展開時的View)
                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(record.this,
                        R.layout.record_select_dropdown_item,
                        getResources().getStringArray(R.array.月份));

                //自訂getDropDownView()介面格式(Spinner介面展開時，View所使用的每個item格式)
                adapter1.setDropDownViewResource(R.layout.record_select_dropdown_item);
                adapter2.setDropDownViewResource(R.layout.record_select_dropdown_item);

                //匯入item資料
                mSpinner1.setAdapter(adapter1);
                mSpinner2.setAdapter(adapter2);

                //內建可用Button的方式
//                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener()
//                {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i)
//                    {
//
//                        //除了選擇第一項外皆符合
//                        if(!mSpinner1.getSelectedItem().toString().equalsIgnoreCase("選擇農地編號…"))
//                        {
//                            Toast.makeText(record.this,
//                                    mSpinner1.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
//                            dialogInterface.dismiss();
//                        }
//                    }
//                });
//
//                //內建可用Button的方式
//                mBuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener()
//                {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i)
//                    {
//                        dialogInterface.dismiss();
//                    }
//                });

                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();

                // 調整dialog大小的位置的方法(絕對值)
//                Window dialogWindow = dialog.getWindow();
//                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//                dialogWindow.setGravity(Gravity.RIGHT | Gravity.TOP);
//                lp.x = 100; // 新位置X
//                lp.y = 100; // 新位置Y
//                lp.width = 300; // 宽度
//                lp.height = 300; // 高度
//                lp.alpha = 0.7f; // 透明度
//                dialogWindow.setAttributes(lp);

                dialog.show();

                // 調整Dialog從哪開始
                Window dialogWindow = dialog.getWindow();
                dialogWindow.setGravity(Gravity.RIGHT | Gravity.TOP);

                // 去除四角黑色背景
                dialogWindow.setBackgroundDrawable(new BitmapDrawable());

                /* 將Dialog用螢幕大小百分比方式設置 */
                WindowManager m = getWindowManager();
                Display d = m.getDefaultDisplay(); // 取得螢幕寬和高
                WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 取得對話框目前數值
                p.height = (int) (d.getHeight() * 0.8); // 高度設為螢幕的0.8
                p.width = (int) (d.getWidth() * 0.75);  // 寬度設為螢幕的0.75
                dialogWindow.setAttributes(p);

            }
        });
    }

    private class CardAdapter extends  RecyclerView.Adapter<CardAdapter.ViewHolder>{
        private Context context;
        public List<record_Cardview> cardviewList;

        CardAdapter(Context context, List<record_Cardview> cardviewList) {
            this.context = context;
            this.cardviewList = cardviewList;
        }

        @Override
        public CardAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.record_cardview,viewGroup,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CardAdapter.ViewHolder viewHolder, int i) {
            final record_Cardview cardview = cardviewList.get(i);
            viewHolder.tx1.setText(String.valueOf(cardview.getName()));
            viewHolder.plantId.setImageResource(cardview.getImage());

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addItem(cardviewList.size());
                    Intent intent = new Intent(record.this, record_Information.class);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return cardviewList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
                    ImageView plantId;
                    TextView tx1;

                    ViewHolder(View itemView){
                        super(itemView);
                        plantId = (ImageView) itemView.findViewById(R.id.plantId);
                        tx1 = (TextView) itemView.findViewById(R.id.tx1);
                    }
        }
        public  void addItem(int i){
            fg = true;
            num = cardviewList.size()-1;
            //add(位置,資料)
            cardviewList.add(i, new record_Cardview(id,"小白菜", R.drawable.icon201));
            id=id+1;
            notifyItemInserted(i);
        }
    }
}
