package com.example.hy.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hy.GlobalVariable;
import com.example.hy.R;
import com.example.hy.select_model;
import com.example.hy.webservice;

import static com.example.hy.R.drawable.login2_button_action;

public class login2 extends AppCompatActivity  {

    //找到UI工人的經紀人，這樣才能派遣工作  (找到顯示畫面的UI Thread上的Handler)
    private Handler mUI_Handler = new Handler();
    //宣告特約工人的經紀人
    private Handler mThreadHandler;
    //宣告特約工人
    private HandlerThread mThread;

    EditText name,phone,email;
    Button user_info_confrim,Man,Woman,One_year,Two_year,Three_year;
    int gender=0, //判斷目前選到哪個性別
            experience=0; //判斷目前種植經驗多久
    Spinner County,Age;
    String insert_vege_item="",countryshow,areashow,addr,select_gender="",select_expri="",select_age="";
    GlobalVariable gl;
    Intent intent;
    private  String[] type= new String[]{"臺北市","新北市","基隆市","桃園市","新竹縣","新竹市",
            "苗栗縣","臺中市","南投縣","彰化縣","雲林縣","嘉義縣","嘉義市","臺南市","高雄市",
            "屏東縣","宜蘭縣","花蓮縣","臺東縣","澎湖縣","金門縣","連江縣"};
    private String[] area = new String[]{"中正區", "大同區", "中山區", "萬華區", "信義區", "松山區", "大安區",
            "南港區", "北投區", "內湖區", "士林區", "文山區"};
    private String[][] type2 = new String[][]{
            {"中正區", "大同區", "中山區", "萬華區", "信義區", "松山區", "大安區",
                    "南港區", "北投區", "內湖區", "士林區", "文山區"},
            {"板橋區", "新莊區", "泰山區", "林口區", "淡水區", "金山區", "八里區",
                    "萬里區", "石門區", "三芝區", "瑞芳區", "汐止區", "平溪區", "貢寮區",
                    "雙溪區", "深坑區", "石碇區", "新店區", "坪林區", "烏來區", "中和區",
                    "永和區", "土城區", "三峽區", "樹林區", "鶯歌區", "三重區", "蘆洲區", "五股區"},
            {"仁愛區", "中正區", "信義區", "中山區", "安樂區", "暖暖區", "七堵區"},
            {"桃園區", "中壢區", "平鎮區", "八德區", "楊梅區", "蘆竹區", "龜山區", "龍潭區", "大溪區", "大園區", "觀音區", "新屋區", "復興區"},
            {"竹北市", "竹東鎮", "新埔鎮", "關西鎮", "峨眉鄉", "寶山鄉", "北埔鄉", "橫山鄉", "芎林鄉", "湖口鄉", "新豐鄉", "尖石鄉", "五峰鄉"},
            {"東區", "北區", "香山區"},
            {"苗栗市", "通霄鎮", "苑裡鎮", "竹南鎮", "頭份鎮", "後龍鎮", "卓蘭鎮", "西湖鄉", "頭屋鄉", "公館鄉", "銅鑼鄉", "三義鄉", "造橋鄉",
                    "三灣鄉", "南庄鄉", "大湖鄉", "獅潭鄉", "泰安鄉"},
            {"中區", "東區", "南區", "西區", "北區", "北屯區", "西屯區", "南屯區", "太平區", "大里區", "霧峰區", "烏日區", "豐原區", "后里區",
                    "東勢區", "石岡區", "新社區", "和平區", "神岡區", "潭子區", "大雅區", "大肚區", "龍井區", "沙鹿區", "梧棲區", "清水區",
                    "大甲區", "外埔區", "大安區"},
            {"南投市", "埔里鎮", "草屯鎮", "竹山鎮", "集集鎮", "名間鄉", "鹿谷鄉", "中寮鄉", "魚池鄉", "國姓鄉", "水里鄉", "信義鄉", "仁愛鄉"},
            {"彰化市", "員林鎮", "和美鎮", "鹿港鎮", "溪湖鎮", "二林鎮", "田中鎮", "北斗鎮", "花壇鄉", "芬園鄉", "大村鄉", "永靖鄉", "伸港鄉",
                    "線西鄉", "福興鄉", "秀水鄉", "埔心鄉", "埔鹽鄉", "大城鄉", "芳苑鄉", "竹塘鄉", "社頭鄉", "二水鄉", "田尾鄉", "埤頭鄉",
                    "溪州鄉"},
            {"斗六市", "斗南鎮", "虎尾鎮", "西螺鎮", "土庫鎮", "北港鎮", "莿桐鄉", "林內鄉", "古坑鄉", "大埤鄉", "崙背鄉", "二崙鄉", "麥寮鄉",
                    "臺西鄉", "東勢鄉", "褒忠鄉", "四湖鄉", "口湖鄉", "水林鄉", "元長鄉"},
            {"太保市", "朴子市", "布袋鎮", "大林鎮", "民雄鄉", "溪口鄉", "新港鄉", "六腳鄉", "東石鄉", "義竹鄉", "鹿草鄉", "水上鄉", "中埔鄉",
                    "竹崎鄉", "梅山鄉", "番路鄉", "大埔鄉", "阿里山鄉"},
            {"東區", "西區"},
            {"中西區", "東區", "南區", "北區", "安平區", "安南區", "永康區", "歸仁區", "新化區", "左鎮區", "玉井區", "楠西區", "南化區", "仁德區",
                    "關廟區", "龍崎區", "官田區", "麻豆區", "佳里區", "西港區", "七股區", "將軍區", "學甲區", "北門區", "新營區", "後壁區", "白河區",
                    "東山區", "六甲區", "下營區", "柳營區", "鹽水區", "善化區", "大內區", "山上區", "新市區", "安定區"},
            {"楠梓區", "左營區", "鼓山區", "三民區", "鹽埕區", "前金區", "新興區", "苓雅區", "前鎮區", "小港區", "旗津區", "鳳山區", "大寮區", "鳥松區",
                    "林園區", "仁武區", "大樹區", "大社區", "岡山區", "路竹區", "橋頭區", "梓官區", "彌陀區", "永安區", "燕巢區", "田寮區", "阿蓮區", "茄萣區",
                    "湖內區", "旗山區", "美濃區", "內門區", "杉林區", "甲仙區", "六龜區", "茂林區", "桃源區", "那瑪夏區"},
            {"屏東市", "潮州鎮", "東港鎮", "恆春鎮", "萬丹鄉", "長治鄉", "麟洛鄉", "九如鄉", "里港鄉", "鹽埔鄉", "高樹鄉", "萬巒鄉", "內埔鄉", "竹田鄉", "新埤鄉",
                    "枋寮鄉", "新園鄉", "崁頂鄉", "林邊鄉", "南州鄉", "佳冬鄉", "琉球鄉", "車城鄉", "滿州鄉", "枋山鄉", "霧台鄉", "瑪家鄉", "泰武鄉", "來義鄉",
                    "春日鄉", "獅子鄉", "牡丹鄉", "三地門鄉"},
            {"宜蘭市", "羅東鎮", "蘇澳鎮", "頭城鎮", "礁溪鄉", "壯圍鄉", "員山鄉", "冬山鄉", "五結鄉", "三星鄉", "大同鄉", "南澳鄉"},
            {"花蓮市", "鳳林鎮", "玉里鎮", "新城鄉", "吉安鄉", "壽豐鄉", "秀林鄉", "光復鄉", "豐濱鄉", "瑞穗鄉", "萬榮鄉", "富里鄉", "卓溪鄉"},
            {"臺東市", "成功鎮", "關山鎮", "長濱鄉", "海端鄉", "池上鄉", "東河鄉", "鹿野鄉", "延平鄉", "卑南鄉", "金峰鄉", "大武鄉", "達仁鄉",
                    "綠島鄉", "蘭嶼鄉", "太麻里鄉"},
            {"馬公市", "湖西鄉", "白沙鄉", "西嶼鄉", "望安鄉", "七美鄉"},
            {"金城鎮", "金湖鎮", "金沙鎮", "金寧鄉", "烈嶼鄉", "烏坵鄉"},
            {"南竿鄉", "北竿鄉", "莒光鄉", "東引鄉"},

    };
    private Spinner sp;//第一個下拉選單
    private Spinner sp2;//第二個下拉選單
    private Context context;
    ArrayAdapter<String> adapter ;
    ArrayAdapter<String> adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("test","進入login2最上面");
        super.onCreate(savedInstanceState);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login2);

        //聘請一個特約工人，有其經紀人派遣其工人做事 (另起一個有Handler的Thread)
        mThread = new HandlerThread("");

        //讓Worker待命，等待其工作 (開啟Thread)
        mThread.start();

        //找到特約工人的經紀人，這樣才能派遣工作 (找到Thread上的Handler)
        mThreadHandler=new Handler(mThread.getLooper());

        Log.v("test","進入login2.xml");
        context = this;
        gl= (GlobalVariable)getApplicationContext();

        name=findViewById(R.id.name);
        phone=findViewById(R.id.phone);
        email=findViewById(R.id.email);
        user_info_confrim  = (Button) findViewById(R.id.user_info_confirm);


        //使用者按下確認後
        user_info_confrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("test","onClick");
                Intent a = new Intent(login2.this, select_model.class);
                if(name.getText().toString().equals("") || phone.getText().toString().equals("")||email.getText().toString().equals("")||
                        addr.equals("")||select_age.equals("")||select_gender.equals("")||select_expri.equals(""))
                {
                    Toast.makeText(login2.this,"資料未輸入完整 !",Toast.LENGTH_SHORT).show();
                }
                else
                    {
                    mThreadHandler.post(r1);
                    gl.setUser_name(name.getText().toString());
                    gl.setUser_gmail(email.getText().toString());
                    startActivity(a);
                }
            }
        });

        Intent intent= getIntent();
        String getemail=intent.getStringExtra("giveemail");
        email.setText(getemail);

        Man = (Button) findViewById(R.id.man);
        Woman = (Button) findViewById(R.id.woman);
        One_year = (Button) findViewById(R.id.one_year);
        Two_year = (Button) findViewById(R.id.two_year);
        Three_year = (Button) findViewById(R.id.three_year);

        // 選取的效果
        Man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gender==2)
                {
                    Woman.setBackground(getResources().getDrawable( R.drawable.gender ));
                    Man.setBackground(getResources().getDrawable( R.drawable.login2_button_action));
                }
                else
                {
                    Man.setBackground(getResources().getDrawable( R.drawable.login2_button_action));
                }
                gender=1;
                select_gender="男";
            }
        });

        Woman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gender==1)
                {
                    Man.setBackground(getResources().getDrawable( R.drawable.gender ));
                    Woman.setBackground(getResources().getDrawable( R.drawable.login2_button_action));
                }
                else
                {
                    Woman.setBackground(getResources().getDrawable( R.drawable.login2_button_action));
                }
                gender=2;
                select_gender="女";
            }
        });

        One_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(experience==2 || experience==3)
                {
                    Two_year.setBackground(getResources().getDrawable( R.drawable.gender ));
                    Three_year.setBackground(getResources().getDrawable( R.drawable.gender ));
                    One_year.setBackground(getResources().getDrawable((login2_button_action)));
                }
                else
                {
                    One_year.setBackground(getResources().getDrawable((login2_button_action)));
                }
                experience=1;
                select_expri="1年以上";
            }
        });

        Two_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(experience==1 || experience==3)
                {
                    One_year.setBackground(getResources().getDrawable( R.drawable.gender ));
                    Three_year.setBackground(getResources().getDrawable( R.drawable.gender ));
                    Two_year.setBackground(getResources().getDrawable((login2_button_action)));
                }
                else
                {
                    Two_year.setBackground(getResources().getDrawable((login2_button_action)));
                }
                experience=2;
                select_expri="2年以上~3年以下";
            }
        });

        Three_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(experience==2 || experience==1)
                {
                    Two_year.setBackground(getResources().getDrawable( R.drawable.gender ));
                    One_year.setBackground(getResources().getDrawable( R.drawable.gender ));
                    Three_year.setBackground(getResources().getDrawable((login2_button_action)));
                }
                else
                {
                    Three_year.setBackground(getResources().getDrawable((login2_button_action)));
                }
                experience=3;
                select_expri="3年以上";
            }
        });


        Age = (Spinner) findViewById(R.id.age);

