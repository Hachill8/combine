package com.example.hy;


import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.Spinner;
import android.widget.Toast;

public class record extends AppCompatActivity {

    Button showDialog,BT1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        BT1 = (Button) findViewById(R.id.bt1);
        BT1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(record.this,record_Information.class);
                startActivity(intent);
            }
        });
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
}
