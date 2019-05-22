package com.example.hy;

import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class forum extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    TabItem tabDiscussion;
    TabItem tabExchange;
    Button bt1,bt2;
    RecyclerView recyclerView;
    forum_postadaper adapter;

    List<forum_post> postList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.tablayout);
        tabDiscussion = findViewById(R.id.tabdiscussion);
        tabExchange = findViewById(R.id.tabexchange);
        viewPager = findViewById(R.id.viewpager);

        pagerAdapter = new forum_PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount()) ;
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        bt1=findViewById(R.id.BT1);
        bt2=findViewById(R.id.BT2);

        postList=new ArrayList<>();
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        postList.add(
                new forum_post(
                        1,
                        "如何種植小白菜？",
                        "到底怎樣我的小白\n菜才會給我乖乖長大？",
                        R.drawable.icon_bokchoy));

        postList.add(
                new forum_post(
                        1,
                        "如何種植小白菜？",
                        "到底怎樣我的小白\n菜才會給我乖乖長大？",
                        R.drawable.icon_bokchoy));

        postList.add(
                new forum_post(
                        1,
                        "如何種植小白菜？",
                        "到底怎樣我的小白\n菜才會給我乖乖長大？",
                        R.drawable.icon_bokchoy));

        adapter=new forum_postadaper(this,postList);
        recyclerView.setAdapter(adapter);
    }
}
