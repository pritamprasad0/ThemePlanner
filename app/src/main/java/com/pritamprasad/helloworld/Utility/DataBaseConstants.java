package com.pritamprasad.helloworld.Utility;

/**
 * Created by jarvis on 2/8/17.
 */

public class DataBaseConstants {

    /**
     * Database Version
     */
    public static final int DATABASE_VERSION = 1;
    /**
     * Database Name
     */
    public static final String DATABASE_NAME = "planner";
    /**
     * THEME Table
     */
    public static final String TABLE_THEME = "theme";
    public static final String KEY_THEME_ID = "theme_id";
    public static final String KEY_THEME_NAME = "theme_name";
    public static final String KEY_THEME_DESCRIPTION = "theme_description";
    public static final String KEY_THEME_CURRENT_ACTIVE_GOAL = "theme_current_active_goal";
    public static final String KEY_THEME_CREATE_DATE = "goal_date_created";
    /**
     * GOAL Table
     */
    public static final String TABLE_GOAL = "goal";
    public static final String KEY_GOAL_ID = "goal_id";
    public static final String KEY_GOAL_NAME = "goal_name";
    public static final String KEY_GOAL_DESCRIPTION = "goal_description";
    public static final String KEY_GOAL_PARENT_THEME_ID = "goal_parent_theme_id";
    public static final String KEY_GOAL_PRIORITY = "goal_priority";
    public static final String KEY_GOAL_STATE = "goal_state";
    public static final String KEY_GOAL_QUADRANT_STATE = "goal_quadrant_state";
    public static final String KEY_GOAL_CREATE_DATE = "goal_date_created";
    public static final String KEY_GOAL_START_DATE = "goal_date_started";
    public static final String KEY_GOAL_FINISH_DATE = "goal_date_finished";
    public static final String KEY_GOAL_ESTIMATED_DAYS_REQUIRED = "goal_days_required";
    /**
     * TASK Table
     */
    public static final String TABLE_TASK = "task";
    public static final String KEY_TASK_ID = "task_id";
    public static final String KEY_TASK_NAME = "task_name";
    public static final String KEY_TASK_DESCRIPTION = "task_description";
    public static final String KEY_TASK_PARENT_GOAL_ID = "task_parent_goal_id";
    public static final String KEY_TASK_DEADLINE_DATE = "task_deadline";
    public static final String KEY_TASK_PRIORITY = "task_priority";
    public static final String KEY_TASK_STATE = "task_state";
    public static final String KEY_TASK_IS_DAILY_TASK = "task_daily";
    public static final String KEY_TASK_ESTIMATED_DAYS_REQUIRED = "task_days_required";
    public static final String KEY_TASK_PLANNED_START_DATE = "task_planned_start_date";
    public static final String KEY_TASK_QUADRANT_STATE = "task_quadrant_state";
    public static final String KEY_TASK_CREATE_DATE = "task_date_create";
    public static final String KEY_TASK_START_DATE = "task_date_start";
    public static final String KEY_TASK_FINISH_DATE = "task_date_finish";
}
