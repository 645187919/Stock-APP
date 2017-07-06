package Fragment_Market.Market_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by magic on 2017/4/3.
 */

public class MarketOpenHelper extends SQLiteOpenHelper {


    public MarketOpenHelper(Context context) {
        super(context, "asdasd", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table stocks_info (symbol varchar(20),name varchar(30),pricechange decimal(20),changepercent decimal(20))" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
