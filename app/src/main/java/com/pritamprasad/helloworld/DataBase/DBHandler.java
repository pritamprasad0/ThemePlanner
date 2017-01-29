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
import com.pritamprasad.helloworld.Utility.LocalConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * This class handles all DataBase Related Operations
 */

public class DBHandler extends SQLiteOpenHelper implements DataBaseHandlerInterface {

    public DBHandler(Context context) {
        super(context, LocalConstants.DATABASE_NAME, null, LocalConstants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            String CreateTableTheme="CREATE TABLE "+ LocalConstants.TABLE_THEME+ "(\n" +
                    LocalConstants.KEY_THEME_ID+ "\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    LocalConstants.KEY_THEME_NAME+ "\tTEXT NOT NULL,\n" +
                    LocalConstants.KEY_THEME_DESC+"\tTEXT\n" +
                    ");";
            db.execSQL(CreateTableTheme);

            String CreateTableGoal="CREATE TABLE "+ LocalConstants.TABLE_GOAL+ "(\n" +
                    LocalConstants.KEY_GOAL_ID+ "\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    LocalConstants.KEY_GOAL_NAME+ "\tTEXT NOT NULL,\n" +
                    LocalConstants.KEY_GOAL_DESC+"\tTEXT,\n" +
                    LocalConstants.KEY_GOAL_PARENT_THEME_ID+"\tINTEGER NOT NULL\n" +
                    ");";
            db.execSQL(CreateTableGoal);

            String CreateTableTask="CREATE TABLE "+ LocalConstants.TABLE_TASK+ "(\n" +
                    LocalConstants.KEY_TASK_ID+ "\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    LocalConstants.KEY_TASK_NAME+ "\tTEXT NOT NULL,\n" +
                    LocalConstants.KEY_TASK_DESC+"\tTEXT,\n" +
                    LocalConstants.KEY_TASK_PARENT_GOAL_ID+"\tINTEGER NOT NULL\n" +
                    ");";
            db.execSQL(CreateTableTask);
        }catch (SQLException sqlex){
            Log.d("DBHandler","CREATE TABLE FAILED !!!");
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * Add new theme to Database
     * @param theme
     * @return
     */
    @Override
    public Boolean addTheme(Theme theme){
        Log.d("DBHandler","Adding theme - name : "+theme.getThemeName());
        SQLiteDatabase db = null;
        Boolean status = true;
        try{
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            if (theme.getId() != -1){
                values.put(LocalConstants.KEY_THEME_ID,theme.getId());
            }
            values.put(LocalConstants.KEY_THEME_NAME,theme.getThemeName());
            values.put(LocalConstants.KEY_THEME_DESC,theme.getThemeDesc());
            db.insert(LocalConstants.TABLE_THEME,null,values);
        }catch (SQLException sqlex){
            status = false;
        }
        finally {
            if(db.isOpen()){
                db.close();
            }
        }
        return status;
    }


    /**
     * Checks if theme already exist in DB or not
     * @param theme_id
     * @return
     */
    private boolean IsThemeExist(int theme_id) {
        if(getThemeById(theme_id) != null){
            return true;
        }
        return false;
    }

    /**
     * Read a new Theme from Database
     * @param id
     * @return
     */
    @Override
    public Theme getThemeById(int id){
        Log.d("DBHandler", "Getting Theme By id : "+ id);
        Theme resultTheme = null;
        SQLiteDatabase db = null;
        Cursor cursor =null;
        try{
            db = this.getReadableDatabase();
            cursor = db.query(LocalConstants.TABLE_THEME, new String[] { LocalConstants.KEY_THEME_ID, LocalConstants.KEY_THEME_NAME, LocalConstants.KEY_THEME_DESC }, LocalConstants.KEY_THEME_ID + "=?",
                    new String[] { String.valueOf(id) }, null, null, null,null);
            if (cursor.moveToFirst()){
                resultTheme = new Theme(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
            }
            else {
                Log.e("DBHandler","No Data Found!!!");
            }
        }catch (SQLException sqlex){
            Log.e("DBHandler",sqlex.getMessage());
        }
//        finally {
//            if(cursor!=null){
//                cursor.close();
//            }
//            if(db != null){
//                db.close();
//            }
//        }
        return resultTheme;
    }

    /**
     * Get All themes present in DataBase
     * @return
     */
    @Override
    public List<Theme> getAllThemes() {
        List<Theme> themeList = new ArrayList<Theme>();
        SQLiteDatabase db=null;
        Cursor cursor = null;
        try{
            String selectQuery = "SELECT * FROM " + LocalConstants.TABLE_THEME;
            db = this.getReadableDatabase();
            cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    Theme theme = new Theme();
                    theme.setId(cursor.getInt(0));
                    theme.setThemeName(cursor.getString(1));
                    theme.setThemeDesc(cursor.getString(2));
                    themeList.add(theme);
                } while (cursor.moveToNext());
            }
        }catch (SQLException sqlex){
            Log.d("DBHandler","Error occured at Get All Themes");
        }
//        finally {
//            if(db!=null){
//                db.close();
//            }
//            if(cursor!=null){
//                cursor.close();
//            }
//        }
        return themeList;
    }

    /**
     * Add new goal to Database
     * @param goal
     * @return
     */
    @Override
    public Boolean addGoal(Goal goal){
        SQLiteDatabase db = null;
        Boolean status = true;
        try{
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            if (goal.getId() != -1){
                values.put(LocalConstants.KEY_GOAL_ID,goal.getId());
            }
            values.put(LocalConstants.KEY_GOAL_NAME,goal.getGoalName());
            values.put(LocalConstants.KEY_GOAL_DESC,goal.getGoalDesc());
            values.put(LocalConstants.KEY_GOAL_PARENT_THEME_ID,goal.getParent_theme_id());

            if(IsThemeExist(goal.getParent_theme_id()))
            {
                db.insert(LocalConstants.TABLE_GOAL,null,values);
            }
            else {
                Log.d("DBHandler","Theme not present, please link goal to a existing theme");
            }

        }catch (SQLException sqlex){
            status = false;
        }
        finally {
            if(db.isOpen()){
                db.close();
            }
        }
        return status;
    }


    /**
     * Read a new Goal from Database
     * @param id
     * @return
     */
    @Override
    public Goal getGoalById(int id){
        Log.d("DBHandler", "Getting Goal By id : "+ id);
        Goal resultGoal = null;
        SQLiteDatabase db = null;
        Cursor cursor =null;
        try{
            db = this.getReadableDatabase();
            cursor = db.query(LocalConstants.TABLE_GOAL, new String[] { LocalConstants.KEY_GOAL_ID, LocalConstants.KEY_GOAL_NAME, LocalConstants.KEY_GOAL_DESC, LocalConstants.KEY_GOAL_PARENT_THEME_ID }, LocalConstants.KEY_GOAL_ID + "=?",
                    new String[] { String.valueOf(id) }, null, null, null,null);
            if (cursor.moveToFirst()){
                resultGoal = new Goal(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3));
            }
            else {
                Log.e("DBHandler","No Goal Data Found!!!");
            }
        }catch (SQLException sqlex){
            Log.e("DBHandler",sqlex.getMessage());
        }
//        finally {
//            if(cursor!=null){
//                cursor.close();
//            }
//            if(db != null){
//                db.close();
//            }
//        }
        return resultGoal;
    }


