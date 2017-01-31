package com.pritamprasad.helloworld.Utility;

/**
 * All constants used in project
 */

public class LocalConstants {
    /**
     * Default DataType Values
     */
    public static final int INVALID_INTEGER_VALUE = -1;
    /**
     *     Database Version
     */
    public static final int DATABASE_VERSION = 1;
    // Database Name
    public static final String DATABASE_NAME = "planner";
    /**
     * THEME Table
     */
    public static final String TABLE_THEME = "theme";
    public static final String KEY_THEME_ID = "theme_id";
    public static final String KEY_THEME_NAME = "theme_name";
    public static final String KEY_THEME_DESC = "theme_desc";
    public static final String KEY_THEME_CURRENT_ACTIVE_GOAL = "theme_current_active_goal";
    /**
     * GOAL Table
     */
    public static final String TABLE_GOAL = "goal";
    public static final String KEY_GOAL_ID = "goal_id";
    public static final String KEY_GOAL_NAME = "goal_name";
    public static final String KEY_GOAL_DESC = "goal_desc";
    public static final String KEY_GOAL_PARENT_THEME_ID = "goal_parent_theme_id";
    /**
     * TASK Table
     */
    public static final String TABLE_TASK = "task";
    public static final String KEY_TASK_ID = "task_id";
    public static final String KEY_TASK_NAME = "task_name";
    public static final String KEY_TASK_DESC = "task_desc";
    public static final String KEY_TASK_PARENT_GOAL_ID = "task_parent_goal_id";

    /**
     * Request Code for statrtActivityForResult
     */
    public static int REQUESTCODE_ADD_THEME = 1111;
    public static int REQUESTCODE_ADD_GOAL = 2222;
    public static int REQUESTCODE_ADD_TASK = 3333;

    /**
     * Add new theme Activity Constants
     */
    public static String INTENT_ADD_THEME_THEME_NAME = "theme_name";
    public static String INTENT_ADD_THEME_THEME_DESC = "theme_desc";

    /**
     * Add new goal Activity Constants
     */
    //public static final String INTENT_ADD_GOAL_PARENT_THEME_ID = "parent_theme_id";
    public static String INTENT_ADD_GOAL_GOAL_NAME = "goal_name";
    public static String INTENT_ADD_GOAL_GOAL_DESC = "goal_desc";

    /**
     * Add new task Activity Constants
     */
    //public static final String INTENT_ADD_GOAL_PARENT_THEME_ID = "parent_theme_id";
    public static String INTENT_ADD_TASK_TASK_NAME = "task_name";
    public static String INTENT_ADD_TASK_TASK_DESC = "task_desc";



    /**
     * Starting New Activity Intent Parameters
     */
    public static String INTENT_GOALSACTIVITY_THEME_ID = "theme_id";
    public static String INTENT_TASKSACTIVITY_GOAL_ID = "goal_id";
    public static String INTENT_TASKSDETAILS_ACTIVITY_TASK_ID = "task_id";
}
