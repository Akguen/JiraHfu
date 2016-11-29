package com.jira.jirahfu.model;

/**
 * @author Gonca Akguen
 * @version 1.0
 * @since 11/2016 (WS16/17)
 */


public class OperationStatus
{
/*    public static final OperationType EPIC = new OperationType("Epic", 0);
    public static final OperationType story = new OperationType("User-Story", 1);
    public static final OperationType task = new OperationType("Task", 2);
    public static final OperationType bug = new OperationType("Bug", 3);
*/
    public enum OperationStatusType
    {
        TODO, INPROGRESS, DONE
    }
}
