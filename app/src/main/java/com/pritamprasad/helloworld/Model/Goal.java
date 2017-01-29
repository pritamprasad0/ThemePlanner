package com.pritamprasad.helloworld.Model;

/**
 * Created by jarvis on 1/28/17.
 */

public class Goal {
    private int id=-1;
    private String goalName;
    private String goalDesc;
    private int parent_theme_id;

    public Goal() {

    }

    public Goal(String name, String desc, int parent_id)
    {
        this.goalName = name;
        this.goalDesc = desc;
        this.parent_theme_id = parent_id;
    }

    public Goal(int id, String name, String desc,int parent_id) {
        this.setId(id);
        this.setGoalName(name);
        this.setGoalDesc(desc);
        this.setParent_theme_id(parent_id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public String getGoalDesc() {
        return goalDesc;
    }

    public void setGoalDesc(String goalDesc) {
        this.goalDesc = goalDesc;
    }

    public int getParent_theme_id() {
        return parent_theme_id;
    }

    public void setParent_theme_id(int parent_theme_id) {
        this.parent_theme_id = parent_theme_id;
    }
}

