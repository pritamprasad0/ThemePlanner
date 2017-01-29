package com.pritamprasad.helloworld.DataBase;

import com.pritamprasad.helloworld.Model.Goal;
import com.pritamprasad.helloworld.Model.Task;
import com.pritamprasad.helloworld.Model.Theme;

import java.util.List;

/**
 * Created by jarvis on 1/28/17.
 */
public interface DataBaseHandlerInterface {
    /**
     * Add new theme to Database
     * @param theme
     * @return
     */
    Boolean addTheme(Theme theme);

    /**
     * Read a new Theme from Database
     * @param id
     * @return
     */
    Theme getThemeById(int id);

    /**
     * Get All themes present in DataBase
     * @return
     */
    List<Theme> getAllThemes();

    /**
     * Add new goal to Database
     * @param goal
     * @return
     */
    Boolean addGoal(Goal goal);

    /**
     * Read a new Goal from Database
     * @param id
     * @return
     */
    Goal getGoalById(int id);

    /**
     * Get All themes present in DataBase
     * @return
     */
    List<Goal> getAllGoals();

    /**
     * Returns list of goals belonging to a theme id
     * @param parent_theme_id
     * @return
     */
    List<Goal> getAllGoalsByParentThemeId(int parent_theme_id);

    /**
     *
     */
    void close();

    /**
     *
     * @param parentGoalId
     */
    List<Task> getAllTasksByParentGoalId(int parentGoalId);

    /**
     *
     * @param task
     * @return
     */
    Boolean addTask(Task task);

    /**
     *
     * @param taskId
     * @return
     */
    Task getTaskById(int taskId);
}
