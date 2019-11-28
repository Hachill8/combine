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
    private String User_name="WHY";
    private String User_phone="WHY";
    private String User_addr="WHY";
    private String User_age="WHY!!!!!!!!!!!";
    private String User_gender="WHY";
    private String User_expri="WHY";
    private String Search_forum_string="無";

    //修改 變數字串
    public void setWord(String word){
        this.Word = word;
    }
    //顯示 變數字串
    public String getWord() {
        return Word;
    }

    public void setSearch_forum_string(String search_forum_string){this.Search_forum_string = search_forum_string;}
    public String getSearch_forum_string(){ return Search_forum_string;}

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

    public void setUser_name(String user_name){this.User_name = user_name;}
    public String getUser_name(){ return User_name;}

    public void setUser_phone(String user_phone){this.User_phone = user_phone;}
    public String getUser_phone(){ return User_phone;}

    public void setUser_addr(String user_addr){this.User_addr = user_addr;}
    public String getUser_addr(){ return User_addr;}

    public void setUser_age(String user_age){this.User_age = user_age;}
    public String getUser_age(){ return User_age;}

    public void setUser_gender(String user_gender){this.User_gender = user_gender;}
    public String getUser_gender(){ return User_gender;}

    public void setUser_expri(String user_expri){this.User_expri = user_expri;}
    public String getUser_expri(){ return User_expri;}

}
