package com.example.hy.market;

import android.graphics.Bitmap;

public class market_p4_cardview
{
    private int id;
    private String name,price,sum,amount;
    private Bitmap image;
    public market_p4_cardview (){
        super();
    }

    public market_p4_cardview (int id, String name, String price, Bitmap image, String amount)
    {
        super();
        this.name = name;
        this.image = image;
        this.price = price;
        this.sum = sum;
        this.amount = amount;
        this.id = id;
    }

    public String getAmount(){
        return amount;
    }

    public void setAmount(String Amount){
        this.amount = Amount;
    }

    public String getPrice(){
        return price;
    }

    public void setPrice(){
        this.price = price;
    }

    public String getSum(){
        return sum;
    }

    public void setSum(){
        this.sum = sum;
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
