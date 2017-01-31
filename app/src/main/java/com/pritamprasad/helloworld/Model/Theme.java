package com.pritamprasad.helloworld.Model;

import com.pritamprasad.helloworld.Utility.LocalConstants;

/**
 * Theme data model
 */

public class Theme {

    private int id = LocalConstants.INVALID_INTEGER_VALUE;
    private String themeName;
    private String themeDesc;
    private int current_active_goal = LocalConstants.INVALID_INTEGER_VALUE;

    public Theme(){

    }

    /**
     * Non-auto-generating fields constructor
     * @param name Theme name
     * @param desc Theme Description
     */
    public Theme(String name, String desc)
    {
        this.themeName = name;
        this.themeDesc = desc;
    }

    public Theme(int id, String name, String desc, int current_active_goal)
    {
        this.id = id;
        this.themeName = name;
        this.themeDesc = desc;
        this.current_active_goal = current_active_goal;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public String getThemeDesc() {
        return themeDesc;
    }

    public void setThemeDesc(String themeDesc) {
        this.themeDesc = themeDesc;
    }

    public int getCurrent_active_goal() {
        return current_active_goal;
    }

    public void setCurrent_active_goal(int current_active_goal) {
        this.current_active_goal = current_active_goal;
    }
}
