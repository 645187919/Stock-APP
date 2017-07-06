package Fragment_Market;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hzy.magic.stock.R;

import java.util.ArrayList;

import Fragment_Market.Bean.StockBean;

/**
 * Created by magic on 2017/4/3.
 */

public class MarketAdapter extends BaseAdapter {

    private ArrayList<StockBean> list;
    private Context context;

    //通过构造方法接受要显示的新闻数据集合
    public MarketAdapter(Context context, ArrayList<StockBean> list){
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        //1.复用converView优化listview,创建一个view作为getview的返回值用来显示一个条目
        if(convertView != null){
            view = convertView;
        }else {
            //context:上下文, resource:要转换成view对象的layout的id, root:将layout用root(ViewGroup)包一层作为codify的返回值,一般传null
//			view = View.inflate(context, R.layout.item_news_layout, null);//将一个布局文件转换成一个view对象

            //通过LayoutInflater将布局转换成view对象
//			view =  LayoutInflater.from(context).inflate(R.layout.item_news_layout, null);

            //通过context获取系统服务得到一个LayoutInflater，通过LayoutInflater将一个布局转换为view对象
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_stock_layout, null);

        }
        //2.获取view上的子控件对象
        TextView stockName = (TextView) view.findViewById(R.id.stock_name_tv);
        TextView stockNum = (TextView) view.findViewById(R.id.stock_num_tv);
        TextView priceChange = (TextView) view.findViewById(R.id.pricechange_tv);
        TextView changePercent = (TextView) view.findViewById(R.id.changepercent_tv);
        TextView newPrice = (TextView) view.findViewById(R.id.new_price);







        //3.获取postion位置条目对应的list集合中的新闻数据，Bean对象
        StockBean stockbean = list.get(position);
        //4.将数据设置给这些子控件做显示
        stockName.setText(stockbean.name);
        stockNum.setText(stockbean.symbol);
        priceChange.setText(stockbean.priceChange);
        changePercent.setText(stockbean.changePercent);
        newPrice.setText(stockbean.newPrice);







        return view;
    }

}
