package com.example.hy;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class select_model extends AppCompatActivity {

    private ViewPager slideViewPager;
    private LinearLayout dotsLayout;
    private SliderAdapter sliderAdapter;
    Dialog choose_pot_or_vege;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_select_model);

        choose_pot_or_vege=new Dialog(this);

        slideViewPager=(ViewPager)findViewById(R.id.slideViewPager);
        dotsLayout=(LinearLayout)findViewById(R.id.dotsLayout);

        sliderAdapter=new SliderAdapter(this);

        slideViewPager.setAdapter(sliderAdapter);




    }




    private class SliderAdapter extends PagerAdapter
    {

        Context context;
        LayoutInflater layoutInflater;

        public SliderAdapter(Context context)

        {
            this.context=context;
        }


        //陣列
        public int[] slide_imagebtns=
                {
                        R.drawable.potted_plant,
                        R.drawable.one_meter_garden,
                        R.drawable.farm_management

                };


        public String[] slide_headings=
                {
                        "初階_盆栽種植",
                        "中階_一米菜園",
                        "高階_農園栽種"

                };

        public String[] slide_descs=
                {
                        "適合剛學習種植的新手，占地範圍小，成功率高。",
                        "適合多樣化種植，減少占地範圍；對作物有基本了解，喜歡挑戰新型種植法。",
                        "適合剛學習種植的新手，占地範圍小，成功率高"

                };



        @Override
        public int getCount()
        {
            return slide_headings.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o)
        {
            return view==(LinearLayout)o;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position)
        {
            layoutInflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View view=layoutInflater.inflate(R.layout.slide_layout,container,false);

            ImageView slideImagebtn =(ImageButton) view.findViewById(R.id.slide_imagebtn);
            TextView slideHeading =(TextView) view.findViewById(R.id.slide_heading);
            TextView slideDescription =(TextView) view.findViewById(R.id.slide_desc);


            slideImagebtn.setImageResource(slide_imagebtns[position]);
            slideHeading.setText(slide_headings[position]);
            slideDescription.setText(slide_descs[position]);



            slideImagebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    ShowPopUp();
                }
            });

            container.addView(view);
            return view;


        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object)
        {
            container.removeView((LinearLayout)object);
        }





    }


    //跳出對話框
    private void ShowPopUp()
    {
        Button btn_choose_pot,btn_choose_vege;

        choose_pot_or_vege.setContentView(R.layout.choose_pot_ot_vege);

        btn_choose_pot=(Button)choose_pot_or_vege.findViewById(R.id.btn_choose_pot);
        btn_choose_vege=(Button)choose_pot_or_vege.findViewById(R.id.btn_choose_vege);



        btn_choose_pot.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent a = new Intent(select_model.this,pot_size.class);
                startActivity(a);
            }
        });

        btn_choose_vege.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent b = new Intent(select_model.this,pot_size.class);
                startActivity(b);
            }
        });


        choose_pot_or_vege.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        choose_pot_or_vege.show();
    }




}
