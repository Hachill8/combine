package com.example.hy.market;

public class market_cardview
{
    private int image,id;
    private String name,price;
    public market_cardview() { super(); }
    public market_cardview(int id,int image,String name,String price)
    {
        super();
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
