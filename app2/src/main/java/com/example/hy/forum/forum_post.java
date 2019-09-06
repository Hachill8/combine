package com.example.hy.forum;

public class forum_post {
    private int id;
    private String title,shortdesc,time,heartnum,commentnum;
    private int image,userimg;

    public forum_post(int id, String title, String shortdesc,String time,
                      String heartnum,String commentnum,int image,int userimg){
        this.id=id;
        this.title=title;
        this.shortdesc=shortdesc;
        this.time=time;
        this.image=image;
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

    public String getHeartnum() { return heartnum; }

    public String getCommentnumnum() { return commentnum; }

    public int getUserimg() { return userimg; }

    public int getImage() {
        return image;
    }
}
