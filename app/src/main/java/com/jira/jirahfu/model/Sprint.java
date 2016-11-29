package com.jira.jirahfu.model;

import java.util.Date;

/**
 * @author Gonca Akguen
 * @version 1.0
 * @since 11/2016 (WS16/17)
 */

public class Sprint
{
    private String id;
    private String key;
    private String name;
    private int storyPoints;
    private int numberOfuserStorys;
    private int numberOfTodoStatus;
    private int numberOfInProgressStatus;
    private int numberOfDoneStatus;
    private User user;
    private Date startDate;
    private Date endDate;
}
