package com.example.hy;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.app.AlertDialog.Builder;
import android.support.v7.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

public class search extends AppCompatActivity {
    SearchView mSearchView;
    Button btnextPageBtn1, btnextPageBtn2, bt_filter;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_search);

        //篩選
        bt_filter = (Button) findViewById(R.id.BT_filter);
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
                p.height = (int) (d.getHeight() * 0.8); // 高度設為螢幕的0.6
                p.width = (int) (d.getWidth() * 0.75);  // 寬度設為螢幕的0.65
                dialogWindow.setAttributes(p);

            }
        });

        //
        btnextPageBtn1 = (Button) findViewById(R.id.BT_nextPageBtn1);
        btnextPageBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(search.this, crop_info.class);
                startActivity(intent);
            }
        });

        btnextPageBtn2 = (Button) findViewById(R.id.BT_nextPageBtn2);
        btnextPageBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(search.this, crop_info.class);
                startActivity(intent);
            }
        });

        mSearchView = (SearchView) findViewById(R.id.search_view);
        ImageView searchIcon = (ImageView) mSearchView.findViewById(android.support.v7.appcompat.R.id.search_mag_icon);
        searchIcon.setImageDrawable(null);
        mSearchView.setIconifiedByDefault(false);
    }

    public AlertDialog getMutiItemDialog(final String[] items) {
        Builder builder = new Builder(this);
        //設定對話框內的項目
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //當使用者點選對話框時，顯示使用者所點選的項目
                Toast.makeText(search.this, "您選擇的是" + items[which], Toast.LENGTH_SHORT).show();
            }
        });
        return builder.create();
    }

}

