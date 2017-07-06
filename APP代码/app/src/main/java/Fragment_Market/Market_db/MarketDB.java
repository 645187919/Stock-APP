package Fragment_Market.Market_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import Fragment_Market.Bean.StockBean;

/**
 * Created by magic on 2017/4/3.
 */

public class MarketDB {
    private MarketOpenHelper marketOpenHelper;

    public MarketDB(Context context){
        //创建一个帮助类对象
        marketOpenHelper = new MarketOpenHelper(context);

    }




    //删除数据库中缓存的旧数据
    public void delete(){

        //通过帮助类对象获取一个数据库操作对象
        SQLiteDatabase db = marketOpenHelper.getReadableDatabase();
        db.delete("stocks_info", null, null);
        db.close();
    }

    //向数据库中添加新闻数据
    public void saveNews(ArrayList<StockBean> list){

        //通过帮助类对象获取一个数据库操作对象
        SQLiteDatabase db = marketOpenHelper.getReadableDatabase();
        for (StockBean stockBean : list) {
            ContentValues values = new ContentValues();

            values.put("symbol", stockBean.symbol);
            values.put("name", stockBean.name);
            values.put("pricechange", stockBean.priceChange);
            values.put("changepercent", stockBean.changePercent);

            db.insert("stocks_info", null, values);

        }

        db.close();
    }

    //从数据库中获取缓存的新闻数据
    public ArrayList<StockBean> getNews(){
        ArrayList<StockBean> list = new ArrayList<StockBean>();
        //通过帮助类对象获取一个数据库操作对象
        SQLiteDatabase db = marketOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from stocks_info", null);//查询获取数据

        if(cursor != null && cursor.getCount() > 0){

            while(cursor.moveToNext()){

                StockBean newsBean = new StockBean();

                newsBean.symbol= cursor.getString(0);
                newsBean. name =	cursor.getString(1);
                newsBean. priceChange =cursor.getString(2);
                newsBean. changePercent =cursor.getString(3);

                list.add(newsBean);
            }
        }

        db.close();
        cursor.close();


        return list;

    }



}
