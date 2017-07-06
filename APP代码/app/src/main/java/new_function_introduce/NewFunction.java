package new_function_introduce;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.hzy.magic.stock.MainActivity;
import com.hzy.magic.stock.R;

import java.util.ArrayList;

/**
 * Created by magic on 2017/3/31.
 */

public class NewFunction extends Activity {
    private ViewPager mViewPager;
    private ImageView mPage0;
    private ImageView mPage1;
    private ImageView mPage2;
    private ImageView mPage3;
    private ImageView mPage4;
    private int currIndex = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newfunction_viewpager);
        initView();

    }

    public void initView(){
        mViewPager.setOnPageChangeListener((ViewPager.OnPageChangeListener) new MyOnPageChangeListener());
        mViewPager = (ViewPager)findViewById(R.id.whatsnew_viewpager);
        mPage0 = (ImageView)findViewById(R.id.page0);
        mPage1 = (ImageView)findViewById(R.id.page1);
        mPage2 = (ImageView)findViewById(R.id.page2);
        mPage3 = (ImageView)findViewById(R.id.page3);
        mPage4 = (ImageView)findViewById(R.id.page4);

        //将要分页显示的View装入数组中
        LayoutInflater mLi = LayoutInflater.from(this);
        View view1 = mLi.inflate(R.layout.whats1, null);
        View view2 = mLi.inflate(R.layout.whats2, null);
        View view3 = mLi.inflate(R.layout.whats3, null);
        View view4 = mLi.inflate(R.layout.whats4, null);
        View view5 = mLi.inflate(R.layout.whats5, null);
        //每个页面的view布局数据
        final ArrayList<View> views = new ArrayList<View>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);
        views.add(view5);
        //填充ViewPager的数据适配器
        PagerAdapter mPagerAdapter = new PagerAdapter() {

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                return views.size();
            }

            @Override
            public void destroyItem(View container, int position, Object object) {
                ((ViewPager)container).removeView(views.get(position));
            }



            @Override
            public Object instantiateItem(View container, int position) {
                ((ViewPager)container).addView(views.get(position));
                return views.get(position);
            }
        };

        mViewPager.setAdapter(mPagerAdapter);
    }

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageSelected(int arg0) {
            switch (arg0) {
                //当滑动到不同的页面时，分别对应不同的case.然后再在当前case下通过相应的控件就去加载对应的资源
                case 0:
                    mPage0.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
                    mPage1.setImageDrawable(getResources().getDrawable(R.drawable.page));
                    break;
                case 1:
                    mPage1.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
                    mPage0.setImageDrawable(getResources().getDrawable(R.drawable.page));
                    mPage2.setImageDrawable(getResources().getDrawable(R.drawable.page));
                    break;
                case 2:
                    mPage2.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
                    mPage1.setImageDrawable(getResources().getDrawable(R.drawable.page));
                    mPage3.setImageDrawable(getResources().getDrawable(R.drawable.page));
                    break;
                case 3:
                    mPage3.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
                    mPage4.setImageDrawable(getResources().getDrawable(R.drawable.page));
                    mPage2.setImageDrawable(getResources().getDrawable(R.drawable.page));
                    break;
                case 4:
                    mPage4.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
                    mPage3.setImageDrawable(getResources().getDrawable(R.drawable.page));
                    //当滑动到最后一页时，通过Handler().postDelayed()方法来实现在页面停留一定时间后自动跳转的功能。
                    new Handler().postDelayed(new Runnable(){
                        @Override
                        public void run(){
                           Intent intent = new Intent (NewFunction.this,MainActivity.class);
                            startActivity(intent);
                            NewFunction.this.finish();
                        }
                    }, 1000);

                    break;
            }
            currIndex = arg0;
        }
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }

}



