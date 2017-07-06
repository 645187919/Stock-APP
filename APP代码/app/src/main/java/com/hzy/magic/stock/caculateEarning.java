package com.hzy.magic.stock;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by magic on 2017/4/19.
 */

public class caculateEarning extends Activity {
    private TextView tvMin, tvIncome;
    private EditText etBuy, etSale, etCount;
    private Button btnCalculate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.caculate_earning_layout);
        //初始化UI布局中对应的控件
        initView();
        btnCalculate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //获取控件中输入的内容
                String strSale = etSale.getText().toString().trim();
                String strBuy = etBuy.getText().toString().trim();
                String strCount = etCount.getText().toString().trim();
                //若输入内容不为空则依据相关的公式计算股票的交易的收益
                if(!strBuy.equals("") && !strCount.equals("")) {
                    Float buy = Float.valueOf(strBuy);
                    Integer count = Integer.valueOf(strCount);
                    Float guohu = (float) Math.ceil(count/1000.0)+1;
                    Float yinhuashui = (float) (buy*count*0.001);
                    Float yongjinbuy = (float) (buy*count*0.003);
                    if( yongjinbuy < 5 )
                        yongjinbuy = (float) 5;
                    Float baoben = (float) ((buy*count+guohu+yongjinbuy)/count/0.996);
                    tvMin.setText(baoben.toString());
                    if(!strSale.equals("")){
                        Float sale = Float.valueOf(strSale);
                        Float yongjinsale = (float) (sale*count*0.003);
                        yinhuashui = (float) (sale*count*0.001);
                        if( yongjinsale < 5 )
                            yongjinsale = (float) 5;
                        Float shouyi = sale*count-buy*count-guohu-yinhuashui-yongjinbuy-yongjinsale;
                        tvIncome.setText(shouyi.toString());
                    }
                }
            }
        });
    }
    public void initView(){
        tvMin = (TextView) findViewById(R.id.tvMinPirce);
        tvIncome = (TextView) findViewById(R.id.tvIncome);
        etBuy = (EditText) findViewById(R.id.edBuyPrice);
        etSale = (EditText) findViewById(R.id.edSalePrice);
        etCount = (EditText) findViewById(R.id.edCount);
        btnCalculate = (Button) findViewById(R.id.btnCalculate);

    }
}
