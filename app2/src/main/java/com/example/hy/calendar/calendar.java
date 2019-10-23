package com.example.hy.calendar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hy.GlobalVariable;
import com.example.hy.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class calendar extends AppCompatActivity {
    CalendarView cv1;
    TextView tv1,tv2,act_tv;
    String[] listItems;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();
    Button edit,mBtn;
    Spinner spi;
    Switch swi;
    private static  final  int REQUEST_CODE=1;
    GlobalVariable action_item_value,action_item_value2;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_calendar);

        edit =(Button)findViewById(R.id.edit);
        cv1 = (CalendarView)findViewById(R.id.CV01);
        tv1 = (TextView)findViewById(R.id.TV01);
        tv2 = (TextView)findViewById(R.id.TV02);
        mBtn = (Button)findViewById(R.id.bt01);
        act_tv = (TextView)findViewById(R.id.act_tv);
        action_item_value= (GlobalVariable)getApplicationContext();
        action_item_value2= (GlobalVariable)getApplicationContext();
        spi = (Spinner)findViewById(R.id.spinner);
        swi = (Switch) findViewById(R.id.switch1);

        swi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Intent intent = new Intent(calendar.this, fake_plant_suggestion.class);
                    startActivity(intent);
                } else {

                }
            }
        });
        final String[] lunch = {"A101 小白菜","B102 秋葵", "B103 空心菜", "C104 九層塔", "C105 迷迭香"};
        ArrayAdapter<String> lunchList = new ArrayAdapter<>(calendar.this,
                R.layout.zzz,
                lunch);

        spi.setAdapter(lunchList);
        spi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(calendar.this, "你選的是" + lunch[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        listItems = getResources().getStringArray(R.array.action_item);
        checkedItems = new boolean[listItems.length];

//        mBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(calendar.this);
//                //String array for alert dialog multichoice items
//                final String[] actionsArray = new String[]{"育苗","播種","澆水","施肥","鋤草","防蟲","除病","收成"};
//                //Boolean array for initial selected items
//                final boolean[] checkedActionsArray = new boolean[]{
//                        false,
//                        false,
//                        false,
//                        false,
//                        false,
//                        false,
//                        false,
//                        false,
//
//                };
//                //convert the acts array to list
//                final List<String> actionsList = Arrays.asList(actionsArray);
//                //set AlertDialog title
//                builder.setTitle("選擇活動");
//                //set icon(optional)
//                builder.setIcon(R.drawable.ico);
//                //set multichoice
//                builder.setMultiChoiceItems(actionsArray, checkedActionsArray, new DialogInterface.OnMultiChoiceClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//                        //update current focused item's checked status
//                        checkedActionsArray[which] = isChecked;
//                        //get the current focused item
//                        String currentItem = actionsList.get(which);
//                        //notify the current action
//                        Toast.makeText(calendar.this, "很棒喔! 有"+currentItem, Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//
//
//
//                //set positive/yes button click listener
//                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        mTv.setText("");
//                        String action="";
//                        for (int i=0; i<checkedActionsArray.length; i++){
//                            boolean checked = checkedActionsArray[i];
//                            if (checked){
//                                action = mTv.getText()+ actionsList.get(i) + " / ";
//                                mTv.setText(action);
//                            }
//                        }
//                        action_item_value.setAction_item(action);
//                    }
//                });
//
//
//
//                //set neutral/cancel button click listener
//                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        //do something here
//                    }
//                });
//
//                AlertDialog dialog = builder.create();
//                //show alert dialog
//                dialog.show();
//            }
//        });


        cv1.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int y, int m, int dm) {
                date = y+"/"+(m+1)+"/"+dm;
                tv1.setText(date);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(calendar.this,calendar_memo.class);
                //給message起一個名字，並傳給另一個activity
                intent2.putExtra("EXTRA_DATE",date);
                startActivity(intent2);
            }
        });

        Intent intent = getIntent();
        //把傳送進來的String型別的Message的值賦給新的變數message
        String message = intent.getStringExtra("EXTRA_MESSAGE");
        //所勾選的活動
        String s = intent.getStringExtra("EXTRA_MESSAGE2");
        //把佈局檔案中的文字框和textview連結起來
        //在textview中顯示出來message
        tv2.setText(message);
        if((!tv2.getText().equals("")) || tv2.getText().equals("")) {
            act_tv.setText(s);
            Toast.makeText(calendar.this, "OK", Toast.LENGTH_SHORT).show();
        }
    }
    public void sendMessage(View v)
    {
        Intent intent = new Intent(calendar.this,calendar_memo.class);
        //宣告一個編輯框和佈局檔案中id為edit_message的編輯框連結起來。
        //把編輯框獲取的文字賦值給String型別的message
        String message = tv2.getText().toString();
        //給message起一個名字，並傳給另一個activity
        intent.putExtra("EXTRA_MESSAGE",message);
        //啟動意圖
        startActivity(intent);
    }
}
