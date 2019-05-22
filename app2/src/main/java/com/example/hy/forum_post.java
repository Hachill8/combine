package com.example.hy;

public class forum_post {
    private int id;
    private String title,shortdesc;
    private int image;

    public forum_post(int id, String title, String shortdesc, int image){
        this.id=id;
        this.title=title;
        this.shortdesc=shortdesc;
        this.image=image;

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

    public int getImage() {
        return image;
    }
}
