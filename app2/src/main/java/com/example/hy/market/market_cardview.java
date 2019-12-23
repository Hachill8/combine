package com.example.hy.market;

import android.graphics.Bitmap;

public class market_cardview
{
    private int img,id;
    private String name,price;
    private Bitmap image;
    public market_cardview() { super(); }
    public market_cardview(int id,int img,String name,String price)
    {
        super();
        this.name = name;
        this.img = img;
        this.price = price;
    }

    public market_cardview(int id, Bitmap image, String name, String price)
    {
        super();
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public String getPrice(){
        return price;
    }

    public void setPrice(){
        this.price = price;
    }

    public String getName()
    {
        return name;
    }
    public void setName(){
        this.name = name;
    }
    public int getImg(){
        return img;
    }
    public void setImg(){
        this.img = img;
    }
    public int getId(){
        return id;
    }
    public void setId(){
        this.id = id;
    }

    public Bitmap getImage(){
        return image;
    }
    public void setImage(){
        this.image = image;
    }
}
