package com.pritamprasad.helloworld.Model;

import com.pritamprasad.helloworld.Utility.LocalConstants;

import java.util.Date;

/**
 * Task Data model
 */

public class Task {
    private int id=-1;
    private String taskName;
    private String taskDesc;
    private int parent_goal_id;
    private Date deadline_date;
    private int priority;
    private LocalConstants.TASK_STATES state;
    private int is_daily_task; // 0: No 1: yes
    private int estimated_days_required;
    private LocalConstants.QUADRANT_STATES quadrant_states;


    public Task(){

    }

    public Task(String name, String desc, int parent_id)
    {
        this.taskName = name;
        this.taskDesc = desc;
        this.parent_goal_id = parent_id;
    }



    public Task(int id, String name, String desc, int parent_id){
        this.setId(id);
        this.setTaskName(name);
        this.setTaskDesc(desc);
        this.setParent_goal_id(parent_id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public int getParent_goal_id() {
        return parent_goal_id;
    }

    public void setParent_goal_id(int parent_goal_id) {
        this.parent_goal_id = parent_goal_id;
    }
}
