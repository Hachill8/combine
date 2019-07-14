package com.example.hy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

public class login2 extends AppCompatActivity implements  NumberPicker.OnValueChangeListener {
//AdapterView.OnItemSelectedListener,

    private TextView tvShowNumbers;
    public TextView Name;
    Button btntomodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login2);

        Name=(TextView)findViewById(R.id.tv_name);
        //取得intent中的bundle物件
        Bundle bundle01 =this.getIntent().getExtras();
        String name = bundle01.getString("name");
        Name.setText(name);


        Spinner spinner = (Spinner) findViewById(R.id.spnSex);
        final String[] item = {"男", "女"};
        //將下拉選單改為自行設定樣式
        ArrayAdapter<String> itemlist = new ArrayAdapter<>(this,R.layout.z_spnsex_setting,item);
        spinner.setAdapter(itemlist);
        //下拉選單改回預設樣式
        //ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.sex_list,android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner.setOnItemSelectedListener(this);

        tvShowNumbers=findViewById(R.id.tvShowNumbers);
        NumberPicker numberPicker=findViewById(R.id.numberPicker);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);
        numberPicker.setOnValueChangedListener(this);

        btntomodel = (Button) findViewById(R.id.btntomodel);

        btntomodel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(login2.this,select_model.class);
                startActivity(a);

            }
        });

    }

    @Override
    public void onValueChange(NumberPicker picker, int i, int newVal)
    {
        tvShowNumbers.setText("種植年數: "+i+" 年");
    }

}
