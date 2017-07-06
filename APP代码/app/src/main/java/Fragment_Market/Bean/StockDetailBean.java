package Fragment_Market.Bean;

/**
 * Created by magic on 2017/4/4.
 */

public class StockDetailBean {
    public String name;
    public String gid;


    public String getMinurl() {
        return minurl;
    }

    public String minurl;

    public String getDayurl() {
        return dayurl;
    }

    public void setDayurl(String dayurl) {
        this.dayurl = dayurl;
    }

    public String dayurl;

    public String getWeekurl() {
        return weekurl;
    }

    public void setWeekurl(String weekurl) {
        this.weekurl = weekurl;
    }

    public String weekurl;

    public String getMonthurl() {
        return monthurl;
    }

    public void setMonthurl(String monthurl) {
        this.monthurl = monthurl;
    }

    public String monthurl;
}
