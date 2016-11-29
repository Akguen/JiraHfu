package com.jira.jirahfu.model;

/**
 * @author Gonca Akguen
 * @version 1.0
 * @since 11/2016 (WS16/17)
 */

public class Task
{
    private String id;
    private String key;
    private String summary;
    private String description;
    private OperationType operationType;
    private OperationStatus operationStatus;
    private Priority priority;
    private Version version;
    private UserStory userStory;
    private User user;
}
