package com.example.hy.calendar;

import android.graphics.Bitmap;

public class choose_calendar_cardview
{
    private int id;
    private String day,time,message;
    private Bitmap image;

    public choose_calendar_cardview(){
        super();
    }

    public choose_calendar_cardview(int id,String day,String time,String message,Bitmap image)
    {
        super();
        this.day = day;
        this.image = image;
        this.time = time;
        this.message = message;
    }

    public String getTime(){
        return time;
    }

    public void setTime(){
        this.time = time;
    }

    public String getDay(){
        return day;
    }

    public void setDay(){
        this.day = day;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(){
        this.message = message;
    }

    public Bitmap getImage(){
        return image;
    }

    public void setImage(){
        this.image = image;
    }

    public int getId(){
        return id;
    }

    public void setId(){
        this.id = id;
    }


}
