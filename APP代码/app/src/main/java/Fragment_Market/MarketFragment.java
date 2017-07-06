package Fragment_Market;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hzy.magic.stock.AmerianStockActivity;
import com.hzy.magic.stock.HKStcokActivity;
import com.hzy.magic.stock.MyApplication;
import com.hzy.magic.stock.R;
import com.hzy.magic.stock.SHIndex;
import com.hzy.magic.stock.SZIndex;
import com.hzy.magic.stock.shStockActivity;
import com.hzy.magic.stock.szStockActivity;

import java.util.List;

import Fragment_Market.Bean.SHIndexBean;
import Fragment_Market.Bean.SZIndexBean;
import Util.HttpForIndex;
import Util.StockAPI;


/**
 * Created by magic on 2017/3/31.
 */
public class MarketFragment extends Fragment implements View.OnClickListener{
    private View mView;
    private RelativeLayout lu_stock_ly,shen_stock_ly,gang_stock_ly,world_stock_ly;
    private TextView shIndex,szIndex,shPriceChange,szPriceChange,shChangePercent,szChangePercent;
    private LinearLayout shIndex_layout,szIndex_layout;
    private static Intent intent4,intent5;
    public  static List<Object>  obBean;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    SHIndexBean mindexBeanSH = (SHIndexBean) msg.obj;
                    intent4=new Intent(MyApplication.getContext(),SHIndex.class);
                    intent4.putExtra("shindexBean", mindexBeanSH);
                    shIndex.setText(mindexBeanSH.getNowPric());
                    shPriceChange.setText(mindexBeanSH.getChangePrice());
                    shChangePercent.setText(mindexBeanSH.getChangePercent());
                    Log.i("message1:", mindexBeanSH.nowPric);

                    break;
                case 2:
                    SZIndexBean smindexBean= (SZIndexBean) msg.obj;
                    intent5=new Intent(MyApplication.getContext(),SZIndex.class);
                    intent5.putExtra("szIndexBean", smindexBean);
                    szIndex.setText(smindexBean.getNowPric());
                    szPriceChange.setText(smindexBean.getChangePrice());
                    szChangePercent.setText(smindexBean.getChangePercent());

                    break;
        }

        }
    };



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initEvent();
//        通过创建不同的Bean对象来接收返回的泸股和深股的大盘指数信息
        new Thread(new Runnable() {
            @Override
            public void run() {
               SHIndexBean SHIndexBean = HttpForIndex.getIndexForNetWork(MyApplication.getContext(), StockAPI.getHS("sh000001"));

                Message msg=new Message();
                msg.obj= SHIndexBean;
                msg.what=1;
                mHandler.sendMessage(msg);

                SZIndexBean szIndexBean= HttpForIndex.getSZIndexForNetWork1(MyApplication.getContext(), StockAPI.getHS("sz399001"));

                Message msg1 = new Message();
                msg1.what=2;
                msg1.obj= szIndexBean;
                mHandler.sendMessage(msg1);



            }
        }).start();

    }

    private void initEvent() {
        lu_stock_ly.setOnClickListener(this);
        shen_stock_ly.setOnClickListener(this);
        gang_stock_ly.setOnClickListener(this);
        world_stock_ly.setOnClickListener(this);
        shIndex_layout.setOnClickListener(this);
        szIndex_layout.setOnClickListener(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        mView = inflater.inflate(R.layout.tab02, container, false);





        return mView;
    }

    private void initView() {
        lu_stock_ly= (RelativeLayout) mView.findViewById(R.id.lu_stock);
        shen_stock_ly= (RelativeLayout) mView.findViewById(R.id.shen_stock);
        gang_stock_ly= (RelativeLayout) mView.findViewById(R.id.gang_stock);
        world_stock_ly=(RelativeLayout) mView.findViewById(R.id.world_stock);
        shIndex= (TextView) mView.findViewById(R.id.sh_index_tv);
        szIndex= (TextView) mView.findViewById(R.id.sz_index_tv);
        shPriceChange= (TextView) mView.findViewById(R.id.sh_priceChange_tv);
        shChangePercent= (TextView) mView.findViewById(R.id.sh_pricePercent_tv);
        szPriceChange= (TextView) mView.findViewById(R.id.sz_changePrice_tv);
        szChangePercent= (TextView) mView.findViewById(R.id.sz_pricePercent_tv);
        shIndex_layout= (LinearLayout) mView.findViewById(R.id.sh_index_layout);
        szIndex_layout= (LinearLayout) mView.findViewById(R.id.sz_index_layout);



    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.lu_stock:
                Intent intent=new Intent(MyApplication.getContext(),shStockActivity.class);
                startActivity(intent);
                break;
            case R.id.shen_stock:
                Intent intent1=new Intent(MyApplication.getContext(), szStockActivity.class);
                startActivity(intent1);
                break;
            case R.id.gang_stock:
                Intent intent2=new Intent(MyApplication.getContext(),HKStcokActivity.class);
                startActivity(intent2);
                break;
            case R.id.world_stock:
                Intent intent3=new Intent(MyApplication.getContext(),AmerianStockActivity.class);
                startActivity(intent3);
                break;
            case R.id.sh_index_layout:

                startActivity(intent4);
                break;
            case R.id.sz_index_layout:
                startActivity(intent5);
                break;







        }

    }
}
