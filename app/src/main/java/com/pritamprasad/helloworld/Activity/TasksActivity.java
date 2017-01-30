package com.pritamprasad.helloworld.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.pritamprasad.helloworld.CustomArrayAdapter.CustomTaskArrayAdapter;
import com.pritamprasad.helloworld.DataBase.DBHandler;
import com.pritamprasad.helloworld.DataBase.DataBaseHandlerInterface;
import com.pritamprasad.helloworld.Model.Task;
import com.pritamprasad.helloworld.R;
import com.pritamprasad.helloworld.Utility.LocalConstants;

import java.util.ArrayList;

public class TasksActivity extends AppCompatActivity {

    private DataBaseHandlerInterface dbHandler = null;
    private ListView taskList;
    private Button addNewTaskButton;
    int parentGoalId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        dbHandler = new DBHandler(this);
        getViews();
        Intent intent = getIntent();
        int DEFAULT_PARENT_GOAL_VALUE = -1;
        parentGoalId = intent.getIntExtra(LocalConstants.INTENT_TASKSACTIVITY_GOAL_ID, DEFAULT_PARENT_GOAL_VALUE);
        Log.d("TasksActivity","Got parent Goal Id: "+parentGoalId);
        refreshTaskListView();

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

        addNewTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TasksActivity.this,AddNewTaskActivity.class);
                startActivityForResult(intent, LocalConstants.REQUESTCODE_ADD_TASK);
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LocalConstants.REQUESTCODE_ADD_TASK) {
            if (resultCode == RESULT_OK) {
                String task_name = data.getStringExtra(LocalConstants.INTENT_ADD_TASK_TASK_NAME);
                String task_desc = data.getStringExtra(LocalConstants.INTENT_ADD_TASK_TASK_DESC);

                Log.d("TasksActivity","Received task name : "+task_name);
                dbHandler.addTask(new Task(task_name,task_desc,parentGoalId));
                refreshTaskListView();
            }
        }
    }

    private void refreshTaskListView() {
        ArrayList<Task> list = (ArrayList<Task>) dbHandler.getAllTasksByParentGoalId(parentGoalId);
        CustomTaskArrayAdapter adapter = new CustomTaskArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        taskList.setAdapter(adapter);
    }

    private void getViews() {
        taskList = (ListView)findViewById(R.id.taskListView);
        addNewTaskButton = (Button)findViewById(R.id.addNewTaskButton);
    }

}
