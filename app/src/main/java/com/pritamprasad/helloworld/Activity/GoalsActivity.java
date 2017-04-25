package com.pritamprasad.helloworld.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.pritamprasad.helloworld.CustomArrayAdapter.CustomGoalArrayAdapter;
import com.pritamprasad.helloworld.DataBase.DataBaseHandlerImpl;
import com.pritamprasad.helloworld.DataBase.DataBaseHandler;
import com.pritamprasad.helloworld.Model.Goal;
import com.pritamprasad.helloworld.R;
import com.pritamprasad.helloworld.Utility.LocalConstants;

import java.util.ArrayList;

public class GoalsActivity extends AppCompatActivity {

    private final int DEFAULT_PARENT_THEME_VALUE = -1;
    private DataBaseHandler dbHandler = null;
    private ListView goalList;
    private Button addNewGoalButton;
    private CustomGoalArrayAdapter adapter;
    private ArrayList<Goal> list = new ArrayList<>();
    private Intent intent= null;
    int parentThemeId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);
        dbHandler = new DataBaseHandlerImpl(this);
        getViews();
        intent = getIntent();
        parentThemeId = intent.getIntExtra(LocalConstants.INTENT_GOALSACTIVITY_THEME_ID,DEFAULT_PARENT_THEME_VALUE);
        Log.d("GoalsActivity","Got parent Theme Id: "+parentThemeId);
        refreshGoalListView();

        goalList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent goalIntent = new Intent(GoalsActivity.this,TasksActivity.class);
                Goal goal = (Goal) parent.getAdapter().getItem(position);
                Log.d("GoalsActivity","Clicked Theme Id: "+goal.getId());
                goalIntent.putExtra(LocalConstants.INTENT_TASKSACTIVITY_GOAL_ID,goal.getId());
                startActivity(goalIntent);
            }
        });

        addNewGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GoalsActivity.this,AddNewGoalActivity.class);
                startActivityForResult(intent, LocalConstants.REQUESTCODE_ADD_GOAL);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LocalConstants.REQUESTCODE_ADD_GOAL) {
            if (resultCode == RESULT_OK) {
                String goal_name = data.getStringExtra(LocalConstants.INTENT_ADD_GOAL_GOAL_NAME);
                String goal_desc = data.getStringExtra(LocalConstants.INTENT_ADD_GOAL_GOAL_DESC);

                Log.d("GoalsActivity","Received goal name : "+goal_name);
                dbHandler.addGoal(new Goal(goal_name,goal_desc,parentThemeId));
                refreshGoalListView();
            }
        }
    }

    private void refreshGoalListView() {
        Log.d("GoalsActivity","Refreshing ListView");
        list = (ArrayList<Goal>) dbHandler.getAllGoalsByParentThemeId(parentThemeId);
        adapter = new CustomGoalArrayAdapter(this,R.id.goal_list_item,list);
        goalList.setAdapter(adapter);
    }

    private void getViews() {
        goalList = (ListView)findViewById(R.id.goalListView);
        addNewGoalButton = (Button)findViewById(R.id.addNewGoalButton);
    }
}
