package com.example.xiangmuu.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseLayFragment extends Fragment {


    private boolean IS_VIEW_CREATED = false;

    public boolean IS_DATA_LOAD = false;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        lazyLoad();

    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        lazyLoad();
    }

    private void lazyLoad(){
       if( getUserVisibleHint()  && IS_VIEW_CREATED  && !IS_DATA_LOAD){
           initData();
           IS_DATA_LOAD = true;
       }else{
       }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  LayoutInflater.from(container.getContext()).inflate(getLayoutID(),container,false);
        IS_VIEW_CREATED = true;

        initView();
        initLinstener();
        return view;
    }

    protected abstract void initLinstener();

    protected abstract void initData();

    protected abstract void initView();

    public abstract  int getLayoutID();
}
