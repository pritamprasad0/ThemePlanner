package com.pritamprasad.helloworld.Model;

import com.pritamprasad.helloworld.Utility.LocalConstants;

import java.util.Date;

/**
 * Goal Data Model
 */

public class Goal {
    private int id=-1;
    private String goal_name;
    private String goal_description;
    private int parent_theme_id;
    private int priority_number;
    private LocalConstants.GOAL_STATES state;
    private LocalConstants.QUADRANT_STATES quadrant_state;
    private Date date_created;
    private Date date_started;
    private Date date_finished;
    private int estimated_days;

    public Goal() {

    }

    /**
     *
     * @param id
     * @param goal_name
     * @param goal_description
     * @param parent_theme_id
     * @param priority_number
     * @param state
     * @param quadrant_state
     * @param date_created
     * @param date_started
     * @param date_finished
     * @param estimated_days
     */
    public Goal(int id, String goal_name, String goal_description, int parent_theme_id, int priority_number, LocalConstants.GOAL_STATES state, LocalConstants.QUADRANT_STATES quadrant_state, Date date_created, Date date_started, Date date_finished, int estimated_days) {
        this.id = id;
        this.goal_name = goal_name;
        this.goal_description = goal_description;
        this.parent_theme_id = parent_theme_id;
        this.priority_number = priority_number;
        this.state = state;
        this.quadrant_state = quadrant_state;
        this.date_created = date_created;
        this.date_started = date_started;
        this.date_finished = date_finished;
        this.estimated_days = estimated_days;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoal_name() {
        return goal_name;
    }

    public void setGoal_name(String goal_name) {
        this.goal_name = goal_name;
    }

    public String getGoal_description() {
        return goal_description;
    }

    public void setGoal_description(String goal_description) {
        this.goal_description = goal_description;
    }

    public int getParent_theme_id() {
        return parent_theme_id;
    }

    public void setParent_theme_id(int parent_theme_id) {
        this.parent_theme_id = parent_theme_id;
    }

    public int getPriority_number() {
        return priority_number;
    }

    public void setPriority_number(int priority_number) throws Exception {
        if(priority_number >= LocalConstants.MIN_PRIORITY_VALUE && priority_number <=LocalConstants.MAX_PRIORITY_VALUE){
            this.priority_number = priority_number;
        }else{
            throw new Exception("Set correct priority value:    MIN: "+LocalConstants.MIN_PRIORITY_VALUE + "  MAX: "+LocalConstants.MAX_PRIORITY_VALUE);
        }
    }

    public LocalConstants.GOAL_STATES getState() {
        return state;
    }

    public void setState(LocalConstants.GOAL_STATES state) {
        this.state = state;
    }

    public LocalConstants.QUADRANT_STATES getQuadrant_state() {
        return quadrant_state;
    }

    public void setQuadrant_state(LocalConstants.QUADRANT_STATES quadrant_state) {
        this.quadrant_state = quadrant_state;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public Date getDate_started() {
        return date_started;
    }

    public void setDate_started(Date date_started) {
        this.date_started = date_started;
    }

    public Date getDate_finished() {
        return date_finished;
    }

    public void setDate_finished(Date date_finished) {
        this.date_finished = date_finished;
    }

    public int getEstimated_days() {
        return estimated_days;
    }

    public void setEstimated_days(int estimated_days) {
        this.estimated_days = estimated_days;
    }
}

