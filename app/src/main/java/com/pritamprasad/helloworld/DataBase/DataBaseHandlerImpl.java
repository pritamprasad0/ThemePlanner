package com.pritamprasad.helloworld.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.pritamprasad.helloworld.Model.Goal;
import com.pritamprasad.helloworld.Model.Task;
import com.pritamprasad.helloworld.Model.Theme;
import com.pritamprasad.helloworld.Utility.DataBaseConstants;
import com.pritamprasad.helloworld.Utility.LocalConstants;
import com.pritamprasad.helloworld.Utility.Utility;

import java.util.ArrayList;
import java.util.List;

/**
 * This class handles all DataBase Related Operations
 */

public class DataBaseHandlerImpl extends SQLiteOpenHelper implements DataBaseHandler {

    public DataBaseHandlerImpl(Context context) {
        super(context, DataBaseConstants.DATABASE_NAME, null, DataBaseConstants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            String CreateTableTheme="CREATE TABLE "+ DataBaseConstants.TABLE_THEME+ "(\n" +
                    DataBaseConstants.KEY_THEME_ID+ "\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    DataBaseConstants.KEY_THEME_NAME+ "\tTEXT NOT NULL,\n" +
                    DataBaseConstants.KEY_THEME_DESCRIPTION +"\tTEXT,\n" +
                    DataBaseConstants.KEY_THEME_CURRENT_ACTIVE_GOAL+ "\tINTEGER\n" +
                    DataBaseConstants.KEY_THEME_CREATE_DATE + "\tTEXT,\n"+
                    ");";
            db.execSQL(CreateTableTheme);

            String CreateTableGoal="CREATE TABLE "+ DataBaseConstants.TABLE_GOAL+ "(\n" +
                    DataBaseConstants.KEY_GOAL_ID+ "\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    DataBaseConstants.KEY_GOAL_NAME+ "\tTEXT NOT NULL,\n" +
                    DataBaseConstants.KEY_GOAL_DESCRIPTION +"\tTEXT,\n" +
                    DataBaseConstants.KEY_GOAL_PARENT_THEME_ID+"\tINTEGER NOT NULL\n" +
                    DataBaseConstants.KEY_GOAL_PRIORITY +"\tINTEGER,\n" +
                    DataBaseConstants.KEY_GOAL_STATE +"\tINTEGER,\n" +
                    DataBaseConstants.KEY_GOAL_QUADRANT_STATE +"\tINTEGER,\n" +
                    DataBaseConstants.KEY_GOAL_CREATE_DATE +"\tTEXT,\n" +
                    DataBaseConstants.KEY_GOAL_START_DATE +"\tTEXT,\n" +
                    DataBaseConstants.KEY_GOAL_FINISH_DATE +"\tTEXT,\n" +
                    DataBaseConstants.KEY_GOAL_ESTIMATED_DAYS_REQUIRED +"\tINTEGER,\n" +
                    ");";
            db.execSQL(CreateTableGoal);

            String CreateTableTask="CREATE TABLE "+ DataBaseConstants.TABLE_TASK+ "(\n" +
                    DataBaseConstants.KEY_TASK_ID+ "\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    DataBaseConstants.KEY_TASK_NAME+ "\tTEXT NOT NULL,\n" +
                    DataBaseConstants.KEY_TASK_DESCRIPTION +"\tTEXT,\n" +
                    DataBaseConstants.KEY_TASK_PARENT_GOAL_ID+"\tINTEGER NOT NULL\n" +
                    DataBaseConstants.KEY_TASK_DEADLINE_DATE +"\tTEXT,\n" +
                    DataBaseConstants.KEY_TASK_PRIORITY +"\tINTEGER,\n" +
                    DataBaseConstants.KEY_TASK_STATE +"\tINTEGER,\n" +
                    DataBaseConstants.KEY_TASK_IS_DAILY_TASK +"\tINTEGER,\n" +
                    DataBaseConstants.KEY_TASK_ESTIMATED_DAYS_REQUIRED +"\tINTEGER,\n" +
                    DataBaseConstants.KEY_TASK_PLANNED_START_DATE +"\tTEXT,\n" +
                    DataBaseConstants.KEY_TASK_QUADRANT_STATE +"\tINTEGER,\n" +
                    DataBaseConstants.KEY_TASK_CREATE_DATE +"\tTEXT,\n" +
                    DataBaseConstants.KEY_TASK_START_DATE +"\tTEXT,\n" +
                    DataBaseConstants.KEY_TASK_FINISH_DATE +"\tTEXT,\n" +
                    ");";
            db.execSQL(CreateTableTask);
        }catch (SQLException sqlex){
            Log.d("DataBaseHandlerImpl","CREATE TABLE FAILED !!!");
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseConstants.TABLE_THEME);
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseConstants.TABLE_GOAL);
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseConstants.TABLE_TASK);
        onCreate(db);
    }

    @Override
    public Boolean addTheme(Theme theme){
        Log.d("DataBaseHandlerImpl","Adding theme - name : "+theme.getTheme_name());
        SQLiteDatabase db = null;
        Boolean status = true;
        try{
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            if (theme.getId() != LocalConstants.INVALID_INTEGER_VALUE){
                values.put(DataBaseConstants.KEY_THEME_ID,theme.getId());
            }
            values.put(DataBaseConstants.KEY_THEME_NAME,theme.getTheme_name());
            values.put(DataBaseConstants.KEY_THEME_DESCRIPTION,theme.getTheme_description());
            if (theme.getCurrent_active_goal_id() != LocalConstants.INVALID_INTEGER_VALUE){
                values.put(DataBaseConstants.KEY_THEME_CURRENT_ACTIVE_GOAL,theme.getCurrent_active_goal_id());
            }
            values.put(DataBaseConstants.KEY_THEME_CREATE_DATE,theme.getCreate_date().toString());
            db.insert(DataBaseConstants.TABLE_THEME,null,values);
        }catch (SQLException sqlex){
            status = false;
        }
        finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
        return status;
    }

    @Override
    public Boolean addGoal(Goal goal){
        SQLiteDatabase db = null;
        Boolean status = true;
        try{
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            if (goal.getId() != -1){
                values.put(DataBaseConstants.KEY_GOAL_ID,goal.getId());
            }
            values.put(DataBaseConstants.KEY_GOAL_NAME,goal.getGoal_name());
            values.put(DataBaseConstants.KEY_GOAL_DESCRIPTION,goal.getGoal_description());
            values.put(DataBaseConstants.KEY_GOAL_PARENT_THEME_ID,goal.getParent_theme_id());
            values.put(DataBaseConstants.KEY_GOAL_PRIORITY,goal.getPriority_number());
            values.put(DataBaseConstants.KEY_GOAL_STATE, Utility.goalStateEnumToInt(goal.getState()));
            values.put(DataBaseConstants.KEY_GOAL_QUADRANT_STATE, Utility.quadrantStateEnumToInt(goal.getQuadrant_state()));
            values.put(DataBaseConstants.KEY_GOAL_CREATE_DATE, goal.getDate_created().toString());
            values.put(DataBaseConstants.KEY_GOAL_START_DATE, goal.getDate_started().toString());
            values.put(DataBaseConstants.KEY_GOAL_FINISH_DATE, goal.getDate_finished().toString());
            values.put(DataBaseConstants.KEY_GOAL_ESTIMATED_DAYS_REQUIRED,goal.getEstimated_days());
            if(IsThemeExist(goal.getParent_theme_id()))
            {
                db.insert(DataBaseConstants.TABLE_GOAL,null,values);
            }
            else {
                Log.d("DataBaseHandlerImpl","Theme not present, please link goal to a existing theme");
            }

        }catch (SQLException sqlex){
            status = false;
        }
        finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
        return status;
    }

    @Override
    public Boolean addTask(Task task) {
        Log.d("DataBaseHandlerImpl","Adding task : "+task.getTaskName());
        SQLiteDatabase db = null;
        Boolean status = true;
        try{
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            if (task.getId() != -1){
                values.put(DataBaseConstants.KEY_TASK_ID,task.getId());
            }
            values.put(DataBaseConstants.KEY_TASK_NAME,task.getTaskName());
            values.put(DataBaseConstants.KEY_TASK_DESCRIPTION,task.getTaskDesc());
            values.put(DataBaseConstants.KEY_TASK_PARENT_GOAL_ID,task.getParent_goal_id());


            if(IsGoalExist(task.getParent_goal_id()))
            {
                db.insert(DataBaseConstants.TABLE_TASK,null,values);
            }
            else {
                Log.d("DataBaseHandlerImpl","Task not present, please link task to a existing goal");
            }

        }catch (SQLException sqlex){
            status = false;
        }
        finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
        return status;
    }

    /**
     * Updates an existing theme in DB
     *
     * @param theme theme object to be updated
     * @return status of update
     */
    @Override
    public Boolean updateTheme(Theme theme) {
        Log.d("DataBaseHandlerImpl","Updating theme - name : "+theme.getTheme_name());
        SQLiteDatabase db = null;
        Boolean status = true;
        try{
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            if (theme.getId() != LocalConstants.INVALID_INTEGER_VALUE){
                values.put(DataBaseConstants.KEY_THEME_ID,theme.getId());
            }
            values.put(DataBaseConstants.KEY_THEME_NAME,theme.getTheme_name());
            values.put(DataBaseConstants.KEY_THEME_DESCRIPTION,theme.getTheme_description());
            if (theme.getCurrent_active_goal_id() != LocalConstants.INVALID_INTEGER_VALUE){
                values.put(DataBaseConstants.KEY_THEME_CURRENT_ACTIVE_GOAL,theme.getCurrent_active_goal_id());
            }
            db.update(DataBaseConstants.TABLE_THEME,values,DataBaseConstants.KEY_THEME_ID + "=?",
                    new String[]{String.valueOf(theme.getId())});
        }catch (SQLException sqlex){
            status = false;
        }
        finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
        return status;
    }

    /**
     * Update an existing goal
     *
     * @param goal
     * @return
     */
    @Override
    public Boolean updateGoal(Goal goal) {
        return null;
    }

    /**
     * Update an existing goal
     *
     * @param task
     * @return
     */
    @Override
    public Boolean updateTask(Task task) {
        return null;
    }


    /**
     * Read a new Theme from Database
     * @param id id of theme to query
     * @return theme object having id as id
     */
    @Override
    public Theme getThemeById(int id){
        Log.d("DataBaseHandlerImpl", "Getting Theme By id : "+ id);
        Theme resultTheme = null;
        SQLiteDatabase db;
        Cursor cursor = null;
        try{
            db = this.getReadableDatabase();
            cursor = db.query(DataBaseConstants.TABLE_THEME, new String[] { DataBaseConstants.KEY_THEME_ID,
                    DataBaseConstants.KEY_THEME_NAME, DataBaseConstants.KEY_THEME_DESCRIPTION,DataBaseConstants.KEY_THEME_CURRENT_ACTIVE_GOAL },
                    DataBaseConstants.KEY_THEME_ID + "=?",
                    new String[] { String.valueOf(id) }, null, null, null,null);
            if (cursor.moveToFirst()) {
                resultTheme = new Theme(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                        cursor.getInt(3));
            }
            else {
                Log.e("DataBaseHandlerImpl","No Data Found!!!");
            }
        }catch (SQLException sqlex){
            Log.e("DataBaseHandlerImpl",sqlex.getMessage());
        }
        finally {
            if(cursor!=null){
                cursor.close();
            }
//            if(db != null){
//                db.close();
//            }
        }
        return resultTheme;
    }

    /**
     * Read a new Goal from Database
     * @param id id of Goal
     * @return Goal associated with id
     */
    @Override
    public Goal getGoalById(int id){
        Log.d("DataBaseHandlerImpl", "Getting Goal By id : "+ id);
        Goal resultGoal = null;
        SQLiteDatabase db;
        Cursor cursor =null;
        try{
            db = this.getReadableDatabase();
            cursor = db.query(DataBaseConstants.TABLE_GOAL, new String[] { DataBaseConstants.KEY_GOAL_ID, DataBaseConstants.KEY_GOAL_NAME, DataBaseConstants.KEY_GOAL_DESCRIPTION, DataBaseConstants.KEY_GOAL_PARENT_THEME_ID }, DataBaseConstants.KEY_GOAL_ID + "=?",
                    new String[] { String.valueOf(id) }, null, null, null,null);
            if (cursor.moveToFirst()){
                resultGoal = new Goal(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3));
            }
            else {
                Log.e("DataBaseHandlerImpl","No Goal Data Found!!!");
            }
        }catch (SQLException sqlex){
            Log.e("DataBaseHandlerImpl",sqlex.getMessage());
        }
        finally {
            if(cursor!=null){
                cursor.close();
            }
//            if(db != null){
//                db.close();
//            }
        }
        return resultGoal;
    }

    @Override
    public Task getTaskById(int taskId) {
        Log.d("DataBaseHandlerImpl", "Getting Task By id : "+ taskId);
        Task resultTask = null;
        SQLiteDatabase db;
        Cursor cursor =null;
        try{
            db = this.getReadableDatabase();
            cursor = db.query(DataBaseConstants.TABLE_TASK, new String[] { DataBaseConstants.KEY_TASK_ID, DataBaseConstants.KEY_TASK_NAME, DataBaseConstants.KEY_TASK_DESCRIPTION, DataBaseConstants.KEY_TASK_PARENT_GOAL_ID }, DataBaseConstants.KEY_TASK_ID + "=?",
                    new String[] { String.valueOf(taskId) }, null, null, null,null);
            if (cursor.moveToFirst()){
                resultTask = new Task(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3));
            }
            else {
                Log.e("DataBaseHandlerImpl","No Data Found!!!");
            }
        }catch (SQLException sqlex){
            Log.e("DataBaseHandlerImpl",sqlex.getMessage());
        }
        finally {
            if(cursor!=null){
                cursor.close();
            }
//            if(db != null){
//                db.close();
//            }
        }
        return resultTask;
    }


    /**
     * Get All themes present in DataBase
     * @return List of all themes in DB
     */
    @Override
    public List<Theme> getAllThemes() {
        List<Theme> themeList = new ArrayList<>();
        SQLiteDatabase db;
        Cursor cursor = null;
        try{
            String selectQuery = "SELECT * FROM " + DataBaseConstants.TABLE_THEME;
            db = this.getReadableDatabase();
            cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    Theme theme = new Theme();
                    theme.setId(cursor.getInt(0));
                    theme.setTheme_name(cursor.getString(1));
                    theme.setTheme_description(cursor.getString(2));
                    theme.setCurrent_active_goal_id(cursor.getInt(3));
                    themeList.add(theme);
                } while (cursor.moveToNext());
            }
        }catch (SQLException sqlexception){
            Log.d("DataBaseHandlerImpl","Error occurred at Get All Themes");
        }
        finally {
//            if(db!=null){
//                db.close();
//            }
            if(cursor!=null){
                cursor.close();
            }
        }
        return themeList;
    }







    /**
     * Get All themes present in DataBase
     * @return List of all goals in DB
     */
    @Override
    public List<Goal> getAllGoals() {
        List<Goal> goalList = new ArrayList<>();
        SQLiteDatabase db;
        Cursor cursor = null;
        try{
            String selectQuery = "SELECT * FROM " + DataBaseConstants.TABLE_GOAL;
            db = this.getReadableDatabase();
            cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    Goal goal = new Goal();
                    goal.setId(cursor.getInt(0));
                    goal.setGoal_name(cursor.getString(1));
                    goal.setGoal_description(cursor.getString(2));
                    goal.setParent_theme_id(cursor.getInt(3));
                    goalList.add(goal);
                } while (cursor.moveToNext());
            }
        }catch (SQLException sqlex){
            Log.d("DataBaseHandlerImpl","GET ALL goals failed");
        }
        finally {
//            if(db!=null){
//                db.close();
//            }
            if(cursor!=null){
                cursor.close();
            }
        }
        return goalList;
    }

    /**
     * Get All themes present in DataBase
     *
     * @return List of all goals
     */
    @Override
    public List<Task> getAllTasks() {
        return null;
    }

    /**
     * Returns list of goals belonging to a theme id
     * @param parent_theme_id id of parent theme
     * @return List of goals associated with parent theme id
     */
    @Override
    public List<Goal> getAllGoalsByParentThemeId(int parent_theme_id){
        Log.d("DataBaseHandlerImpl", "Getting all goals in theme id : "+parent_theme_id);
        List<Goal> goalList = new ArrayList<>();
        SQLiteDatabase db;
        Cursor cursor = null;
        try{
            String selectQuery = "SELECT * FROM " + DataBaseConstants.TABLE_GOAL +" WHERE "+ DataBaseConstants.KEY_GOAL_PARENT_THEME_ID+"="+parent_theme_id;
            //Log.d("DataBaseHandlerImpl","Query Dump: "+selectQuery);
            db = this.getReadableDatabase();
            cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    Goal goal = new Goal();
                    goal.setId(cursor.getInt(0));
                    goal.setGoal_name(cursor.getString(1));
                    goal.setGoal_description(cursor.getString(2));
                    goal.setParent_theme_id(cursor.getInt(3));
                    goalList.add(goal);
                } while (cursor.moveToNext());
            }
        }catch (SQLException sqlex){
            Log.d("DataBaseHandlerImpl","GET ALL goals by parent id : "+parent_theme_id+"  failed");
        }
        finally {
//            if (db != null) {
//                db.close();
//            }
            if (cursor != null) {
                cursor.close();
            }
        }
        return goalList;
    }


    @Override
    public List<Task> getAllTasksByParentGoalId(int parentGoalId) {
        Log.d("DataBaseHandlerImpl", "Getting all tasks in goal id : "+parentGoalId);
        List<Task> taskList = new ArrayList<>();
        SQLiteDatabase db;
        Cursor cursor = null;
        try{
            String selectQuery = "SELECT * FROM " + DataBaseConstants.TABLE_TASK +" WHERE "+ DataBaseConstants.KEY_TASK_PARENT_GOAL_ID+"="+parentGoalId;
            //Log.d("DataBaseHandlerImpl","Query Dump: "+selectQuery);
            db = this.getReadableDatabase();
            cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    Task task = new Task();
                    task.setId(cursor.getInt(0));
                    task.setTaskName(cursor.getString(1));
                    task.setTaskDesc(cursor.getString(2));
                    task.setParent_goal_id(cursor.getInt(3));
                    taskList.add(task);
                } while (cursor.moveToNext());
            }
        }catch (SQLException sqlex){
            Log.d("DataBaseHandlerImpl","GET ALL tasks by parent id : "+parentGoalId+"  failed");
        }
        finally {
//            if (db != null) {
//                db.close();
//            }
            if (cursor != null) {
                cursor.close();
            }
        }
        return taskList;
    }

    /**
     * Checks if theme already exist in DB or not
     * @param theme_id id of theme to check in DB
     * @return if queried theme exist or not
     */
    private boolean IsThemeExist(int theme_id) {
        return getThemeById(theme_id) != null;
    }


    private boolean IsGoalExist(int goal_id) {
        return getGoalById(goal_id) != null;
    }

    @Override
    public synchronized void close() {
        super.close();
    }
}
