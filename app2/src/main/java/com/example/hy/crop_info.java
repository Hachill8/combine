package com.example.hy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;
import android.app.AlertDialog;

public class crop_info extends AppCompatActivity
{
    private Button bt_like,bt_back,bt_stplant,bt_病蟲害,bt_注意事項;
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

        bt_like = (Button)findViewById(R.id.BT_heart);
        bt_like.setOnClickListener (new Button.OnClickListener()
        {
            boolean fg1=true;
            @Override
            public void onClick(View v)
            {
                if(fg1==true)
                {bt_like.setBackgroundResource(R.drawable.like_1);fg1=false;
                    Toast.makeText(crop_info.this, "已收藏", Toast.LENGTH_SHORT).show();}
                else if(fg1==false)
                {bt_like.setBackgroundResource(R.drawable.like_2);fg1=true;
                    Toast.makeText(crop_info.this, "已取消收藏", Toast.LENGTH_SHORT).show();}
            }
        }   );

        bt_stplant = (Button)findViewById(R.id.BT_stplant);
        bt_stplant.setOnClickListener (new Button.OnClickListener()
        {
            boolean fg1=true;
            @Override
            public void onClick(View v)
            {
                if(fg1==true)
                {bt_stplant.setBackgroundResource(R.drawable.icon_stplant01);fg1=false;
                    //Toast.makeText跳出提示訊息告知user系統收到該指令
                    Toast.makeText(crop_info.this, "開始種植", Toast.LENGTH_SHORT).show();}
                else if(fg1==false)
                {bt_stplant.setBackgroundResource(R.drawable.icon_stplant02);fg1=true;
                    Toast.makeText(crop_info.this, "取消種植", Toast.LENGTH_SHORT).show();}
            }
        }   );


        bt_病蟲害 = (Button)findViewById(R.id.BT_病蟲害);
        bt_病蟲害.setOnClickListener (new Button.OnClickListener()
        {
            boolean fg1=true;
            @Override
            public void onClick(View v)
            {
                if(fg1==true)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(crop_info.this);    //main是class name
                    builder.setTitle("小白菜_病蟲害防治");//作物名稱需隨查詢作物不同而變動
                    builder.setMessage("黑斑病(Alternaria leaf spot; Black leaf spot; Brown rot)\n" +
                            "\n" +
                            "學名：Alternaria brassicae (Berk) Sacc.\n" +
                            "\n" +
                            "(費雯綺、王喻其編。2007。植物保護手冊─蔬菜篇，第59頁。台中。229頁。)\n" +
                            "\n" +
                            "病原生態：\n" +
                            "　　本病靠病斑上分生孢子飛散而傳播。典型的病徵在葉片形成大小2～3公厘，呈淡褐色同心輪紋的圓形病斑，病斑後期，中央易破裂。在種子受害，種皮常出現皺縮或使發芽率急降。幼苗的胚莖會受種子上的菌危害，嚴重時易造成猝倒症。\n" +
                            "\n" +
                            "(黃振文。1989。十字花科。台灣農家全書，第57頁。行政院農委會。台北。263頁。)\n" +
                            "\n" +
                            "病徵：\n" +
                            "　　葉、莖等皆可被感染被害部呈現2～10公分圓形且具鮮明同心輪紋狀褐色病斑。老葉發生較多，病斑數目多時，病斑相連在一起而引起葉片呈枯焦狀。葉柄、莖部受害時，患部呈黑色凹陷圓形斑或條斑。\n" +
                            "\n" +
                            "(黃振文。1989。十字花科。台灣農家全書，第57頁。行政院農委會。台北。263頁。)\n" +
                            "\n" +
                            "防治方法：\n" +
                            "\n" +
                            "一、\n" +
                            "\n" +
                            "健株採種，以獲得清潔健康的種子。\n" +
                            "\n" +
                            "二、\n" +
                            "\n" +
                            "在50℃的溫湯中，浸種30分鐘。\n" +
                            "\n" +
                            "三、\n" +
                            "\n" +
                            "將病株或病葉燒毀或深埋土中。\n" +
                            "\n" +
                            "四、\n" +
                            "\n" +
                            "輪作十字花科蔬菜以外的作物。\n" +
                            "\n" +
                            "五、\n" +
                            "\n" +
                            "注意氮磷鉀的使用，勿缺肥。\n" +
                            "\n" +
                            "六、\n" +
                            "\n" +
                            "葉片長出5、6枚時，以10％保粒丹1000倍液或是依普同1000倍液，每隔7～10天施用一次。\n" +
                            "\n" +
                            "(黃振文。1989。十字花科病害。台灣農家全書，第183頁。行政院農委會。台北。263頁。)");//內容需隨查詢作物不同而變動
                    builder.show();
                }
            }
        }   );


        bt_注意事項 = (Button)findViewById(R.id.BT_注意事項);
        bt_注意事項.setOnClickListener (new Button.OnClickListener()
        {
            boolean fg1=true;
            @Override
            public void onClick(View v)
            {
                if(fg1==true)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(crop_info.this);    //main是class name
                    builder.setTitle("小白菜_注意事項");//作物名稱需隨查詢作物不同而變動
                    builder.setMessage("一、\n" +"小白菜採撒播的方式栽種，必須注意種子要疏密適當，苗株才能均勻生長。\n" +
                            "\n" +
                            "二、\n" +"小白菜根系淺，不耐旱，生長期間要有充足的水分。此外，必須注意土壤要排水良好，以防產生病蟲害。澆水時注意避免沖散種子。\n" +
                            "\n" +
                            "三、\n" +"栽種小白菜可以紗網覆蓋，略為抵擋風雨。");//內容需隨查詢作物不同而變動
                    builder.show();
                }
            }
        }   );


        bt_back = (Button) findViewById(R.id.BT_back);
        bt_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                {
                    Intent intent = new Intent();
                    intent.setClass(crop_info.this, search.class);
                    startActivity(intent);
                }
            }
        });
    }




    public boolean onTouchEvent(MotionEvent event)
    {   return gestureDetector.onTouchEvent(event); }

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
