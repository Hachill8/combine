package com.example.hy.record;


import android.graphics.Bitmap;

public class record_Cardview
{
    private int id;
    private String name;
    private Bitmap image;

    public record_Cardview(){
        super();
    }

    public record_Cardview(int id,String name,Bitmap image)
    {
        super();
        this.name = name;
        this.image = image;
    }

    public String getName(){
        return name;
    }

    public void setName(){
        this.name = name;
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
