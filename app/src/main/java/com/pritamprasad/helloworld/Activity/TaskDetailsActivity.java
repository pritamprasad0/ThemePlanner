package com.pritamprasad.helloworld.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.pritamprasad.helloworld.DataBase.DBHandler;
import com.pritamprasad.helloworld.DataBase.DataBaseHandlerInterface;
import com.pritamprasad.helloworld.Model.Task;
import com.pritamprasad.helloworld.R;
import com.pritamprasad.helloworld.Utility.LocalConstants;

public class TaskDetailsActivity extends AppCompatActivity {

    private TextView taskName;
    private TextView taskDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        DataBaseHandlerInterface dbHandler = new DBHandler(this);
        getViews();
        Intent intent = getIntent();
        int DEFAULT_PARENT_TASK_VALUE = -1;
        int taskId = intent.getIntExtra(LocalConstants.INTENT_TASKSDETAILS_ACTIVITY_TASK_ID, DEFAULT_PARENT_TASK_VALUE);
        Log.d("TasksActivity","Got parent Goal Id: "+taskId);

        Task task = dbHandler.getTaskById(taskId);
        taskName.setText(task.getTaskName());
        taskDesc.setText(task.getTaskDesc());
    }

    private void getViews() {
        taskName = (TextView)findViewById(R.id.task_name);
        taskDesc = (TextView)findViewById(R.id.task_desc);
    }

}
