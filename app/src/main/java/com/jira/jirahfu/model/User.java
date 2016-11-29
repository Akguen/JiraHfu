package com.jira.jirahfu.model;

import java.io.Serializable;

/**
 * @author Gonca Akguen
 * @version 1.0
 * @since 11/2016 (WS16/17)
 */


public class User implements Serializable {

    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


}
