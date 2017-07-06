package Fragments_News;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hzy.magic.stock.R;

import java.util.ArrayList;

/**
 * Created by magic on 2017/3/31.
 */
public class NewsFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ListView lv_news;




    private Handler handler = new Handler(){
        public void handleMessage(Message msg) {

            ArrayList<NewsBean> allNews = (ArrayList<NewsBean>) msg.obj;

            if(allNews != null && allNews .size()>0)
            {

                NewsAdapter newsAdapter = new NewsAdapter(getContext(), allNews);
                lv_news.setAdapter(newsAdapter);
            }

        };
    };





    @Override
    public void onAttach(Context context) {
        // TODO Auto-generated method stub
        super.onAttach(context);


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view= inflater.inflate(R.layout.tab04, container, false);
        lv_news=(ListView) view.findViewById(R.id.weiqiu_listView);
        //1.先去数据库中获取缓存的新闻数据展示到listview
        ArrayList<NewsBean> allnews_database = NewsUtils.getAllNewsForDatabase(getContext());

        if(allnews_database !=null && allnews_database.size()>0){
            //创建一个adapter设置给listview
            NewsAdapter newsAdapter = new NewsAdapter(getContext(), allnews_database);
            lv_news.setAdapter(newsAdapter);
        }

        //2.通过网络获取服务器上的新闻数据用list封装 ,获取网络数据需要在子线程中做
        new Thread(new Runnable() {

            @Override
            public void run() {


                //请求网络数据
                ArrayList<NewsBean> allNews = NewsUtils.getAllNewsForNetWork(getContext());
                //通过handler将msg发送到主线程去更新Ui
                Message msg = Message.obtain();
                msg.obj = allNews;
                handler.sendMessage(msg);


            }
        }).start();


        //3.设置listview条目的点击事件
        lv_news.setOnItemClickListener(this);








        return view;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //需要获取条目上bean对象中url做跳转
        NewsBean bean = (NewsBean) parent.getItemAtPosition(position);

        String url = bean.news_url;

        //跳转浏览器
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

}




