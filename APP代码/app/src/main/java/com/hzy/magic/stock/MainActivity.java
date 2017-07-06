package com.hzy.magic.stock;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import Fragment_Market.MarketFragment;
import Fragment_Home.HomeFragment;
import Fragments_News.NewsFragment;
import Fragment_Trade.TradeFragment;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private ViewPager myViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragment=new ArrayList<Fragment>();
    private LinearLayout myTabWeiQiu;
    private LinearLayout myTabMyself;
    private LinearLayout myTabAddress;
    private LinearLayout myTabSetting;
    private ImageButton myWeiqiuImg;
    private ImageButton mySelfImg;
    private ImageButton myAddressImg;
    private ImageButton mySeettingImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();


    }

    private void initView(){

        myViewPager=(ViewPager) findViewById(R.id.Viewpager);
        myTabWeiQiu=(LinearLayout)findViewById(R.id.id_tab_weixin);
        myTabMyself=(LinearLayout)findViewById(R.id.id_tab_myself);
        myTabAddress=(LinearLayout)findViewById(R.id.id_tab_address);
        myTabSetting=(LinearLayout)findViewById(R.id.id_tab_setting);
        myWeiqiuImg=(ImageButton)findViewById(R.id.id_tab_weiqiu_img);
        mySelfImg=(ImageButton)findViewById(R.id.id_tab_myself_img);
        myAddressImg=(ImageButton)findViewById(R.id.id_tab_address_img);
        mySeettingImg=(ImageButton)findViewById(R.id.id_tab_setting_img);
        mFragment=new ArrayList<Fragment>();
        Fragment tab01=new HomeFragment();
        Fragment tab02=new MarketFragment();
        Fragment tab03=new TradeFragment();
        Fragment tab04=new NewsFragment();
        mFragment.add(tab01);
        mFragment.add(tab02);
        mFragment.add(tab03);
        mFragment.add(tab04);
        mAdapter=new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return mFragment.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                // TODO Auto-generated method stub
                return mFragment.get(arg0);
            }
        };

        myViewPager.setAdapter(mAdapter);
    }

    private void initEvent(){
        myTabWeiQiu.setOnClickListener(this);
        myTabMyself.setOnClickListener(this);
        myTabAddress.setOnClickListener(this);
        myTabSetting.setOnClickListener(this);

        myViewPager.setOnPageChangeListener(
                new ViewPager.OnPageChangeListener() {

                    @Override
                    public void onPageSelected(int arg0) {
                        // TODO Auto-generated method stub
                        int currentItem=myViewPager.getCurrentItem();
                        switch(currentItem)
                        {
                            case 0:
                                resetImg();
                                myWeiqiuImg.setImageResource(R.drawable.menu_home_select_img);
                                break;
                            case 1:
                                resetImg();
                                myAddressImg.setImageResource(R.drawable.menu_hangqing_select_img);
                                break;
                            case 2:
                                resetImg();
                                mySelfImg.setImageResource(R.drawable.menu_trade_select_img);
                                break;
                            case 3:
                                resetImg();
                                mySeettingImg.setImageResource(R.drawable.menu_news_select_img);
                                break;
                            default:
                                break;


                        }
                    }

                    @Override
                    public void onPageScrolled(int arg0, float arg1, int arg2) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onPageScrollStateChanged(int arg0) {
                        // TODO Auto-generated method stub

                    }
                }


        );

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_tab_weixin:
                myViewPager.setCurrentItem(0);
                resetImg();
                myWeiqiuImg.setImageResource(R.drawable.menu_home_select_img);
                break;
            case R.id.id_tab_address:
                myViewPager.setCurrentItem(1);
                resetImg();
                myAddressImg.setImageResource(R.drawable.menu_hangqing_select_img);
                break;
            case R.id.id_tab_myself:
                myViewPager.setCurrentItem(2);
                resetImg();
                mySelfImg.setImageResource(R.drawable.menu_trade_select_img);
                break;
            case R.id.id_tab_setting:
                myViewPager.setCurrentItem(3);
                resetImg();
                mySeettingImg.setImageResource(R.drawable.menu_news_select_img);
                break;

            default:
                break;
        }

    }
    private void resetImg(){
        myWeiqiuImg.setImageResource(R.drawable.menu_home_normal_img);
        myAddressImg.setImageResource(R.drawable.menu_hangqing_normal_img);
        mySelfImg.setImageResource(R.drawable.menu_trade_normal_img);
        mySeettingImg.setImageResource(R.drawable.menu_news_normal_img);

    }

}
