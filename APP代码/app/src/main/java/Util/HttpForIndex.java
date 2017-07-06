package Util;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import Fragment_Market.Bean.SHIndexBean;
import Fragment_Market.Bean.SZIndexBean;

/**
 * Created by magic on 2017/4/5.
 */

public class HttpForIndex {

    public static SHIndexBean stcokBean = new SHIndexBean();
    public static SZIndexBean stcokBean1 = new SZIndexBean();


    public static SHIndexBean getIndexForNetWork(Context context, String Url) {

        try{
            //1.请求服务器获取新闻数据
            //获取一个url对象，通过url对象得到一个urlconnnection对象

            URL url = new URL(Url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //设置连接的方式和超时时间
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10*1000);
            //获取请求响应码
            int code = connection.getResponseCode();
            if(code == 200){
                //获取请求到的流信息
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"utf_8"));
                StringBuilder response = new StringBuilder();
                String line;;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                String string=response.toString();
                Log.i("Json数据", string);
                parseJSONWithJSONObject(context, string);





            }
//
//            //3.清楚数据库中旧的数据，将新的数据缓存到数据库中
//            new MarketDB(context).delete();
//            new MarketDB(context).saveNews(arrayList);



        }catch (Exception e) {
            e.printStackTrace();
        }
        return stcokBean;
    }
    public static SZIndexBean getSZIndexForNetWork1(Context context, String Url) {

        try{
            //1.请求服务器获取新闻数据
            //获取一个url对象，通过url对象得到一个urlconnnection对象

            URL url = new URL(Url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //设置连接的方式和超时时间
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10*1000);
            //获取请求响应码
            int code = connection.getResponseCode();
            if(code == 200){
                //获取请求到的流信息
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"utf_8"));
                StringBuilder response = new StringBuilder();
                String line;;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                String string=response.toString();
                Log.i("Json数据", string);
                parseJSONWithJSONObject1(context, string);





            }
//
//            //3.清楚数据库中旧的数据，将新的数据缓存到数据库中
//            new MarketDB(context).delete();
//            new MarketDB(context).saveNews(arrayList);



        }catch (Exception e) {
            e.printStackTrace();
        }
        return stcokBean1;
    }


    //    //从数据库中获取上次缓存的新闻数据做listview的展示
//    public  static java.util.ArrayList<StockBean> getAllNewsForDatabase(Context context) {
//
//        return new MarketDB(context).getNews();
//
//    }
    public static void parseJSONWithJSONObject(Context context, String jsonData) {

        JSONObject root_json= null;
        try {
            root_json = new JSONObject(jsonData);
            JSONArray jsonArray1 = root_json.getJSONArray("result");//获取root_json中的newss作为jsonArray对象
            String string=jsonArray1.toString();

            Log.i("result1",string);
            JSONObject jsonArray=jsonArray1.getJSONObject(0);
            String js=jsonArray.toString();
            Log.i("shuju",js);

            JSONObject news_json = jsonArray.getJSONObject("data");

            stcokBean.nowPric=news_json.getString("nowPri");
            stcokBean.changePrice=news_json.getString("increase");
            stcokBean.changePercent = news_json.getString("increPer");
            stcokBean.date = news_json.getString("date");



            JSONObject news_json1=jsonArray.getJSONObject("gopicture");
            stcokBean. minurl = news_json1.getString("minurl");
            stcokBean. dayurl = news_json1.getString("dayurl");
            stcokBean. weekurl = news_json1.getString("weekurl");
            stcokBean. monthurl = news_json1.getString("monthurl");

//            arrayList.add(stcokBean);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//将一个字符串封装成一个json对象。

    }
    public static void parseJSONWithJSONObject1(Context context, String jsonData) {

        JSONObject root_json= null;
        try {
            root_json = new JSONObject(jsonData);
            JSONArray jsonArray1 = root_json.getJSONArray("result");//获取root_json中的newss作为jsonArray对象
            String string=jsonArray1.toString();

            Log.i("result1",string);
            JSONObject jsonArray=jsonArray1.getJSONObject(0);
            String js=jsonArray.toString();
            Log.i("shuju",js);

            JSONObject news_json = jsonArray.getJSONObject("data");
            stcokBean1.nowPric=news_json.getString("nowPri");
            stcokBean1.changePrice=news_json.getString("increase");
            stcokBean1.changePercent = news_json.getString("increPer");
            stcokBean1.date = news_json.getString("date");





            JSONObject news_json1=jsonArray.getJSONObject("gopicture");
            stcokBean1. minurl = news_json1.getString("minurl");
            stcokBean1. dayurl = news_json1.getString("dayurl");
            stcokBean1. weekurl = news_json1.getString("weekurl");
            stcokBean1. monthurl = news_json1.getString("monthurl");

//            arrayList.add(stcokBean);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//将一个字符串封装成一个json对象。

    }
}


