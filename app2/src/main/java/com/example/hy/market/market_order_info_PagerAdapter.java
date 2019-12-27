package com.example.hy.market;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class market_order_info_PagerAdapter  extends FragmentPagerAdapter {

    private int numoftabs;
    market_order_info_PagerAdapter(FragmentManager fm, int numoftabs) {
        super(fm);
        this.numoftabs=numoftabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new market_order_info_ready_to_ship();
            case 1:
                return new market_order_info_ready_to_receive();
            case 2:
                return new market_order_info_successed();
            case 3:
                return new market_order_info_cancel();
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return numoftabs;
    }

}