    /**
     * Get All themes present in DataBase
     * @return
     */
    @Override
    public List<Goal> getAllGoals() {
        List<Goal> goalList = new ArrayList<Goal>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try{
            String selectQuery = "SELECT * FROM " + LocalConstants.TABLE_GOAL;
            db = this.getReadableDatabase();
            cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    Goal goal = new Goal();
                    goal.setId(cursor.getInt(0));
                    goal.setGoalName(cursor.getString(1));
                    goal.setGoalDesc(cursor.getString(2));
                    goal.setParent_theme_id(cursor.getInt(3));
                    goalList.add(goal);
                } while (cursor.moveToNext());
            }
        }catch (SQLException sqlex){
            Log.d("DBHandler","GET ALL goals failed");
        }
//        finally {
//            if(db!=null){
//                db.close();
//            }
//            if(cursor!=null){
//                cursor.close();
//            }
//        }
        return goalList;
    }

    /**
     * Returns list of goals belonging to a theme id
     * @param parent_theme_id
     * @return
     */
    @Override
    public List<Goal> getAllGoalsByParentThemeId(int parent_theme_id){
        Log.d("DBHandler", "Getting all goals in theme id : "+parent_theme_id);
        List<Goal> goalList = new ArrayList<Goal>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try{
            String selectQuery = "SELECT * FROM " + LocalConstants.TABLE_GOAL +" WHERE "+ LocalConstants.KEY_GOAL_PARENT_THEME_ID+"="+parent_theme_id;
            //Log.d("DBHandler","Query Dump: "+selectQuery);
            db = this.getReadableDatabase();
            cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    Goal goal = new Goal();
                    goal.setId(cursor.getInt(0));
                    goal.setGoalName(cursor.getString(1));
                    goal.setGoalDesc(cursor.getString(2));
                    goal.setParent_theme_id(cursor.getInt(3));
                    goalList.add(goal);
                } while (cursor.moveToNext());
            }
        }catch (SQLException sqlex){
            Log.d("DBHandler","GET ALL goals by parent id : "+parent_theme_id+"  failed");
        }
