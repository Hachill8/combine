package com.example.hy;

import android.app.Application;
import android.util.Log;

public class GlobalVariable extends Application
{
    private String Word="No_message";     //要傳送的作物名稱
    private String Vege_item="";
    private String Action_item="";
    //修改 變數字串
    public void setWord(String word){
        this.Word = word;
    }
    //顯示 變數字串
    public String getWord() {
        return Word;
    }

    public void setVege_item(String vege_item){this.Vege_item = vege_item;}
    public String getVege_item(){ return Vege_item;}

    public void setAction_item(String action_item){this.Action_item = action_item;}
    public String getAction_item(){ return Action_item;}
}
