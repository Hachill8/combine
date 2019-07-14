package com.example.hy.forum;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class forum_PagerAdapter extends FragmentPagerAdapter {

    private int numoftabs;
    forum_PagerAdapter(FragmentManager fm, int numoftabs) {
        super(fm);
        this.numoftabs=numoftabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new forum_discussionFragment();
            case 1:
                return new forum_exchangeFragment();
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return numoftabs;
    }
}
