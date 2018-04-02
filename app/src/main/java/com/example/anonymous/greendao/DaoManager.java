package com.example.anonymous.greendao;

import com.example.duzeming.resourceback.application.MyApplication;

/**
 * Created by 陈金桁 on 2018/3/31.
 */

public class DaoManager  {
    private static DaoManager mInstance;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private DaoManager(){
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(MyApplication.getmContext(),"my-db",null);
        DaoMaster mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }
    public DaoMaster getMaster() {
        return mDaoMaster;
    }

    public DaoSession getSession() {
        return mDaoSession;
    }

    public static DaoManager getInstance() {
        if (mInstance == null) {
            mInstance = new DaoManager();
        }
        return mInstance;
    }
}
