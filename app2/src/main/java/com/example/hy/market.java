package com.example.hy;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class market extends AppCompatActivity
{
    private ViewPager viewPager;
    private PagerAdapter adapter;
    private List<View> viewPages = new ArrayList<>();

    //包點點的LinearLayout
    private ViewGroup group;
    private ImageView imageView;
    //定義ImageView陣列存放小點
    private ImageView[] imageViews;

    private FragmentManager fmgr;
    private market_commodity mc;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        fmgr = getSupportFragmentManager();
        mc = new market_commodity();




        initView();
        initPageAdapter();
        initPointer();
        initEvent();
    }
    public void setcommo(View view)
    {
        FragmentTransaction transaction = fmgr.beginTransaction();
        transaction.replace(R.id.activity_market_fragment,mc);
        transaction.commit();
    }

    //為控制元件繫結事件,繫結介面卡
    private void initEvent() {
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new GuidePageChangeListener());
    }
    //初始化ViewPager
    private void initPageAdapter() {
/**
 * 對於這幾個想要動態載入的page頁面，使用LayoutInflater.inflate()來找到其佈局檔案，並例項化為View物件
 */
        LayoutInflater inflater = LayoutInflater.from(this);
        View page1 = inflater.inflate(R.layout.market_viewpage1, null);
        View page2 = inflater.inflate(R.layout.market_viewpage2, null);
        View page3 = inflater.inflate(R.layout.market_viewpage3, null);
//新增到集合中
        viewPages.add(page1);
        viewPages.add(page2);
        viewPages.add(page3);
        adapter = new PagerAdapter() {
            //獲取當前介面個數
            @Override
            public int getCount() {
                return viewPages.size();
            }
            //判斷是否由物件生成頁面
            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(viewPages.get(position));
            }
            //返回一個物件，這個物件表明了PagerAdapter介面卡選擇哪個物件放在當前的ViewPager中
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = viewPages.get(position);
                container.addView(view);
                return view;
            }
        };
    }
    //繫結控制元件
    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.market_viewpager);
        group = (ViewGroup) findViewById(R.id.market_viewgroup);
    }
    //初始化下面的小圓點的方法
    private void initPointer() {
//有多少個介面就new多長的陣列
        imageViews = new ImageView[viewPages.size()];
        for (int i = 0; i < imageViews.length; i++ )
        {
            imageView = new ImageView(this);
//設定控制元件的寬高
            imageView.setLayoutParams(new ViewGroup.LayoutParams(30, 30));
//設定控制元件的padding屬性
            imageView.setPadding(60, 0, 60, 0);
            imageViews[i] = imageView;
//初始化第一個page頁面的圖片的原點為選中狀態
            if (i == 0) {
//表示當前圖片
                imageViews[i].setBackgroundResource(R.drawable.page_focused);
/**
 * 在java程式碼中動態生成ImageView的時候
 * 要設定其BackgroundResource屬性才有效
 * 設定ImageResource屬性無效
 */
            } else {
                imageViews[i].setBackgroundResource(R.drawable.page_unfocused);
            }

            group.addView(imageViews[i]);
        }
    }
    //ViewPager的onPageChangeListener監聽事件，當ViewPager的page頁發生變化的時候呼叫
    public class GuidePageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }
        //頁面滑動完成後執行
        @Override
        public void onPageSelected(int position) {
//判斷當前是在那個page，就把對應下標的ImageView原點設定為選中狀態的圖片
            for (int i = 0; i < imageViews.length; i ++ )
            {
                imageViews[position].setBackgroundResource(R.drawable.page_focused);
                if (position != i)
                {
                    imageViews[i].setBackgroundResource(R.drawable.page_unfocused);
                }
            }
        }
        //監聽頁面的狀態，0--靜止 1--滑動  2--滑動完成
        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }


}
