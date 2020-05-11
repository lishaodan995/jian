package com.example.xiangmuu.ap;

import android.app.Activity;
import android.os.Process;

import java.util.Stack;

import androidx.fragment.app.Fragment;

public class AppManager {

    private static Stack<Activity> activityStack = new Stack<>();
    private static Stack<Fragment> fragmentStack = new Stack<>();


    private static volatile AppManager appManager;

    private AppManager() {
    }

    public static AppManager getInstance() {
        if (appManager == null){
            synchronized (AppManager.class){
                if (appManager ==null){
                    appManager = new AppManager();
                }
            }
        }
        return appManager;
    }


    public static Stack<Activity> getActivityStack(){
        return activityStack;
    }

    public static Stack<Fragment> getFragmentStack(){
        return fragmentStack;
    }


    public void addActivity(Activity activity){
        if (activityStack == null){
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }
    public void removeActivity(Activity activity){
        if (activity!=null){
            activity.finish();
            activityStack.remove(activity);
        }
    }

    public void deleteActivity(){
        Activity activity = activityStack.lastElement();
        if(activity!= null){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
    }


    public void appExit(){
        for (int i = 0; i < activityStack.size(); i++) {
            if (null != activityStack.get(i)){
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
        System.exit(0);
        Process.killProcess(Process.myPid());

    }



}
