package com.example.hy;

import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.List;

public class webservice
{
    //以下字串必須完全正確，不能有空白!! (之前為了一個空白卡了兩小時...)
    private static final String NAMESPACE = "http://tempuri.org/" ;       //WebService預設的命名空間
    private static final String URL = "http://134.208.96.168/ws_test1/webservice.asmx";     //WebService的網址
    private static final String SOAP_ACTION = "http://tempuri.org/VegeInfo_WS";          //命名空間+要用的函數名稱
    private static final String METHOD_NAME = "VegeInfo_WS";   //函數名稱

    public static String VegeInfo_WS(String s)
    {
        String SOAP_ACTION = "http://tempuri.org/VegeInfo_WS";          //命名空間+要用的函數名稱
        String METHOD_NAME = "VegeInfo_WS";   //函數名稱
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("name",s);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);

            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            Log.v("test","object: "+object);
            // 獲取返回的結果
            String result = object.getProperty(0).toString();
            Log.v("test","result: "+result);
            return result;
        } catch (Exception e) {
            Log.v("test","e.toString(): "+e.toString());
            return e.toString();
        }
    }

    public static String VegeInfo_Goods(String s)
    {
        String SOAP_ACTION = "http://tempuri.org/VegeInfo_goods";          //命名空間+要用的函數名稱
        String METHOD_NAME = "VegeInfo_goods";   //函數名稱
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("vege",s);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);

            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            Log.v("test","object: "+object);
            // 獲取返回的結果
            String result = object.getProperty(0).toString();
            Log.v("test","result: "+result);
            return result;
        } catch (Exception e) {
            Log.v("test","e.toString(): "+e.toString());
            return e.toString();
        }
    }

    public static String Vegename_list()
    {
        String SOAP_ACTION = "http://tempuri.org/vegename";          //命名空間+要用的函數名稱
        String METHOD_NAME = "vegename";   //函數名稱

        //必須用try catch包著
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("vege","s");

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);
            Log.v("test","有進WS");
            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            // 獲取返回的結果
            String result = object.getProperty(0).toString();

            Log.v("test","search WS的result: "+result);
            return result;
        } catch (Exception e) {
            return e.toString();
        }
    }

    public static String Login_Getgmail(String s)
    {
        String SOAP_ACTION = "http://tempuri.org/Login_gmail";          //命名空間+要用的函數名稱
        String METHOD_NAME = "Login_gmail";   //函數名稱

        //必須用try catch包著
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("name",s);
            Log.v("test","RRRRRRRRRR"+s);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);
            Log.v("test","有進WS");
            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            // 獲取返回的結果
            String result = object.getProperty(0).toString();

            Log.v("test","ws的result: "+result);
            return result;
        } catch (Exception e) {
            return e.toString();
        }
    }

    public static String downImage(String imagebyte)
    {
        String SOAP_ACTION = "http://tempuri.org/imagedown";          //命名空間+要用的函數名稱
        String METHOD_NAME = "imagedown";   //函數名稱

        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("in_image", imagebyte);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);
            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            Log.v("test","obj"+object.toString());
            // 獲取返回的結果
            String result = object.getProperty(0).toString();
            Log.v("test","result: "+result);
            return result;
        }
        catch (Exception e)
        {

            return ("失敗: " + e.toString());
        }
    }

    public static String Search_select(String s)
    {
        String SOAP_ACTION = "http://tempuri.org/search_select";          //命名空間+要用的函數名稱
        String METHOD_NAME = "search_select";   //函數名稱
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("s",s);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);

            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            Log.v("test","object: "+object);
            // 獲取返回的結果
            String result = object.getProperty(0).toString();
            Log.v("test","result: "+result);
            return result;
        } catch (Exception e) {
            Log.v("test","e.toString(): "+e.toString());
            return e.toString();
        }
    }

    public static String Goodname_list(String s)
    {
        String SOAP_ACTION = "http://tempuri.org/goodname";          //命名空間+要用的函數名稱
        String METHOD_NAME = "goodname";   //函數名稱

        //必須用try catch包著
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("s","s");

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);
            Log.v("test","有進WS");
            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            // 獲取返回的結果
            String result = object.getProperty(0).toString();

            Log.v("test","ws的result: "+result);
            return result;
        } catch (Exception e) {
            return e.toString();
        }
    }

    public static String GoodInfo_WS(String s)
    {
        String SOAP_ACTION = "http://tempuri.org/GoodInfo_WS";          //命名空間+要用的函數名稱
        String METHOD_NAME = "GoodInfo_WS";   //函數名稱
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("name",s);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);

            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            Log.v("test","object: "+object);
            // 獲取返回的結果
            String result = object.getProperty(0).toString();
            Log.v("test","result: "+result);
            return result;
        } catch (Exception e) {
            Log.v("test","e.toString(): "+e.toString());
            return e.toString();
        }
    }

    public static String Good_in_cart(String s)
    {
        String SOAP_ACTION = "http://tempuri.org/Good_in_cart";          //命名空間+要用的函數名稱
        String METHOD_NAME = "Good_in_cart";   //函數名稱
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("info",s);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);

            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            Log.v("test","object: "+object);
            // 獲取返回的結果
            String result = object.getProperty(0).toString();
            Log.v("test","result: "+result);
            return result;
        } catch (Exception e) {
            Log.v("test","e.toString(): "+e.toString());
            return e.toString();
        }
    }

    public static String Cart_cardview(String s)
    {
        String SOAP_ACTION = "http://tempuri.org/Cart_cardview";          //命名空間+要用的函數名稱
        String METHOD_NAME = "Cart_cardview";   //函數名稱
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("name",s);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);

            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            Log.v("test","object: "+object);
            // 獲取返回的結果
            String result = object.getProperty(0).toString();
            Log.v("test","result: "+result);
            return result;
        } catch (Exception e) {
            Log.v("test","e.toString(): "+e.toString());
            return e.toString();
        }
    }

    public static String Cart_cardview_dele(String s)
    {
        String SOAP_ACTION = "http://tempuri.org/Cart_cardview_dele";          //命名空間+要用的函數名稱
        String METHOD_NAME = "Cart_cardview_dele";   //函數名稱
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("ID",s);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);

            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            Log.v("test","object: "+object);
            // 獲取返回的結果
            String result = object.getProperty(0).toString();
            Log.v("test","result: "+result);
            return result;
        } catch (Exception e) {
            Log.v("test","e.toString(): "+e.toString());
            return e.toString();
        }
    }

    public static String Insert_vege(String vege,String gmail)
    {
        String SOAP_ACTION = "http://tempuri.org/Insert_vege";          //命名空間+要用的函數名稱
        String METHOD_NAME = "Insert_vege";   //函數名稱

        //必須用try catch包著
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            request.addProperty("vege",vege);
            request.addProperty("gmail",gmail);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);
            Log.v("test","有進WS");
            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            // 獲取返回的結果
            String result = object.getProperty(0).toString();

            Log.v("test1","ws的result: "+result);
            return result;
        } catch (Exception e) {
            return e.toString();
        }
    }

    public static String insert_cal_vege(String vege,String date,String s,String message,String url,String gmail)
    {
        String SOAP_ACTION = "http://tempuri.org/insert_cal_vege";          //命名空間+要用的函數名稱
        String METHOD_NAME = "insert_cal_vege";   //函數名稱

        //必須用try catch包著
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("vege",vege);
            request.addProperty("day",date);
            request.addProperty("action",s);
            request.addProperty("note",message);
            request.addProperty("picture",url);
            request.addProperty("gmail",gmail);


            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);
            Log.v("test","有進WS");
            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            // 獲取返回的結果
            String result = object.getProperty(0).toString();

            Log.v("test1","ws的result: "+result);
            return result;
        } catch (Exception e) {
            return e.toString();
        }
    }

    public static String Select_keep_post(String post_name,String post_title,String gmail)
    {
        String SOAP_ACTION = "http://tempuri.org/Select_keep_post";          //命名空間+要用的函數名稱
        String METHOD_NAME = "Select_keep_post";   //函數名稱
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("post_name",post_name);
            request.addProperty("post_title",post_title);
            request.addProperty("gmail",gmail);


            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);

            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            Log.v("test","object: "+object);
            // 獲取返回的結果
            String result = object.getProperty(0).toString();
            Log.v("test","result: "+result);
            return result;
        } catch (Exception e) {
            Log.v("test","e.toString(): "+e.toString());
            return e.toString();
        }
    }

    public static String Insert_keep_post(String post_name,String post_title,String likeornot,String gmail)
    {
        String SOAP_ACTION = "http://tempuri.org/Insert_keep_post";          //命名空間+要用的函數名稱
        String METHOD_NAME = "Insert_keep_post";   //函數名稱

        //必須用try catch包著
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("post_name",post_name);
            request.addProperty("post_title",post_title);
            request.addProperty("likeornot",likeornot);
            request.addProperty("gmail",gmail);


            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);
            Log.v("test","有進WS");
            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            // 獲取返回的結果
            String result = object.getProperty(0).toString();

            Log.v("test1","ws的result: "+result);
            return result;
        } catch (Exception e) {
            return e.toString();
        }
    }

    public static String select_cal(String gmail,String vege_name,String date)
{
    String SOAP_ACTION = "http://tempuri.org/select_cal";          //命名空間+要用的函數名稱
    String METHOD_NAME = "select_cal";   //函數名稱

    //必須用try catch包著
    try {
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        request.addProperty("gmail",gmail);
        request.addProperty("vege_name",vege_name);
        request.addProperty("vege_time",date);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.bodyOut = request;
        envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
        envelope.setOutputSoapObject(request);
        envelope.encodingStyle = "utf-8";
        HttpTransportSE ht = new HttpTransportSE(URL);
        ht.call(SOAP_ACTION, envelope);

        // 獲取回傳數據
        SoapObject object = (SoapObject) envelope.bodyIn;
        // 獲取返回的結果
        String result = object.getProperty(0).toString();
        return result;
    } catch (Exception e) {
        return e.toString();
    }
}

    public static String Update_cal_vege(String vege,String date,String s,String message,String url,String gmail)
    {
        String SOAP_ACTION = "http://tempuri.org/Update_cal_vege";          //命名空間+要用的函數名稱
        String METHOD_NAME = "Update_cal_vege";   //函數名稱

        //必須用try catch包著
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("vege",vege);
            request.addProperty("day",date);
            request.addProperty("action",s);
            request.addProperty("picture",url);
            request.addProperty("note",message);
            request.addProperty("gmail",gmail);


            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);
            Log.v("test","有進WS");
            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            // 獲取返回的結果
            String result = object.getProperty(0).toString();

            Log.v("test1","ws的result: "+result);
            return result;
        } catch (Exception e) {
            return e.toString();
        }
    }

    public static String Delete_cal_vege(String date,String vege,String gmail)
    {
        String SOAP_ACTION = "http://tempuri.org/Delete_cal_vege";          //命名空間+要用的函數名稱
        String METHOD_NAME = "Delete_cal_vege";   //函數名稱

        //必須用try catch包著
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            request.addProperty("time",date);
            request.addProperty("vege",vege);
            request.addProperty("gmail",gmail);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);
            Log.v("test","有進WS");
            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            // 獲取返回的結果
            String result = object.getProperty(0).toString();

            Log.v("test1","ws的result: "+result);
            return result;
        } catch (Exception e) {
            return e.toString();
        }
    }

    public static String Delete_keep_post(String post_name,String post_title,String gmail)
    {
        String SOAP_ACTION = "http://tempuri.org/Delete_keep_post";          //命名空間+要用的函數名稱
        String METHOD_NAME = "Delete_keep_post";   //函數名稱

        //必須用try catch包著
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            request.addProperty("post_name",post_name);
            request.addProperty("post_title",post_title);
            request.addProperty("gmail",gmail);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);
            Log.v("test","有進WS");
            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            // 獲取返回的結果
            String result = object.getProperty(0).toString();

            Log.v("test1","ws的result: "+result);
            return result;
        } catch (Exception e) {
            return e.toString();
        }
    }

    public static String insert_user_info(String name,String phone,String email,String addr,String select_age,String select_gender,String select_expri)
    {
        String SOAP_ACTION = "http://tempuri.org/insert_user_info";          //命名空間+要用的函數名稱
        String METHOD_NAME = "insert_user_info";   //函數名稱

        //必須用try catch包著
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            request.addProperty("name",name);
            request.addProperty("phone",phone);
            request.addProperty("email",email);
            request.addProperty("addr",addr);
            request.addProperty("age",select_age);
            request.addProperty("gender",select_gender);
            request.addProperty("expri",select_expri);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);
            Log.v("test","有進WS");
            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            // 獲取返回的結果
            String result = object.getProperty(0).toString();

            Log.v("test1","ws的result: "+result);
            return result;
        } catch (Exception e) {
            return e.toString();
        }
    }

    public static String Update_user_info(String email,String name,String phone,String addr,String age,String gender,String expri)
    {
        String SOAP_ACTION = "http://tempuri.org/Update_user_info";          //命名空間+要用的函數名稱
        String METHOD_NAME = "Update_user_info";   //函數名稱

        //必須用try catch包著
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("UPmail",email);
            request.addProperty("UPname",name);
            request.addProperty("UPphone",phone);
            request.addProperty("UPaddr",addr);
            request.addProperty("UPage",age);
            request.addProperty("UPgender",gender);
            request.addProperty("UPexpri",expri);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);
            Log.v("test","有進WS");
            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            // 獲取返回的結果
            String result = object.getProperty(0).toString();

            Log.v("test1","ws的result: "+result);
            return result;
        } catch (Exception e) {
            return e.toString();
        }
    }

    public static String Select_user_info(String user_email)
    {
        String SOAP_ACTION = "http://tempuri.org/Select_user_info";          //命名空間+要用的函數名稱
        String METHOD_NAME = "Select_user_info";   //函數名稱

        //必須用try catch包著
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("UPmail",user_email);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);
            Log.v("test","有進WS");
            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            // 獲取返回的結果
            String result = object.getProperty(0).toString();

            Log.v("test1","ws的result: "+result);
            return result;
        } catch (Exception e) {
            return e.toString();
        }
    }

    public static String Search_forum_list()
    {
        String SOAP_ACTION = "http://tempuri.org/post_name_list";          //命名空間+要用的函數名稱
        String METHOD_NAME = "post_name_list";   //函數名稱

        //必須用try catch包著
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("post_name","");

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);
            Log.v("test","有進WS");
            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            // 獲取返回的結果
            String result = object.getProperty(0).toString();

            Log.v("test","ws的result: "+result);
            return result;
        } catch (Exception e) {
            return e.toString();
        }
    }

    public static String forum_cardview(boolean sort)
    {
        String SOAP_ACTION = "http://tempuri.org/post_cardview";          //命名空間+要用的函數名稱
        String METHOD_NAME = "post_cardview";   //函數名稱

        //必須用try catch包著
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("sort",sort);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);
            Log.v("test","有進WS");
            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            // 獲取返回的結果
            String result = object.getProperty(0).toString();

            Log.v("test","ws的result: "+result);
            return result;
        } catch (Exception e) {
            return e.toString();
        }
    }

    public static String Select_all_like_post(String gmail)
    {
        String SOAP_ACTION = "http://tempuri.org/Select_all_like_post";          //命名空間+要用的函數名稱
        String METHOD_NAME = "Select_all_like_post";   //函數名稱

        //必須用try catch包著
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("gmail",gmail);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);
            Log.v("test","有進WS");
            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            // 獲取返回的結果
            String result = object.getProperty(0).toString();

            Log.v("test","ws的result: "+result);
            return result;
        } catch (Exception e) {
            return e.toString();
        }
    }

    public static String user_like_forum_cardview(String alllikepost)
    {
        String SOAP_ACTION = "http://tempuri.org/user_like_forum_cardview";          //命名空間+要用的函數名稱
        String METHOD_NAME = "user_like_forum_cardview";   //函數名稱

        //必須用try catch包著
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("alllikepost",alllikepost);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);
            Log.v("test","有進WS");
            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            // 獲取返回的結果
            String result = object.getProperty(0).toString();

            Log.v("test","ws的result: "+result);
            return result;
        } catch (Exception e) {
            return e.toString();
        }
    }

    public static String user_post_cardview(String gmail)
    {
        String SOAP_ACTION = "http://tempuri.org/user_post_cardview";          //命名空間+要用的函數名稱
        String METHOD_NAME = "user_post_cardview";   //函數名稱

        //必須用try catch包著
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("gmail",gmail);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);
            Log.v("test","有進WS");
            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            // 獲取返回的結果
            String result = object.getProperty(0).toString();

            Log.v("test","ws的result: "+result);
            return result;
        } catch (Exception e) {
            return e.toString();
        }
    }

    public static String Select_like_myself_post(String gmail)
    {
        String SOAP_ACTION = "http://tempuri.org/Select_like_myself_post";          //命名空間+要用的函數名稱
        String METHOD_NAME = "Select_like_myself_post";   //函數名稱

        //必須用try catch包著
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("post_name",gmail);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);
            Log.v("test","有進WS");
            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            // 獲取返回的結果
            String result = object.getProperty(0).toString();

            Log.v("test","ws的result: "+result);
            return result;
        } catch (Exception e) {
            return e.toString();
        }
    }

    public static String Select_user_vege(String user_email)
    {
        String SOAP_ACTION = "http://tempuri.org/Select_user_vege";          //命名空間+要用的函數名稱
        String METHOD_NAME = "Select_user_vege";   //函數名稱

        //必須用try catch包著
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("gmail",user_email);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);
            Log.v("test","有進WS");
            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            // 獲取返回的結果
            String result = object.getProperty(0).toString();

            Log.v("test1","ws的result: "+result);
            return result;
        } catch (Exception e) {
            return e.toString();
        }
    }

    public static String Add_post_test(String title, String content,String user)
    {
        String SOAP_ACTION = "http://tempuri.org/Add_post_test";  //命名空間+要用的函數名稱
        String METHOD_NAME = "Add_post_test";   //函數名稱
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("post_title",title);
            request.addProperty("post_content",content);
            request.addProperty("user",user);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);

            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            Log.v("test","object: "+object);
            // 獲取返回的結果
            String result = object.getProperty(0).toString();
            Log.v("test","result: "+result);
            return result;
        }
        catch (Exception e) {
            Log.v("test","e.toString(): "+e.toString());
            return e.toString();
        }
    }

    public static String Add_message(String m_content)
    {
        String SOAP_ACTION = "http://tempuri.org/Add_message";  //命名空間+要用的函數名稱
        String METHOD_NAME = "Add_message";   //函數名稱
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("add_message",m_content);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);

            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            Log.v("test","object: "+object);
            // 獲取返回的結果
            String result = object.getProperty(0).toString();
            Log.v("test","result: "+result);
            return result;
        }
        catch (Exception e) {
            Log.v("test","e.toString(): "+e.toString());
            return e.toString();
        }
    }

    public static String user_img_down(String account)
    {
        String SOAP_ACTION = "http://tempuri.org/user_img_down";          //命名空間+要用的函數名稱
        String METHOD_NAME = "user_img_down";   //函數名稱
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("link","");
            request.addProperty("account",account);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);

            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            Log.v("test","object: "+object);
            // 獲取返回的結果
            String result = object.getProperty(0).toString();
            Log.v("test","result: "+result);
            return result;
        } catch (Exception e) {
            Log.v("test","e.toString(): "+e.toString());
            return e.toString();
        }
    }

    public static String user_img_update(String link,String account)
    {
        String SOAP_ACTION = "http://tempuri.org/user_img_update";          //命名空間+要用的函數名稱
        String METHOD_NAME = "user_img_update";   //函數名稱
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("link",link);
            request.addProperty("account",account);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);

            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            Log.v("test","object: "+object);
            // 獲取返回的結果
            String result = object.getProperty(0).toString();
            Log.v("test","result: "+result);
            return result;
        } catch (Exception e) {
            Log.v("test","e.toString(): "+e.toString());
            return e.toString();
        }
    }

    public static String Record_list()
    {
        String SOAP_ACTION = "http://tempuri.org/record_list";          //命名空間+要用的函數名稱
        String METHOD_NAME = "record_list";   //函數名稱

        //必須用try catch包著
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("record_list_string","s");

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);
            Log.v("test","有進WS");
            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            // 獲取返回的結果
            String result = object.getProperty(0).toString();

            Log.v("test","search WS的result: "+result);
            return result;
        } catch (Exception e) {
            return e.toString();
        }
    }

    public static String Record_Select_month(String month)
    {
        String SOAP_ACTION = "http://tempuri.org/record_select";          //命名空間+要用的函數名稱
        String METHOD_NAME = "record_select";   //函數名稱

        //必須用try catch包著
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("month",month);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);
            Log.v("test","有進WS");
            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            // 獲取返回的結果
            String result = object.getProperty(0).toString();

            Log.v("test","search WS的result: "+result);
            return result;
        } catch (Exception e) {
            return e.toString();
        }
    }

    public static String forum_post_view(int id)
    {
        String SOAP_ACTION = "http://tempuri.org/forum_post_view";          //命名空間+要用的函數名稱
        String METHOD_NAME = "forum_post_view";   //函數名稱
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("id",id);


            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);

            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            Log.v("test","object: "+object);
            // 獲取返回的結果
            String result = object.getProperty(0).toString();
            Log.v("test","result: "+result);
            return result;
        } catch (Exception e) {
            Log.v("test","e.toString(): "+e.toString());
            return e.toString();
        }
    }

    public static String Add_user_like(int id,String gmail)
    {
        String SOAP_ACTION = "http://tempuri.org/Add_user_like";          //命名空間+要用的函數名稱
        String METHOD_NAME = "Add_user_like";   //函數名稱
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("post_id",id);
            request.addProperty("gmail",gmail);


            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);

            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            Log.v("test","object: "+object);
            // 獲取返回的結果
            String result = object.getProperty(0).toString();
            Log.v("test","result: "+result);
            return result;
        } catch (Exception e) {
            Log.v("test","e.toString(): "+e.toString());
            return e.toString();
        }
    }

    public static String Delete_user_like(int id,String gmail)
    {
        String SOAP_ACTION = "http://tempuri.org/Delete_user_like";          //命名空間+要用的函數名稱
        String METHOD_NAME = "Delete_user_like";   //函數名稱
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("post_id",id);
            request.addProperty("gmail",gmail);


            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);

            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            Log.v("test","object: "+object);
            // 獲取返回的結果
            String result = object.getProperty(0).toString();
            Log.v("test","result: "+result);
            return result;
        } catch (Exception e) {
            Log.v("test","e.toString(): "+e.toString());
            return e.toString();
        }
    }

    public static String Update_post_like(String post_name,String post_title,String post_like_value)
    {
        String SOAP_ACTION = "http://tempuri.org/Update_post_like";          //命名空間+要用的函數名稱
        String METHOD_NAME = "Update_post_like";   //函數名稱
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("post_name",post_name);
            request.addProperty("post_title",post_title);
            request.addProperty("post_like",post_like_value);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);

            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            Log.v("test","object: "+object);
            // 獲取返回的結果
            String result = object.getProperty(0).toString();
            Log.v("test","result: "+result);
            return result;
        } catch (Exception e) {
            Log.v("test","e.toString(): "+e.toString());
            return e.toString();
        }
    }


    ///對應資料庫的變數未寫好
    public static String Insert_custom_vege(String vegeornot,String url,String vege_name,String edit_step,String edit_container,String edit_soil,String edit_place,
                                                String edit_water,String edit_fertilizer,String edit_bug,String edit_harvest,String gmail)
    {
        String SOAP_ACTION = "http://tempuri.org/Insert_custom_vege";          //命名空間+要用的函數名稱
        String METHOD_NAME = "Insert_custom_vege";   //函數名稱
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("vegeornotvege",vegeornot);
            request.addProperty("url",url);
            request.addProperty("vege_name",vege_name);
            request.addProperty("edit_step",edit_step);
            request.addProperty("edit_container",edit_container);
            request.addProperty("edit_soil",edit_soil);
            request.addProperty("edit_place",edit_place);
            request.addProperty("edit_water",edit_water);
            request.addProperty("edit_fertilizer",edit_fertilizer);
            request.addProperty("edit_bug",edit_bug);
            request.addProperty("edit_harvest",edit_harvest);
            request.addProperty("gmail",gmail);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);

            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            Log.v("test","object: "+object);
            // 獲取返回的結果
            String result = object.getProperty(0).toString();
            Log.v("test","result: "+result);
            return result;
        } catch (Exception e) {
            Log.v("test","e.toString(): "+e.toString());
            return e.toString();
        }
    }
    public static String Select_like_vege(String vegename,String gmail)
    {
        String SOAP_ACTION = "http://tempuri.org/Select_like_vege";          //命名空間+要用的函數名稱
        String METHOD_NAME = "Select_like_vege";   //函數名稱
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("vegename",vegename);
            request.addProperty("gmail",gmail);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);

            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            Log.v("test","object: "+object);
            // 獲取返回的結果
            String result = object.getProperty(0).toString();
            Log.v("test","result: "+result);
            return result;
        } catch (Exception e) {
            Log.v("test","e.toString(): "+e.toString());
            return e.toString();
        }
    }

    public static String Select_user_like_vege(String alllikevege)
    {
        String SOAP_ACTION = "http://tempuri.org/Select_user_like_vege";          //命名空間+要用的函數名稱
        String METHOD_NAME = "Select_user_like_vege";   //函數名稱
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("alllikevege",alllikevege);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);

            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            Log.v("test","object: "+object);
            // 獲取返回的結果
            String result = object.getProperty(0).toString();
            Log.v("test","result: "+result);
            return result;
        } catch (Exception e) {
            Log.v("test","e.toString(): "+e.toString());
            return e.toString();
        }
    }

    public static String Select_all_like_vege(String gmail)
    {
        String SOAP_ACTION = "http://tempuri.org/Select_all_like_vege";          //命名空間+要用的函數名稱
        String METHOD_NAME = "Select_all_like_vege";   //函數名稱
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("gmail",gmail);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);

            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            Log.v("test","object: "+object);
            // 獲取返回的結果
            String result = object.getProperty(0).toString();
            Log.v("test","result: "+result);
            return result;
        } catch (Exception e) {
            Log.v("test","e.toString(): "+e.toString());
            return e.toString();
        }
    }
    public static String Insert_like_vege(String likevege,String likeornot,String gmail)
    {
        String SOAP_ACTION = "http://tempuri.org/Insert_like_vege";          //命名空間+要用的函數名稱
        String METHOD_NAME = "Insert_like_vege";   //函數名稱
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("likevege",likevege);
            request.addProperty("likeornot",likeornot);
            request.addProperty("gmail",gmail);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);

            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            Log.v("test","object: "+object);
            // 獲取返回的結果
            String result = object.getProperty(0).toString();
            Log.v("test","result: "+result);
            return result;
        } catch (Exception e) {
            Log.v("test","e.toString(): "+e.toString());
            return e.toString();
        }
    }
    public static String Delete_like_vege(String vegename,String gmail)
    {
        String SOAP_ACTION = "http://tempuri.org/Delete_like_vege";          //命名空間+要用的函數名稱
        String METHOD_NAME = "Delete_like_vege";   //函數名稱
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("vegename",vegename);
            request.addProperty("gmail",gmail);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);

            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            Log.v("test","object: "+object);
            // 獲取返回的結果
            String result = object.getProperty(0).toString();
            Log.v("test","result: "+result);
            return result;
        } catch (Exception e) {
            Log.v("test","e.toString(): "+e.toString());
            return e.toString();
        }
    }

    public static String choose_calendar_home_list()
    {
        String SOAP_ACTION = "http://tempuri.org/choose_calendar_home";          //命名空間+要用的函數名稱
        String METHOD_NAME = "choose_calendar_home";   //函數名稱

        //必須用try catch包著
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("allvege","");

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);
            Log.v("test","有進WS");
            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            // 獲取返回的結果
            String result = object.getProperty(0).toString();

            Log.v("test","search WS的result: "+result);
            return result;
        } catch (Exception e) {
            return e.toString();
        }
    }

    public static String choose_calendar_info_cardview(String gmail,String number,String vege)
    {
        String SOAP_ACTION = "http://tempuri.org/choose_calendar_cardview";          //命名空間+要用的函數名稱
        String METHOD_NAME = "choose_calendar_cardview";   //函數名稱

        //必須用try catch包著
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("gmail",gmail);
            request.addProperty("number",number);
            request.addProperty("vege",vege);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);
            Log.v("test","有進WS");
            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            // 獲取返回的結果
            String result = object.getProperty(0).toString();

            Log.v("test","search WS的result: "+result);
            return result;
        } catch (Exception e) {
            return e.toString();
        }
    }
    public static String select_cal_firstday(String gmail,String number,String vege)
    {
        String SOAP_ACTION = "http://tempuri.org/select_cal_firstday";          //命名空間+要用的函數名稱
        String METHOD_NAME = "select_cal_firstday";   //函數名稱

        //必須用try catch包著
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("gmail",gmail);
            request.addProperty("number",number);
            request.addProperty("vege",vege);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.dotNet = true;//若WS有輸入參數必須要加這一行否則WS沒反應
            envelope.setOutputSoapObject(request);
            envelope.encodingStyle = "utf-8";
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION, envelope);
            Log.v("test","有進WS");
            // 獲取回傳數據
            SoapObject object = (SoapObject) envelope.bodyIn;
            // 獲取返回的結果
            String result = object.getProperty(0).toString();

            Log.v("test","search WS的result: "+result);
            return result;
        } catch (Exception e) {
            return e.toString();
        }
    }

}
