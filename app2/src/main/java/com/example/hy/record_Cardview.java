package com.example.hy;


public class record_Cardview
{
    private int image,id;
    private String name;

    public record_Cardview(){
        super();
    }

    public record_Cardview(int id,String name,int image)
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
