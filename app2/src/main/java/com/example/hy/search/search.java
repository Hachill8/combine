package com.example.hy.search;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hy.GlobalVariable;
import com.example.hy.R;
import com.example.hy.home.home2;
import com.example.hy.webservice;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class search extends AppCompatActivity implements View.OnClickListener {
    ImageButton bt_filter;
    CardView vege_card_view1,vege_card_view2,vege_card_view3,vege_card_view4;
    TextView vege1_id,vege2_id,vege3_id,vege4_id;
    GlobalVariable vege_name,vege_item;
    String insert_vege_item="";
    String[] split_line={};
    AutoCompleteTextView searchview;
    View v;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.z_search);

        //篩選
        bt_filter = (ImageButton) findViewById(R.id.BT_filter);
        bt_filter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(search.this);
                View mView = getLayoutInflater().inflate(R.layout.z_search_filter_layout, null);
                //Dialog的標題
                mBuilder.setTitle("");

                final Spinner mSpinner1 = (Spinner) mView.findViewById(R.id.spinner1);
                Spinner mSpinner2 = (Spinner) mView.findViewById(R.id.spinner2);

                //給予對應item的資料
                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(search.this,
                        R.layout.record_select_dropdown_item,
                        getResources().getStringArray(R.array.月份));
                //自訂getDropDownView()介面格式(Spinner介面展開時，View所使用的每個item格式)
                adapter2.setDropDownViewResource(R.layout.record_select_dropdown_item);
                //匯入item資料
                mSpinner2.setAdapter(adapter2);

                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();

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
                p.height = (int) (d.getHeight() * 0.8); // 高度設為螢幕的0.6
                p.width = (int) (d.getWidth() * 0.75);  // 寬度設為螢幕的0.65
                dialogWindow.setAttributes(p);

            }
        });

        vege_card_view1=(CardView)findViewById( R.id.vege_card_view1 );
        vege_card_view2=(CardView)findViewById( R.id.vege_card_view2 );
        vege_card_view3=(CardView)findViewById( R.id.vege_card_view3 );
        vege_card_view4=(CardView)findViewById( R.id.vege_card_view4 );

        vege1_id=(TextView)findViewById(R.id.vege1_id);
        vege2_id=(TextView)findViewById(R.id.vege2_id);
        vege3_id=(TextView)findViewById(R.id.vege3_id);
        vege4_id=(TextView)findViewById(R.id.vege4_id);

        vege_card_view1.setOnClickListener(this);
        vege_card_view2.setOnClickListener(this);
        vege_card_view3.setOnClickListener(this);
        vege_card_view4.setOnClickListener(this);

        vege_name  = (GlobalVariable)getApplicationContext();
        vege_item  = (GlobalVariable)getApplicationContext();
        insert_vege_item = vege_item.getVege_item();


        searchview = (AutoCompleteTextView) findViewById(R.id.search_view);
        searchview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //點擊後抓searchview的文字並跳轉到作物資訊
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v("test","TextView:"+searchview.getText());
                vege_name.setWord(searchview.getText().toString());
                Intent x=new Intent(search.this,VegeInfo.class);
                startActivity(x);
            }
        });
        listview(v);
    }


    //searchview的列表
    public void listview(View view)
    {

        if (!insert_vege_item.equals("")) {
            split_line = insert_vege_item.split("%");
            for(int num = 0; num < split_line.length;num++)
            {
                for(int i = 0; i < split_line[num].length();i++)
                {
                    if(split_line[num].substring(i,i+1).equals(" "))
                    {
                        split_line[num]=split_line[num].substring(0,i);
                        Log.v("test","切割後的字串:"+split_line[num]);
                        break;
                    }
                }
            }


            Log.v("test","it3: "+split_line[0]+"1");
        }
        searchview.setAdapter(new ArrayAdapter<>(search.this,
                android.R.layout.simple_list_item_1, split_line));
    }

    @Override
    public void onClick(View v)
    {
        Intent i;
        switch (v.getId())
        {

            case R.id.vege_card_view1 :
                Log.v("test","1."+vege_name.getWord());
                i = new Intent( this,VegeInfo.class );
                vege_name.setWord("高麗菜");
                Log.v("test",vege_name.getWord());
                startActivity( i );
                break;
            case R.id.vege_card_view2 :
                i = new Intent( this,VegeInfo.class );
                vege_name.setWord("空心菜");
                startActivity( i );
                break;
            case R.id.vege_card_view3 :
                i = new Intent( this,VegeInfo.class );
                vege_name.setWord("秋葵");
                startActivity( i );
                break;
            case R.id.vege_card_view4 :
                i = new Intent( this,VegeInfo.class );
                vege_name.setWord("紅蘿蔔");
                startActivity( i );
                break;

            default:break;
        }
    }

}

