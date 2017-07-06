package Fragment_Market.Bean;

import java.io.Serializable;

/**
 * Created by magic on 2017/4/5.
 */

public  class SHIndexBean implements Serializable{

    public void setNowPric(String nowPric) {
        this.nowPric = nowPric;
    }

    public String getNowPric() {
        return nowPric;
    }

    public String nowPric;

    public void setChangePrice(String changePrice) {
        this.changePrice = changePrice;
    }

    public String getChangePrice() {
        return changePrice;
    }

    public String changePrice;

    public void setChangePercent(String changePercent) {
        this.changePercent = changePercent;
    }

    public String getChangePercent() {
        return changePercent;
    }

    public String changePercent;

    public String getMinurl() {
        return minurl;
    }

    public void setMinurl(String minurl) {
        this.minurl = minurl;
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

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public String date;
}
