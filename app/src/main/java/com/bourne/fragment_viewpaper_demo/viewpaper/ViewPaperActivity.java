package com.bourne.fragment_viewpaper_demo.viewpaper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bourne.fragment_viewpaper_demo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewPaperActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.id_viewpager)
     ViewPager mViewPager;

    @BindView(R.id.id_tab_weixin)
     LinearLayout mTabWeixin;

    @BindView(R.id.id_tab_frd)
     LinearLayout mTabFrd;

    @BindView(R.id.img_model)
     ImageButton img_model;

    @BindView(R.id.img_task)
     ImageButton img_task;

    @BindView(R.id.tv_model)
    TextView tv_model;

    @BindView(R.id.tv_task)
    TextView tv_task;

    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_paper);
        ButterKnife.bind(this);
        initView();
        initEvent();
        setSelect(0);
    }
    private void initEvent()
    {
        mTabWeixin.setOnClickListener(this);
        mTabFrd.setOnClickListener(this);
    }

    private void initView()
    {

        mFragments = new ArrayList<Fragment>();
        Fragment mTab01 = new Fragment1();
        Fragment mTab02 = new Fragment2();

        mFragments.add(mTab01);
        mFragments.add(mTab02);

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager())
        {

            @Override
            public int getCount()
            {
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int arg0)
            {
                return mFragments.get(arg0);
            }
        };

        mViewPager.setAdapter(mAdapter);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {

            @Override
            public void onPageSelected(int arg0)
            {
                int currentItem = mViewPager.getCurrentItem();
                setTab(currentItem);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2)
            {
            }

            @Override
            public void onPageScrollStateChanged(int arg0)
            {
            }
        });
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.id_tab_weixin:
                setSelect(0);
                break;
            case R.id.id_tab_frd:
                setSelect(1);
                break;

            default:
                break;
        }
    }

    private void setSelect(int i)
    {
        setTab(i);
        mViewPager.setCurrentItem(i);
    }

    private void setTab(int i)
    {
        resetImgs();

        switch (i)
        {
            case 0:

                getSupportActionBar().setTitle("聊天");
                img_model.setImageResource(R.mipmap.tab_weixin_pressed);
                tv_model.setTextColor(getResources().getColor(R.color.colorPrimary));
                tv_task.setTextColor(getResources().getColor(R.color.black));
                break;
            case 1:
                getSupportActionBar().setTitle("通讯录");
                img_task.setImageResource(R.mipmap.tab_find_frd_pressed);
                tv_model.setTextColor(getResources().getColor(R.color.black));
                tv_task.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
        }
    }

    private void resetImgs()
    {
        img_model.setImageResource(R.mipmap.tab_weixin_normal);
        img_task.setImageResource(R.mipmap.tab_find_frd_normal);

    }
}
