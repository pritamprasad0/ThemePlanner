package com.pritamprasad.helloworld.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pritamprasad.helloworld.CustomArrayAdapter.CustomGoalArrayAdapter;
import com.pritamprasad.helloworld.CustomArrayAdapter.CustomTaskArrayAdapter;
import com.pritamprasad.helloworld.DataBase.DBHandler;
import com.pritamprasad.helloworld.DataBase.DataBaseHandlerInterface;
import com.pritamprasad.helloworld.Model.Goal;
import com.pritamprasad.helloworld.Model.Task;
import com.pritamprasad.helloworld.R;
import com.pritamprasad.helloworld.Utility.LocalConstants;

import java.util.ArrayList;

public class TasksActivity extends AppCompatActivity {

    private final int DEFAULT_PARENT_GOAL_VALUE = -1;
    private DataBaseHandlerInterface dbHandler = null;
    private ListView taskList;
    private CustomTaskArrayAdapter adapter;
    private ArrayList<Task> list = new ArrayList<>();
    private Intent intent= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        dbHandler = new DBHandler(this);
        getViews();
        intent = getIntent();
        int parentGoalId = intent.getIntExtra(LocalConstants.INTENT_TASKSACTIVITY_GOAL_ID,DEFAULT_PARENT_GOAL_VALUE);
        Log.d("TasksActivity","Got parent Goal Id: "+parentGoalId);
        list = (ArrayList<Task>) dbHandler.getAllTasksByParentGoalId(parentGoalId);
        adapter = new CustomTaskArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        taskList.setAdapter(adapter);

        taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent taskIntent = new Intent(TasksActivity.this,TaskDetailsActivity.class);
                Task task = (Task) parent.getAdapter().getItem(position);
                Log.d("MainActivity","Clicked Theme Id: "+task.getId());
                taskIntent.putExtra(LocalConstants.INTENT_TASKSDETAILS_ACTIVITY_TASK_ID,task.getId());
                startActivity(taskIntent);
            }
        });
    }

    private void getViews() {
        taskList = (ListView)findViewById(R.id.taskListView);
    }

}
