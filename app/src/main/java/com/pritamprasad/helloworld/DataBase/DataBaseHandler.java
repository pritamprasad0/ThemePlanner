package com.pritamprasad.helloworld.DataBase;

import com.pritamprasad.helloworld.Model.Goal;
import com.pritamprasad.helloworld.Model.Task;
import com.pritamprasad.helloworld.Model.Theme;

import java.util.List;

/**
 * Database Handler interface
 */
public interface DataBaseHandler {
    /**
     * Add new theme to Database
     * @param theme theme object
     * @return status of insertion
     */
    Boolean addTheme(Theme theme);
    /**
     * Add new goal to Database
     * @param goal goal object
     * @return status of insertion
     */
    Boolean addGoal(Goal goal);
    /**
     * add a new task
     * @param task task object
     * @return staus of insertion
     */
    Boolean addTask(Task task);

    /**
     * Updates an existing theme in DB
     * @param theme theme object to be updated
     * @return status of update
     */
    Boolean updateTheme(Theme theme);
    /**
     * Update an existing goal
     * @param goal
     * @return
     */
    Boolean updateGoal(Goal goal);
    /**
     * Update an existing goal
     * @param task
     * @return
     */
    Boolean updateTask(Task task);

    /**
     * Read a new Theme from Database
     * @param id id of theme
     * @return theme object
     */
    Theme getThemeById(int id);
    /**
     * Read a new Goal from Database
     * @param id goal id
     * @return Goalobject
     */
    Goal getGoalById(int id);
    /**
     * Read a new Goal from Database
     * @param id goal id
     * @return Goalobject
     */
    Task getTaskById(int id);

    /**
     * Get All themes present in DataBase
     * @return List of themes
     */
    List<Theme> getAllThemes();
    /**
     * Get All themes present in DataBase
     * @return List of all goals
     */
    List<Goal> getAllGoals();
    /**
     * Get All themes present in DataBase
     * @return List of all goals
     */
    List<Task> getAllTasks();


    /**
     * Returns list of goals belonging to a theme id
     * @param parent_theme_id parent theme id
     * @return List of goal associated with parent
     */
    List<Goal> getAllGoalsByParentThemeId(int parent_theme_id);
    /**
     * Returns list of task by parent goal
     * @param parentGoalId parent goal id
     */
    List<Task> getAllTasksByParentGoalId(int parentGoalId);

    /**
     * Closes All DB Connection
     */
    void close();
}
