package Fragments_News;

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
import java.util.ArrayList;

import Fragments_News.News_db.NewsDaoUtils;

/**
 * Created by magic on 2017/3/31.
 */
public class NewsUtils {

    public static String newsPath_url = "http://v.juhe.cn/toutiao/index?type=caijing&key=9918a2cfab25bd3bce959221be46e1c9";
    static ArrayList<NewsBean> arrayList = new ArrayList<NewsBean>();


    //封装新闻的数据到list中返回
    public static ArrayList<NewsBean> getAllNewsForNetWork(Context context) {

        try{
            //1.请求服务器获取新闻数据
            //获取一个url对象，通过url对象得到一个urlconnnection对象
            URL url = new URL(newsPath_url);
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

            //3.清楚数据库中旧的数据，将新的数据缓存到数据库中
            new NewsDaoUtils(context).delete();
            new NewsDaoUtils(context).saveNews(arrayList);


        }catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    //从数据库中获取上次缓存的新闻数据做listview的展示
    public  static ArrayList<NewsBean> getAllNewsForDatabase(Context context) {

        return new NewsDaoUtils(context).getNews();

    }
    public static void parseJSONWithJSONObject(Context context, String jsonData) {

        JSONObject root_json= null;
        try {
            root_json = new JSONObject(jsonData);
            JSONObject jsonArray1 = root_json.getJSONObject("result");//获取root_json中的newss作为jsonArray对象
            String string=jsonArray1.toString();

            Log.i("result",string);
            JSONArray jsonArray=jsonArray1.getJSONArray("data");
            for (int i = 0 ;i < jsonArray.length();i++){//循环遍历jsonArray
                JSONObject news_json = jsonArray.getJSONObject(i);//获取一条新闻的json

                NewsBean newsBean = new NewsBean();



                newsBean. des = news_json.getString("author_name");
                newsBean. title = news_json.getString("title");
                newsBean. news_url = news_json.getString("url");
                newsBean. icon_url = news_json.getString("thumbnail_pic_s");

                arrayList.add(newsBean);
            }} catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//将一个字符串封装成一个json对象。

    }
}
