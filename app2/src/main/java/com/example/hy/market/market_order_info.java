package com.example.hy.market;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.hy.R;

import java.util.Objects;

import static com.example.hy.R.id.ready_to_ship;

public class market_order_info extends AppCompatActivity implements ViewPager.OnPageChangeListener,
        TabLayout.OnTabSelectedListener
{
    TabLayout tabLayout;
    ViewPager viewPager;
    TabLayout.Tab ready_to_ship,ready_to_receive,order_successed,order_cancel,tab;
    TextView getReady_to_ship,getReady_to_receive,getOrder_successed,getOrder_cancel;
    private market_order_info_ready_to_ship market_order_info_ready_to_ship = new market_order_info_ready_to_ship();
    private market_order_info_ready_to_receive market_order_info_ready_to_receive = new market_order_info_ready_to_receive();
    private market_order_info_successed market_order_info_successed = new market_order_info_successed();
    private market_order_info_cancel market_order_info_cancel =new market_order_info_cancel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_market_goods_info);

        tabLayout = findViewById(R.id.market_tabLayout);
        viewPager = findViewById(R.id.market_viewPager);
        viewPager.addOnPageChangeListener(this);
        tabLayout.addOnTabSelectedListener(this);
//        getReady_to_ship = ready_to_ship.getCustomView().findViewById(R.id.ready_to_ship);
//        getReady_to_receive = ready_to_receive.getCustomView().findViewById(R.id.ready_to_receive);
//        getOrder_successed = order_successed.getCustomView().findViewById(R.id.order_successed);
//        getOrder_cancel = order_cancel.getCustomView().findViewById(R.id.order_cancel);

        //添加适配器，在viewPager里引入Fragment
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return market_order_info_ready_to_ship;
                    case 1:
                        return market_order_info_ready_to_receive;
                    case 2:
                        return market_order_info_successed;
                    case 3:
                        return market_order_info_cancel;
                }
                return null;
            }
            @Override
            public int getCount() {
                return 4;
            }
        });




    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

//        getReady_to_ship = tab.getCustomView().findViewById(R.id.ready_to_ship);
//        getReady_to_receive = tab.getCustomView().findViewById(R.id.ready_to_receive);
//        getOrder_successed = tab.getCustomView().findViewById(R.id.order_successed);
//        getOrder_cancel = tab.getCustomView().findViewById(R.id.order_cancel);


        viewPager.setCurrentItem(tab.getPosition());
//        switch (tab.getPosition()){
//            case R.id.ready_to_ship:
//                getReady_to_ship.setSelected(true);
//                getReady_to_receive.setSelected(false);
//                getOrder_successed.setSelected(false);
//                getOrder_cancel.setSelected(false);
//                break;
//            case R.id.ready_to_receive:
//                getReady_to_ship.setSelected(false);
//                getReady_to_receive.setSelected(true);
//                getOrder_successed.setSelected(false);
//                getOrder_cancel.setSelected(false);
//                break;
//            case R.id.order_successed:
//                getReady_to_ship.setSelected(false);
//                getReady_to_receive.setSelected(false);
//                getOrder_successed.setSelected(true);
//                getOrder_cancel.setSelected(false);
//                break;
//            case R.id.order_cancel:
//                getReady_to_ship.setSelected(false);
//                getReady_to_receive.setSelected(false);
//                getOrder_successed.setSelected(false);
//                getOrder_cancel.setSelected(true);
//                break;
//        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

}
