package com.example.hy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

class ImageAdapter extends PagerAdapter {

    private Context mcontext;
    private int[] imageid=new int[]{R.drawable.vege_info_testone,R.drawable.vege_info_testtwo};

    ImageAdapter(Context context)
    {
        mcontext=context;
    }

    @Override
    public int getCount()
    {
        return imageid.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object)
    {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView=new ImageView( mcontext );
        imageView.setScaleType( ImageView.ScaleType.CENTER_CROP );
        imageView.setImageResource( imageid[position] );
        container.addView( imageView,0 );
        return imageView;
    }

    @Override
    public void destroyItem( ViewGroup container, int position,  Object object) {
        container.removeView((ImageView) object );
    }
}
