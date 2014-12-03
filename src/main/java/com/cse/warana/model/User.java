package com.cse.warana.model;

import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sashika
 * on Dec 03 0003, 2014.
 */
@Entity
@Table(name = "user")
public class User implements Serializable{

    private static final long serialVersionUID = 1960820966066484915L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long userId;

    @Column(name = "USERNAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String password;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
