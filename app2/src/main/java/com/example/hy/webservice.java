package com.example.hy;

import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

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

    public static String Vegename_list(String s)
    {
        String SOAP_ACTION = "http://tempuri.org/vegename";          //命名空間+要用的函數名稱
        String METHOD_NAME = "vegename";   //函數名稱

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

    ////務農族的資料還未改和VS還未加
    public static String Insert_calendar(String date,String s,String message)
    {
        String SOAP_ACTION = "http://tempuri.org/insertcalendar";          //命名空間+要用的函數名稱
        String METHOD_NAME = "insertcalendar";   //函數名稱

        //必須用try catch包著
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            request.addProperty("time",date);
            request.addProperty("action",s);
            request.addProperty("note",message);


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
}
