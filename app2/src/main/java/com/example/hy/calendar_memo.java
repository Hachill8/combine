package com.example.hy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class calendar_memo extends AppCompatActivity {

    EditText ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_memo);

        ed = (EditText)findViewById(R.id.edit_message1);

        Intent intent = getIntent();
        //把傳送進來的String型別的Message的值賦給新的變數message
        String message = intent.getStringExtra("EXTRA_MESSAGE");
        //把佈局檔案中的文字框和textview連結起來
        //在textview中顯示出來message
        ed.setText(message);
    }
    public void sendMessage(View view)
    {
        Intent intent = new Intent(calendar_memo.this,calendar.class);
        //宣告一個編輯框和佈局檔案中id為edit_message的編輯框連結起來。
        EditText editText = (EditText) findViewById(R.id.edit_message1);
        //把編輯框獲取的文字賦值給String型別的message
        String message = editText.getText().toString();
        //給message起一個名字，並傳給另一個activity
        intent.putExtra("EXTRA_MESSAGE",message);
        //啟動意圖
        startActivity(intent);
    }

}
