package com.hzy.magic.stock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import Fragment_Market.Bean.StockBean;
import Fragment_Market.MarketAdapter;
import Util.HttpForHK;
import Util.StockAPI;

import static com.hzy.magic.stock.MyApplication.getContext;

/**
 * Created by magic on 2017/4/5.
 */

public class HKStcokActivity extends Activity implements AdapterView.OnItemClickListener{
    private ListView lv_news;




    private Handler handler = new Handler(){
        public void handleMessage(Message msg) {

            ArrayList<StockBean> allNews = (ArrayList<StockBean>) msg.obj;

            if(allNews != null && allNews .size()>0)
            {

                MarketAdapter newsAdapter = new MarketAdapter(getContext(), allNews);
                lv_news.setAdapter(newsAdapter);
            }

        };
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sh_stock_layout);
        lv_news= (ListView) findViewById(R.id.stock_listView);
//        //1.先去数据库中获取缓存的新闻数据展示到listview
//        ArrayList<StockBean> allnews_database = HttpForHK.getAllNewsForDatabase(getContext());
//
//        if(allnews_database !=null && allnews_database.size()>0){
//            //创建一个adapter设置给listview
//            MarketAdapter newsAdapter = new MarketAdapter(getContext(), allnews_database);
//            lv_news.setAdapter(newsAdapter);
//        }

        //2.通过网络获取服务器上的新闻数据用list封装 ,获取网络数据需要在子线程中做
        new Thread(new Runnable() {

            @Override
            public void run() {


                //请求网络数据
                ArrayList<StockBean> allNews = HttpForHK.getAllstockListForNetWork(getContext(), StockAPI.getHKAll(1));
                //通过handler将msg发送到主线程去更新Ui
                Message msg = Message.obtain();
                msg.obj = allNews;
                handler.sendMessage(msg);


            }
        }).start();


        //3.设置listview条目的点击事件
        lv_news.setOnItemClickListener(this);









    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        StockBean bean = (StockBean) parent.getItemAtPosition(position);

        String url = StockAPI.getHK(bean.getSymbol());

        Intent intent = new Intent(this,stock_detail.class);
        intent.setAction(Intent.ACTION_VIEW);
        intent.putExtra("url",url);
        startActivity(intent);
    }

}






