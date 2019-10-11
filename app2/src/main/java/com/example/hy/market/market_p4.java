package com.example.hy.market;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.hy.R;
import com.example.hy.record.record;

public class market_p4 extends AppCompatActivity
{
    Spinner deliver_spinner,pay_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_market4);


        deliver_spinner = (Spinner) findViewById(R.id.shopping_cart_deliver_spinner);
        pay_spinner = (Spinner) findViewById(R.id.shopping_cart_pay_spinner);

        //給予對應item的資料
        ArrayAdapter <String> adapter1 = new ArrayAdapter<String>(market_p4.this,
                R.layout.market4_select_dropdown_item,                            //選項資料內容
                getResources().getStringArray(R.array.運送方式));   //自訂getView()介面格式(Spinner介面未展開時的View)
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(market_p4.this,
                R.layout.market4_select_dropdown_item,
                getResources().getStringArray(R.array.付款方式));

        //自訂getDropDownView()介面格式(Spinner介面展開時，View所使用的每個item格式)
//        adapter1.setDropDownViewResource(R.layout.login2_select_dropdown_item);
//        adapter2.setDropDownViewResource(R.layout.login2_select_dropdown_item);

        //匯入item資料
        deliver_spinner.setAdapter(adapter1);
        pay_spinner.setAdapter(adapter2);


    }



}
