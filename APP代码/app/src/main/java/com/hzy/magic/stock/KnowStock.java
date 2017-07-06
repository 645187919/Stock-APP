package com.hzy.magic.stock;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by magic on 2017/4/19.
 */
public class KnowStock extends Activity implements View.OnClickListener{
    private TextView tvKDJ,tvMACD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stockknow_layout);
        tvKDJ = (TextView) findViewById(R.id.tv_kdj);
        tvMACD = (TextView) findViewById(R.id.tv_macd);
        tvMACD.setOnClickListener(this);
        tvKDJ.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_kdj:
                Uri uri=Uri.parse("http://baike.baidu.com/link?url=3AZ8iwW2FbLj9HtFyZql5AdzH20aGeHY8saZpjnuQzKlCsQrAMYIb9CID-mIonhhh2Xn08efeJnj_T0ZpM07YurOADfTIuumkU2Gt4W-2gi");
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
                break;
            case R.id.tv_macd:
                Uri uri1=Uri.parse("http://baike.baidu.com/link?url=stZLKxy42J_4SG0gqq8GSn-7Y9PDe6u2sz72h8POUdwpumb4119FdXTjUnOpywHeHNe3lwoUo4njOd2ASSVyiHtsh3_Jl42sfbwMsCWF34QbHimA3O-mB9snhqD0Xc_4PVkgAWKj1qGDhEAJGah8ZK");
                Intent intent1=new Intent(Intent.ACTION_VIEW,uri1);
                startActivity(intent1);
                break;

        }

    }
}
