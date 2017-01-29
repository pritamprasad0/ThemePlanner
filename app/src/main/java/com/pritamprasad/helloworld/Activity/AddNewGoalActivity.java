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

public class AddNewGoalActivity extends AppCompatActivity {

    private Button addGoalButton;
    private EditText goalNameEditText;
    private EditText goalDescEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_goal);
        getViews();

    }

    private void getViews() {
        addGoalButton = (Button)findViewById(R.id.addGoalButton);
        goalNameEditText = (EditText)findViewById(R.id.add_goal_activity_name_text);
        goalDescEditText = (EditText)findViewById(R.id.add_goal_activity_desc_text);
    }

    public void addGoalFromForm(View view) {
        Intent data = new Intent();
        data.putExtra(LocalConstants.INTENT_ADD_GOAL_GOAL_NAME,goalNameEditText.getText().toString());
        data.putExtra(LocalConstants.INTENT_ADD_GOAL_GOAL_DESC,goalDescEditText.getText().toString());
        setResult(RESULT_OK, data);
        finish();
    }
}
