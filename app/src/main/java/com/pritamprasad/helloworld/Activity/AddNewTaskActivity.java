package com.pritamprasad.helloworld.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.pritamprasad.helloworld.R;
import com.pritamprasad.helloworld.Utility.LocalConstants;

public class AddNewTaskActivity extends AppCompatActivity {

    private Button addTaskButton;
    private EditText taskNameEditText;
    private EditText taskDescEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_task);
        getViews();
    }

    private void getViews() {
        addTaskButton = (Button)findViewById(R.id.addTaskButton);
        taskNameEditText = (EditText)findViewById(R.id.add_task_activity_name_text);
        taskDescEditText = (EditText)findViewById(R.id.add_task_activity_desc_text);
    }

    public void addTaskFromForm(View view) {
        Intent data = new Intent();
        data.putExtra(LocalConstants.INTENT_ADD_TASK_TASK_NAME,taskNameEditText.getText().toString());
        data.putExtra(LocalConstants.INTENT_ADD_TASK_TASK_DESC,taskDescEditText.getText().toString());
        Log.d("AddNewTaskActivity","Sending data with task name : "+taskNameEditText.getText().toString());
        setResult(RESULT_OK, data);
        finish();
    }

}
