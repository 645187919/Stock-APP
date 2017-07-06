package com.hzy.magic.stock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ant.liao.GifView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import Fragment_Market.Bean.StockDetailBean;
import Util.HttpForDetail;

/**
 * Created by magic on 2017/4/4.
 */
//点击其中一只股票后的连接事件，根据情况实现相应的功能
public class stock_detail extends Activity implements View.OnClickListener {
    String mUrl;
    public static StockDetailBean sDBean;
    Button mi_bt,day_bt,week_bt,month_bt;
    public static String minurl,dayurl,weekurl,monthurl;


    GifView myGifView;

    private Handler handler = new Handler(){

        public void handleMessage(Message msg) {
            if(msg.obj.equals(sDBean)){
                minurl=sDBean.getMinurl();
                dayurl=sDBean.getDayurl();
                weekurl=sDBean.getWeekurl();
                monthurl=sDBean.getMonthurl();
                Log.i("uRl:",minurl);
                Log.i("dayurl",dayurl);

                setImageUrl(minurl);


            }
            else{
                InputStream bitmap = (InputStream) msg.obj;
                myGifView.setShowDimension(490,300);
                myGifView.setGifImage(bitmap);
            }



        };
    };







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_detail);
        myGifView= (GifView)findViewById(R.id.myGifView);
        mi_bt = (Button) findViewById(R.id.minurl);
        day_bt = (Button) findViewById(R.id.dayurl);
        week_bt = (Button) findViewById(R.id.weekurl);
        month_bt = (Button) findViewById(R.id.mouthurl);
        mi_bt.setOnClickListener(this);
        day_bt.setOnClickListener(this);
        week_bt.setOnClickListener(this);

        month_bt.setOnClickListener(this);
        Intent intent=getIntent();
        String url=intent.getStringExtra("url");
        Log.i("url",url);
        mUrl=url;
        new Thread(new Runnable() {
            @Override
            public void run() {
                sDBean=HttpForDetail.getAllstockListForNetWork(MyApplication.getContext(),mUrl);
                Message msg=Message.obtain();
                msg.obj=sDBean;
                handler.sendMessage(msg);

            }
        }).start();
//        StockDetailDB stockDetailDB=new StockDetailDB(this);
//        ArrayList<StockDetailBean> list= stockDetailDB.getNews();
//        StockDetailBean sDBean=list.get(0);








    }

    public void setImageUrl(final String url_str) {


        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    //鑾峰彇url瀵瑰簲鐨勫浘鐗囪祫婧愶紝bitmap
                    URL url = new URL(url_str);

                    HttpURLConnection openConnection = (HttpURLConnection) url.openConnection();

                    openConnection.setRequestMethod("GET");
                    openConnection.setConnectTimeout(10 * 1000);

                    int code = openConnection.getResponseCode();
                    if (code == 200) {
                        InputStream inputStream = openConnection.getInputStream();


                        Message msg = Message.obtain();
                        msg.obj = inputStream;
                        handler.sendMessage(msg);


                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.minurl:
                setImageUrl(minurl);

                break;
            case R.id.dayurl:
                setImageUrl(dayurl);
//                Log.i("dayurl","我点击了");
                break;
            case R.id.weekurl:
                setImageUrl(weekurl);
                break;
            case R.id.mouthurl:
                setImageUrl(monthurl);
                break;
            default:
                break;



        }





    }
}
