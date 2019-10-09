package com.example.hy.search;

import android.os.Handler;
import android.os.HandlerThread;
import android.widget.Toast;

import com.example.hy.webservice;

public class select_month_info
{
    static String select="";

    public static String select_month_item(String select_month)
    {
        select = select_month;
        Thread thread = new Thread() {
            public void run() {
                select = webservice.Search_select(select);
            }
        };
        thread.start();
        return select;
    }
}
