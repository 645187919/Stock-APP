package Fragment_Trade;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.hzy.magic.stock.MyApplication;
import com.hzy.magic.stock.R;
import com.hzy.magic.stock.caculateEarning;


/**
 * Created by magic on 2017/3/31.
 */
public class TradeFragment extends Fragment implements View.OnClickListener{
    RelativeLayout rl_fund,rl_invest;
    View mView;
    //初始化Activity
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rl_fund = (RelativeLayout) mView.findViewById(R.id.fund);
        rl_invest = (RelativeLayout) mView.findViewById(R.id.invest);
        rl_invest.setOnClickListener(this);
        rl_fund.setOnClickListener(this);

    }
    //创建View时要执行的方法
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //通过寻找ID加载相关的布局文件
        mView=inflater.inflate(R.layout.tab03, container, false);
        return mView;
    }
    //为相应的控件设置点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.invest:
                Intent intent=new Intent(MyApplication.getContext(),caculateEarning.class);
                startActivity(intent);
                break;
        }
    }
}
