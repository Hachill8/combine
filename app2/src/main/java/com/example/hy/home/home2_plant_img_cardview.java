package com.example.hy.home;

import android.graphics.Bitmap;
import android.media.Image;

public class home2_plant_img_cardview
{
    private int id;
    private String name;
    private int image;

    public home2_plant_img_cardview(){
        super();
    }

    public home2_plant_img_cardview(int id,String name,int image)
    {
        super();
        this.name = name;
        this.image = image;
        this.id = id;
    }


    public String getName(){
        return name;
    }

    public void setName(){
        this.name = name;
    }

    public int getImage(){
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
