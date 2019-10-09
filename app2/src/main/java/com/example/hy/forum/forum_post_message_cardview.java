package com.example.hy.forum;

public class forum_post_message_cardview
{
    private int image,id;
    private String name,time,message,B_num;

    public forum_post_message_cardview(){
        super();
    }

    public forum_post_message_cardview(int id,String name,int image,String time,String message,String B_num)
    {
        super();
        this.name = name;
        this.image = image;
        this.id = id;
        this.B_num = B_num;
        this.time = time;
        this.message = message;
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

    public String getTime(){
        return time;
    }

    public void setTime(){
        this.time = time;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(){
        this.message = message;
    }

    public String getB_num(){
        return B_num;
    }

    public void setB_num(){
        this.B_num = B_num;
    }

}
