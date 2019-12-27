package com.example.hy.market;

import android.graphics.Bitmap;

public class market_order_info_cardview
{
    private int img,id;
    private String name,price,num;
    private Bitmap image;
    public market_order_info_cardview () { super(); }

    public market_order_info_cardview (int id,  String name, String price,String num,Bitmap image)
    {
        super();
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.num = num;
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
    public String getNum(){
        return num;
    }
    public void setNum(){
        this.num = num;
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
