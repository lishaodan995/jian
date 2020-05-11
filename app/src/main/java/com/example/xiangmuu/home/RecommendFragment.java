package com.example.xiangmuu.home;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.xiangmuu.R;
import com.example.xiangmuu.base.BaseFragment;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import java.util.ArrayList;
import java.util.List;


public class RecommendFragment extends BaseFragment<RecommendPresenter> implements RecommendContract.IRecommendView {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private List<NewsFragment> fragments = new ArrayList<>();

    private NewsFragmentAdapter newsFragmentAdapter;

    @Override
    protected RecommendPresenter initPresenter() {
        return new RecommendPresenter();
    }

    @Override
    protected void initLinstener() {

    }

    @Override
    protected void initData() {

        mPresenter.getColumList();
    }
    @Override
    protected void initView(View view) {

        tabLayout = view.findViewById(R.id.mytablayout);

        viewPager = view.findViewById(R.id.myviewpage);



    }


   private void initTab(final ColunmBean columList){
       newsFragmentAdapter = new NewsFragmentAdapter(getChildFragmentManager(),fragments);
       viewPager.setAdapter(newsFragmentAdapter);
       tabLayout.setupWithViewPager(viewPager);

       tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
           @Override
           public void onTabSelected(TabLayout.Tab tab) {
              for (int i = 0; i < columList.getData().getList().size(); i++) {
                   TextView tabAt = (TextView) tabLayout.getTabAt(i).getCustomView();
                   tabAt.setBackgroundResource(R.color.colorPrimary);
               }
               GradientDrawable drawable = new GradientDrawable();
               drawable.setCornerRadius(50);

               TextView customView = (TextView) tab.getCustomView();
               drawable.setStroke(1, Color.parseColor("#ff00ff"));
               drawable.setColor(Color.parseColor("#"+columList.getData().getList().get(tab.getPosition()).getBack_color()));
               customView.setBackground(drawable);
           }

           @Override
           public void onTabUnselected(TabLayout.Tab tab) {
           }
           @Override
           public void onTabReselected(TabLayout.Tab tab) {
           }
       });


    }

    @Override
    public int getLayoutID() {
        return R.layout.recommend_fragment;
    }
    @Override
    public void setRecommendList(NewsBean newsBean) {
    }
    @Override
    public void setColumList(ColunmBean columList) {
        if(columList.getCode() ==1){
            for (int i = 0; i < columList.getData().getList().size(); i++) {
                NewsFragment  newsFragment = new NewsFragment(columList.getData().getList().get(i).getId());
                fragments.add(newsFragment);
            }

            initTab(columList);

            for (int i = 0; i < columList.getData().getList().size(); i++) {
                TextView textView =(TextView) LayoutInflater.from(getContext()).inflate(R.layout.recommend_tab_item,null);
                textView.setGravity(Gravity.CENTER);
                textView.setText(i+"个");
                tabLayout.addTab(tabLayout.newTab().setCustomView(textView),i);
            }


        }

    }
}
