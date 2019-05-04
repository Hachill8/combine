package com.example.hy;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

public class crop_info extends AppCompatActivity
{
    private Button bt01,bt02;
    private int[] resId = new int[]
            {       R.drawable.icon201, R.drawable.icon202,
                    R.drawable.icon203, R.drawable.icon204,
                    R.drawable.icon205                  };

    int count = 0;
    private GestureDetector gestureDetector;
    private ImageView iv1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_crop_info);

        iv1 = (ImageView) findViewById(R.id.Img_crop); //獲取ImageView控件id
        gestureDetector = new GestureDetector(onGestureListener);

        bt01 = (Button)findViewById(R.id.BT_heart);
        bt01.setOnClickListener (new Button.OnClickListener()
        {
            boolean fg1=true;
            @Override
            public void onClick(View v)
            {
                if(fg1==true)
                {bt01.setBackgroundResource(R.drawable.like_1);fg1=false;}
                else if(fg1==false)
                {bt01.setBackgroundResource(R.drawable.like_2);fg1=true;}
            }
        }   );

        bt02 = (Button) findViewById(R.id.BT_back);
        bt02.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                {
                    Intent intent = new Intent();
                    intent.setClass(crop_info.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    public GestureDetector.OnGestureListener onGestureListener
            = new GestureDetector.SimpleOnGestureListener()
    {
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
        {
            //得到手觸碰位置的起始點和結束點座標x,y，並進行計算
            float x = e2.getX() - e1.getX();
            float y = e2.getY() - e1.getY();
            if(x > 0){
                count++;
                count%=(resId.length-1);        //想顯示多少圖片就把定義的數組長度-1
            }else if(x < 0){
                count--;
                count=(count+(resId.length-1))%(resId.length-1);
            }

            iv1.setImageResource(resId[count]);  //切換imageView的圖片
            return true;
        }
    };

}
