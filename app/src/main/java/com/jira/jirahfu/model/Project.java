package com.jira.jirahfu.model;

/**
 * @author Gonca Akguen
 * @version 1.0
 * @since 11/2016 (WS16/17)
 */

public class Project {

    private String id;
    private String projectTypeKey;
    private String key;
    private String name;
    private String lead;
    private Avatar avatar;

    public Project(String id, String projectTypeKey, String key, String name, String lead,
                   Avatar avatar) {
        this.id = id;
        this.projectTypeKey = projectTypeKey;
        this.key = key;
        this.name = name;
        this.lead= lead;
        this.avatar = avatar;
    }

    public Project(String id, String name, String key, String projectTypeKey, Avatar avatar) {
        this.id = id;
        this.name = name;
        this.key = key;
        this.projectTypeKey = projectTypeKey;
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public String getProjectTypeKey() {
        return projectTypeKey;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getLead() {
        return lead;
    }

    public Avatar getAvatar() {
        return avatar;
    }
}
