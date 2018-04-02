package com.example.duzeming.resourceback.user;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 陈金桁 on 2018/3/31.
 */
@Entity
public class GreenDaoUser {
//    @Id
//    private long id;
//    private User user;
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    private String account;
//    public String getAccount() {
//        return this.account;
//    }
//    public void setAccount(String account) {
//        this.account = account;
//    }
//    public long getId() {
//        return this.id;
//    }
//    public void setId(long id) {
//        this.id = id;
//    }
//    @Generated(hash = 960749115)
//    public GreenDaoUser(long id, String account) {
//        this.id = id;
//        this.account = account;
//    }
//    @Generated(hash = 83249558)
//    public GreenDaoUser() {
//    }
    @Id
    private Long id;
    
    private String account;

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Generated(hash = 2085353879)
    public GreenDaoUser(Long id, String account) {
        this.id = id;
        this.account = account;
    }

    @Generated(hash = 83249558)
    public GreenDaoUser() {
    }
}