//        finally {
//            if (db != null) {
//                db.close();
//            }
//            if (cursor != null) {
//                cursor.close();
//            }
//        }
        return goalList;
    }

    /**
     * To clean up existing DataBase
     */
    public void eraseWholeDatabase(){

    }

    @Override
    public synchronized void close() {
        super.close();
    }


    @Override
    public Boolean addTask(Task task) {
        SQLiteDatabase db = null;
        Boolean status = true;
        try{
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(LocalConstants.KEY_TASK_ID,task.getId());
            values.put(LocalConstants.KEY_TASK_NAME,task.getTaskName());
            values.put(LocalConstants.KEY_TASK_DESC,task.getTaskDesc());
            values.put(LocalConstants.KEY_TASK_PARENT_GOAL_ID,task.getParent_goal_id());

            if(IsGoalExist(task.getParent_goal_id()))
            {
                db.insert(LocalConstants.TABLE_TASK,null,values);
            }
            else {
                Log.d("DBHandler","Task not present, please link task to a existing goal");
            }

        }catch (SQLException sqlex){
            status = false;
        }
        finally {
            if(db.isOpen()){
                db.close();
            }
        }
        return status;
    }

    @Override
    public Task getTaskById(int taskId) {
        Log.d("DBHandler", "Getting Task By id : "+ taskId);
        Task resultTask = null;
        SQLiteDatabase db = null;
        Cursor cursor =null;
        try{
            db = this.getReadableDatabase();
            cursor = db.query(LocalConstants.TABLE_TASK, new String[] { LocalConstants.KEY_TASK_ID, LocalConstants.KEY_TASK_NAME, LocalConstants.KEY_TASK_DESC, LocalConstants.KEY_TASK_PARENT_GOAL_ID }, LocalConstants.KEY_TASK_ID + "=?",
                    new String[] { String.valueOf(taskId) }, null, null, null,null);
            if (cursor.moveToFirst()){
                resultTask = new Task(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3));
            }
            else {
                Log.e("DBHandler","No Data Found!!!");
            }
        }catch (SQLException sqlex){
            Log.e("DBHandler",sqlex.getMessage());
        }
//        finally {
//            if(cursor!=null){
//                cursor.close();
//            }
//            if(db != null){
//                db.close();
//            }
//        }
        return resultTask;
    }

    private boolean IsGoalExist(int goal_id) {
        if(getThemeById(goal_id) != null){
            return true;
        }
        return false;
    }

    @Override
    public List<Task> getAllTasksByParentGoalId(int parentGoalId) {
        Log.d("DBHandler", "Getting all tasks in goal id : "+parentGoalId);
        List<Task> taskList = new ArrayList<Task>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try{
            String selectQuery = "SELECT * FROM " + LocalConstants.TABLE_TASK +" WHERE "+ LocalConstants.KEY_TASK_PARENT_GOAL_ID+"="+parentGoalId;
            //Log.d("DBHandler","Query Dump: "+selectQuery);
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
            Log.d("DBHandler","GET ALL tasks by parent id : "+parentGoalId+"  failed");
        }
//        finally {
//            if (db != null) {
//                db.close();
//            }
//            if (cursor != null) {
//                cursor.close();
//            }
//        }
        return taskList;
    }

}