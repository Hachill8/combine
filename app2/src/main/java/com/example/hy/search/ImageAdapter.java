package com.example.hy.search;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hy.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

class ImageAdapter extends PagerAdapter {

    private Context mcontext;
    private int[] imageid=new int[]{R.drawable.vege_imgnone};
    ImageView imageView;
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
        Log.v("test","imgadapter: 4" );
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView=new ImageView( mcontext );
        imageView.setScaleType( ImageView.ScaleType.CENTER_CROP );
        imageView.setImageResource( imageid[position] );
        container.addView( imageView,0 );
        Log.v("test","imgadapter: 1" );
        return imageView;
    }

    @Override
    public void destroyItem( ViewGroup container, int position,  Object object) {
        Log.v("test","imgadapter: 3" );
        container.removeView((ImageView) object );
    }

    public void decode64(String img_result)
    {
        Bitmap bitmap=null;
        imageView = new ImageView(mcontext );
        byte[] decode = Base64.decode(img_result,Base64.NO_CLOSE);
        bitmap = BitmapFactory.decodeByteArray(decode, 0, decode.length);
        Log.v("test","bitmap: "+decode.toString());
        Log.v("test","imgadapter: 2" );

    }
}
