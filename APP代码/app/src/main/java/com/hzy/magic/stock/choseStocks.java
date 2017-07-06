package com.hzy.magic.stock;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import Fragment_Home.Bean.ServiceBean;
import Fragments_News.NewsBean;

/**
 * Created by magic on 2017/4/18.
 */

public class choseStocks extends Activity implements View.OnClickListener {
    TextView pre_accury,pre_num;

    private Button mButton;
    static String urL="http://10.0.2.2:5000/ml";
    public static ServiceBean nb=new ServiceBean();
    public Handler handler=new Handler(){
        public void handleMessage(Message msg) {
            nb=(ServiceBean) msg.obj;
            pre_accury.setText(nb.getaccury().toString());

        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chose_stocks_layou);
        initView();
        initEvent();


    }

    private void initEvent() {
        mButton.setOnClickListener(this);
        pre_num.setOnClickListener(this);
        pre_accury.setOnClickListener(this);
    }

    private void initView() {
        mButton = (Button) findViewById(R.id.bt_start_pre);
        pre_accury = (TextView) findViewById(R.id.tv_pre_accury);
        pre_num = (TextView) findViewById(R.id.tv_pre_num);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_start_pre:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        nb = getAllNewsForNetWork(MyApplication.getContext());
                        Message msg = new Message();
                        msg.obj=nb;
                        handler.sendMessage(msg);



                    }
                }).start();

                break;

        }

    }
//    接受自己搭建的服务器返回的JSON格式的模型运行结果
    public static ServiceBean getAllNewsForNetWork(Context context) {

        try{
            //1.请求服务器获取新闻数据
            //获取一个url对象，通过url对象得到一个urlconnnection对象
            URL url = new URL(urL);
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


        }catch (Exception e) {
            e.printStackTrace();
        }
        return nb;
    }
    public static void parseJSONWithJSONObject(Context context, String jsonData) {

        JSONArray root_json= null;
        try {
            root_json = new JSONArray(jsonData);
            JSONObject jsonArray1 = root_json.getJSONObject(0);//获取root_json中的newss作为jsonArray对象
            JSONObject jsonArray2 = jsonArray1.getJSONObject("result");
            String reson=jsonArray1.getString("reason");
            Log.i("reson",reson);

            //获取root_json中的newss作为jsonArray对象

            String string=jsonArray2.toString();
            nb.num=reson;

            Log.i("result",string);
            nb.accury=  jsonArray2.getDouble("right_rate");
            String name=nb.accury.toString();
            Log.i("double",name);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}


