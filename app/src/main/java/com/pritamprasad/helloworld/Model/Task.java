package com.pritamprasad.helloworld.Model;

/**
 * Created by jarvis on 1/28/17.
 */

public class Task {
    private int id=-1;
    private String taskName;
    private String taskDesc;
    private int parent_goal_id;

    public Task(){

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
