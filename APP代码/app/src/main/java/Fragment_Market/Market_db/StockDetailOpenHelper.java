//package Fragment_Market.Market_db;
//
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
///**
// * Created by magic on 2017/4/4.
// */
//
//public class StockDetailOpenHelper extends SQLiteOpenHelper {
//    public StockDetailOpenHelper(Context context) {
//        super(context, "dad", null, 1);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("create table stocks_detail (gid varchar(20),name varchar(30),minurl varchar(60),dayurl varchar(60),weekurl varchar(60),monthurl varchar(60))" );
//
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//
//    }
//}
