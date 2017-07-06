//package Fragment_Market.Market_db;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//
//import java.util.ArrayList;
//
//import Fragment_Market.Bean.StockDetailBean;
//
///**
// * Created by magic on 2017/4/4.
// */
//
//public class StockDetailDB {
//    private StockDetailOpenHelper marketOpenHelper;
//
//    public StockDetailDB(Context context){
//        //创建一个帮助类对象
//        marketOpenHelper = new StockDetailOpenHelper(context);
//
//    }
//
//
//
//
//    //删除数据库中缓存的旧数据
//    public void delete(){
//
//        //通过帮助类对象获取一个数据库操作对象
//        SQLiteDatabase db = marketOpenHelper.getReadableDatabase();
//        db.delete("stocks_detail", null, null);
//        db.close();
//    }
//
//    //向数据库中添加新闻数据
//    public void saveNews(ArrayList<StockDetailBean> list){
//
//        //通过帮助类对象获取一个数据库操作对象
//        SQLiteDatabase db = marketOpenHelper.getReadableDatabase();
//        for (StockDetailBean stockDetailBean : list) {
//            ContentValues values = new ContentValues();
//
//            values.put("gid", stockDetailBean.gid);
//            values.put("name", stockDetailBean.name);
//            values.put("minurl", stockDetailBean.minurl);
//            values.put("dayurl", stockDetailBean.dayurl);
//            values.put("weekurl", stockDetailBean.weekurl);
//
//            values.put("monthurl", stockDetailBean.monthurl);
//
//
//
//            db.insert("stocks_detail", null, values);
//
//        }
//
//        db.close();
//    }
//
//    //从数据库中获取缓存的新闻数据
//    public ArrayList<StockDetailBean> getNews(){
//        ArrayList<StockDetailBean> list = new ArrayList<StockDetailBean>();
//        //通过帮助类对象获取一个数据库操作对象
//        SQLiteDatabase db = marketOpenHelper.getReadableDatabase();
//        Cursor cursor = db.rawQuery("select * from stocks_detail", null);//查询获取数据
//
//        if(cursor != null && cursor.getCount() > 0){
//
//            while(cursor.moveToNext()){
//
//                StockDetailBean newsBean = new StockDetailBean();
//
//                newsBean.gid= cursor.getString(0);
//                newsBean. name =	cursor.getString(1);
//                newsBean. minurl =cursor.getString(2);
//                newsBean. dayurl =cursor.getString(3);
//                newsBean. weekurl =cursor.getString(4);
//                newsBean. monthurl =cursor.getString(5);
//
//
//                list.add(newsBean);
//            }
//        }
//
//        db.close();
//        cursor.close();
//
//
//        return list;
//
//    }
//
//
//
//}
