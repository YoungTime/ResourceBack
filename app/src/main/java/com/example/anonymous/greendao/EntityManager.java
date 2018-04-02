package com.example.anonymous.greendao;

/**
 * Created by 陈金桁 on 2018/3/31.
 */

public class EntityManager {
    private static EntityManager entityManager;
    public GreenDaoUserDao userDao;
    public GreenDaoUserDao getUserDao(){
        userDao = DaoManager.getInstance().getSession().getGreenDaoUserDao();
        return userDao;
    }
    public static EntityManager getInstance(){
        if(entityManager == null){
            entityManager = new EntityManager();
        }
        return entityManager;
    }
}
