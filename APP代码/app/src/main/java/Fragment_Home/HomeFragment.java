package Fragment_Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hzy.magic.stock.KnowStock;
import com.hzy.magic.stock.MyApplication;
import com.hzy.magic.stock.R;
import com.hzy.magic.stock.choseStocks;
import com.hzy.magic.stock.strategy;


/**
 * Created by magic on 2017/3/31.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{
    private Button bt_dapan,bt_strategy,bt_choseStock,bt_knowStock;
    View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        mView=inflater.inflate(R.layout.tab01, container, false);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initEvent();
    }

    public void initEvent() {
        bt_strategy.setOnClickListener(this);
        bt_dapan.setOnClickListener(this);
        bt_choseStock.setOnClickListener(this);
        bt_knowStock.setOnClickListener(this);
    }

    public void initView() {
        bt_dapan = (Button) mView.findViewById(R.id.but_2);
        bt_choseStock=(Button) mView.findViewById(R.id.but_1);
        bt_strategy=(Button) mView.findViewById(R.id.but_7);
        bt_knowStock = (Button) mView.findViewById(R.id.but_8);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.but_7:
                Intent intent7 = new Intent(MyApplication.getContext(), strategy.class);
                startActivity(intent7);
                break;
            case R.id.but_8:
                Intent intent8 = new Intent(MyApplication.getContext(), KnowStock.class);
                startActivity(intent8);
                break;
            case R.id.but_1:
                Intent intent9 = new Intent(MyApplication.getContext(), choseStocks.class);
                startActivity(intent9);
                break;

            default:
                break;




        }




    }
}
