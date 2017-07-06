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

import Fragment_Market.Bean.StockDetailBean;

/**
 * Created by magic on 2017/4/4.
 */

public class HttpForDetail {
//    static ArrayList<StockDetailBean> arrayList = new ArrayList<StockDetailBean>();
    public static StockDetailBean stcokBean = new StockDetailBean();

    public static StockDetailBean getAllstockListForNetWork(Context context, String Url) {

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

//            //3.清楚数据库中旧的数据，将新的数据缓存到数据库中
//            new StockDetailDB(context).delete();
//            new StockDetailDB(context).saveNews(stcokBean);


        }catch (Exception e) {
            e.printStackTrace();
        }
        return stcokBean;
    }

    //从数据库中获取上次缓存的新闻数据做listview的展示
//    public  static StockDetailBean getAllNewsForDatabase(Context context) {
//
//        return new StockDetailDB(context).getNews();
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
            JSONObject news_json1=jsonArray.getJSONObject("gopicture");


            stcokBean.gid=news_json.getString("gid");



            stcokBean. minurl = news_json1.getString("minurl");
            stcokBean. name = news_json.getString("name");
            stcokBean. dayurl = news_json1.getString("dayurl");
            stcokBean. weekurl = news_json1.getString("weekurl");
            stcokBean. monthurl = news_json1.getString("monthurl");

//            arrayList.add(stcokBean);
            } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//将一个字符串封装成一个json对象。

    }
}



