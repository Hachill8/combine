package com.example.hy.forum;

public class forum_post {
    private int id;
    private String title,shortdesc,time,heartnum,commentnum,image_string;
    private int userimg,heart_img;

    public forum_post(int id, String title, String shortdesc,String time,int heart_img,
                      String heartnum,String commentnum,String image_string,int userimg){
        this.id=id;
        this.title=title;
        this.shortdesc=shortdesc;
        this.time=time;
        this.image_string=image_string;
        this.heart_img=heart_img;
        this.heartnum=heartnum;
        this.commentnum=commentnum;
        this.userimg=userimg;

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public String getTime() { return time; }

    public int getHeart_img() {return heart_img; }

    public String getHeartnum() { return heartnum; }

    public String getCommentnumnum() { return commentnum; }

    public int getUserimg() { return userimg; }

    public String getImage_string() {
        return image_string;
    }
}