//        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(login2.this,
//                R.layout.login2_select_dropdown_item,                            //選項資料內容
//                getResources().getStringArray(R.array.county_item));
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(login2.this,
                R.layout.login2_select_dropdown_item,                            //選項資料內容
                getResources().getStringArray(R.array.age_item));

//        adapter1.setDropDownViewResource(R.layout.login2_select_dropdown_item);
//
//        County.setAdapter(adapter1);
        Age.setAdapter(adapter2);
        Age.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView adapterView, View view, int position, long id){
                select_age=Age.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView arg0) {
            }
        });

        //程式剛啟始時載入第一個下拉選單
        adapter = new ArrayAdapter<String>(this,R.layout.login2_select_dropdown_item, type);
        adapter.setDropDownViewResource(R.layout.login2_select_dropdown_item);
        sp = findViewById(R.id.type);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(selectListener);

        //因為下拉選單第一個為臺北市，所以先載入臺北市的地區進第二個下拉選單
        adapter2 = new ArrayAdapter<String>(this,R.layout.login2_select_dropdown_item, area);
        adapter2.setDropDownViewResource(R.layout.login2_select_dropdown_item);
        sp2 = findViewById(R.id.type2);
        sp2.setAdapter(adapter2);

        sp2.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView adapterView, View view, int position, long id){
                areashow=sp2.getSelectedItem().toString();
                addr=countryshow+areashow;
            }
            public void onNothingSelected(AdapterView arg0) {
            }
        });



    }


    //第一個下拉類別的監看式
    private AdapterView.OnItemSelectedListener selectListener = new AdapterView.OnItemSelectedListener(){
        public void onItemSelected(AdapterView<?> parent, View v, int position,long id){
            //讀取第一個下拉選單是選擇第幾個
            int pos = sp.getSelectedItemPosition();
            countryshow=sp.getSelectedItem().toString();
            //重新產生新的Adapter，用的是二維陣列type2[pos]
            adapter2 = new ArrayAdapter<String>(context,R.layout.login2_select_dropdown_item, type2[pos]);
            //載入第二個下拉選單Spinner
            sp2.setAdapter(adapter2);
        }

        public void onNothingSelected(AdapterView<?> arg0){

        }

    };

    //工作名稱 r1 的工作內容

    private Runnable r1=new Runnable () {

        public void run() {
            webservice.Update_user_info(email.getText().toString(),name.getText().toString(),phone.getText().toString(),addr,select_age,select_gender,select_expri);

        }

    };





    @Override
    protected void onDestroy() {
        super.onDestroy();

        //移除工人上的工作
        if (mThreadHandler != null) {
            mThreadHandler.removeCallbacks(r1);
        }
        //解聘工人 (關閉Thread)
        if (mThread != null) {
            mThread.quit();
        }
    }




}
