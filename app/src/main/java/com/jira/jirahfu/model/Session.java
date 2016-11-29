package com.jira.jirahfu.model;

/**
 * @author Gonca Akguen
 * @version 1.0
 * @since 11/2016 (WS16/17)
 */

public class Session {

    private String name;
    private String value;


    public Session(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
