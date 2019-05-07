package com.example.hy;

public class record_Information_Cardview
{
    private String period;
    private String date;
    private String tx1;
    private String tx2;
    private String tx3;
    private int image;

    public record_Information_Cardview(){
        super();
    }

    public record_Information_Cardview(String period,String date,String tx1,String tx2,String tx3,int image)
    {
        super();
        this.period = period;
        this.date = date;
        this.tx1 = tx1;
        this.tx2 = tx2;
        this.tx3 = tx3;
        this.image = image;
    }

    public String getPeriod(){
        return period;
    }

    public void setPeriod(){
        this.period = period;
    }

    public String getDate(){
        return date;
    }

    public void setDate(){
        this.date = date;
    }

    public String getTx1(){
        return tx1;
    }

    public void setTx1(){
        this.tx1 = tx1;
    }

    public String getTx2(){
        return tx2;
    }

    public void setTx2(){
        this.tx2 = tx2;
    }

    public String getTx3(){
        return tx3;
    }

    public void setTx3(){
        this.tx3 = tx3;
    }

    public int getImage(){
        return image;
    }

    public void setImage(){
        this.image = image;
    }
}
