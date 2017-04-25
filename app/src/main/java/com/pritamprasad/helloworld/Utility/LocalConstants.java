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
     * Priority Constants
     */
    public static final int MIN_PRIORITY_VALUE = 0;
    public static final int MAX_PRIORITY_VALUE = 10;
    public static final int DEFAULT_PRIORITY_VALUE = (MIN_PRIORITY_VALUE + MAX_PRIORITY_VALUE)/2;

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

    /**
     * States
     */
    public enum GOAL_STATES{NEW,ACTIVE,ON_HOLD,DONE};
    public enum TASK_STATES{NEW,IN_PROGRESS,DONE};
    public enum QUADRANT_STATES{ONE,TWO,THREE,FOUR};
}
