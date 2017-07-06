package Fragments_News.News_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by magic on 2017/3/31.
 */
public class NewsOpenHelper extends SQLiteOpenHelper {

    public NewsOpenHelper(Context context) {
        super(context, "heimanews1", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
/*		newsJson.put("id", newsBean.getId());
		newsJson.put("title", newsBean.getTitle());
		newsJson.put("des", newsBean.getDes());
		newsJson.put("icon_url", newsBean.getIcon_url());
		newsJson.put("news_url", newsBean.getNews_url());
		newsJson.put("type", newsBean.getType());
		newsJson.put("time", newsBean.getTime());
		newsJson.put("comment", newsBean.getComment());*/
        db.execSQL("create table news (title varchar(200),des varchar(300),icon_url varchar(200),news_url varchar(200))" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

}
