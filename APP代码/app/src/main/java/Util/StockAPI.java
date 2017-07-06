package Util;

/**
 * Created by magic on 2017/4/4.
 */
//拼装股票数据的访问地址
public class StockAPI {
    //URL
    public static final String HOST = "http://web.juhe.cn:8080/finance/stock/";
    //配置申请的KEY
    public static final String APPKEY = "ba4122b2ca9daa72ee8df4b31e682608";




//    1.获取沪深股市数据


    public static String getHS(String gid) {

        String url = HOST + "hs";
        return url  + "?gid=" + gid+"&key=" + APPKEY ;

    }
    //2.香港股市
    public static String getHK(String gid) {

        String url = HOST + "hk";
        return url + "?num="+ gid +"&key=" + APPKEY;
    }

    //3.美国股市
    public static String getUSA(String gid) {
        String url = HOST + "usa";
        return url + "?gid=" + gid+"&key=" + APPKEY ;

    }

    //4.获取香港股市列表数据
    public static String getHKAll(int page) {

        String url = HOST + "hkall";
        return url + "?type=2&key=" + APPKEY + "&page=" + page;
    }

    //5.获取美国股市列表数据
    public static String  getUSAAll(int page) {
        String url = HOST + "usaall";
        return url + "?type=2&key=" + APPKEY + "&page=" + page;

    }

    //6.获取深圳股市列表数据

    public static String getSZAll(int page) {
        String url = HOST + "szall";
        return url + "?type=2&key=" + APPKEY + "&page=" + page;
    }

    //7.获取沪股股市列表数据
    public static String getSHAll(int page) {
        String url = HOST + "shall";
        return url + "?type=2&key=" + APPKEY + "&page=" + page;

    }

}


