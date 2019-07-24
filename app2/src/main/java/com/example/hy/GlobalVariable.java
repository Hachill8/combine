package com.example.hy;

import android.app.Application;

public class GlobalVariable extends Application
{
    private String Word;     //要傳送的字串
    //修改 變數字串
    public void setWord(String word){
        this.Word = word;
    }
    //顯示 變數字串
    public String getWord() {
        return Word;
    }
}
