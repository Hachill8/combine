package com.example.hy;

import android.app.Application;
import android.util.Log;

public class GlobalVariable extends Application
{
    private String Word="No_message";     //要傳送的作物名稱
    private String Vege_item="";
    private String Action_item="";
    private String Vege_image_home="無";
    private String Select_month="無";
    private String Forum_content="錯誤",Forum_title="錯誤";
    private String Market_item="無";
    private String User_email="123456";
    private String User_name="GO";

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

    public void setVege_image_home(String vege_image_home){this.Vege_image_home = vege_image_home;}
    public String getVege_image_home(){ return Vege_image_home;}

    public void setForum_content(String forum_content){this.Forum_content = forum_content;}
    public String getForum_content(){ return Forum_content;}

    public void setForum_title(String forum_title){this.Forum_title = forum_title;}
    public String getForum_title(){ return Forum_title;}

    public void setMarket_item(String market_item){this.Market_item = market_item;}
    public String getMarket_item(){ return Market_item;}



    public void setSelect_month(String select_month){this.Select_month = select_month;  Log.v("test","gl的select: "+select_month);}
    public  String getSelect_month(){ Log.v("test","search的select: "+Select_month); return Select_month;}

    //登入後需要傳值到個人設定主頁，點擊個人資訊時需要用到
    public void setUser_email(String user_email){this.User_email = user_email;}
    public String getUser_email(){ return User_email;}

    //個人資訊填寫完後須將姓名傳到個人設定主頁
    public void setUser_name(String user_name){this.User_name = user_name;}
    public String getUser_name(){ return User_name;}

}
