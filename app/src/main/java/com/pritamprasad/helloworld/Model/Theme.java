package com.pritamprasad.helloworld.Model;

import com.pritamprasad.helloworld.Utility.LocalConstants;
import com.pritamprasad.helloworld.Utility.Utility;

import java.util.Date;

/**
 * Theme data model
 */

public class Theme {

    private int id;
    private String theme_name;
    private String theme_description;
    private int current_active_goal_id;
    private Date create_date;

    public Theme(){

    }

    public Theme(int id, String theme_name, String theme_description, int current_active_goal_id, Date create_date) {
        this.id = id;
        this.theme_name = theme_name;
        this.theme_description = theme_description;
        this.current_active_goal_id = current_active_goal_id;
        this.create_date = create_date;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTheme_name() {
        return theme_name;
    }

    public void setTheme_name(String theme_name) {
        this.theme_name = theme_name;
    }

    public String getTheme_description() {
        return theme_description;
    }

    public void setTheme_description(String theme_description) {
        this.theme_description = theme_description;
    }

    public int getCurrent_active_goal_id() {
        return current_active_goal_id;
    }

    public void setCurrent_active_goal_id(int current_active_goal_id) {
        this.current_active_goal_id = current_active_goal_id;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = Utility.StringToDate(create_date);
    }
}
