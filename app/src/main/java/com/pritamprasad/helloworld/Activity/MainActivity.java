package com.pritamprasad.helloworld.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.pritamprasad.helloworld.CustomArrayAdapter.CustomThemeArrayAdapter;
import com.pritamprasad.helloworld.DataBase.DataBaseHandlerImpl;
import com.pritamprasad.helloworld.DataBase.DataBaseHandler;
import com.pritamprasad.helloworld.Model.Goal;
import com.pritamprasad.helloworld.Model.Task;
import com.pritamprasad.helloworld.Model.Theme;
import com.pritamprasad.helloworld.R;
import com.pritamprasad.helloworld.Utility.LocalConstants;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView themeList;
    private Button addNewThemeButton;
    private DataBaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHandler = new DataBaseHandlerImpl(this);
        getViews();
        setInitialData(dbHandler);

        refreshThemeListView();

        themeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent themeIntent = new Intent(MainActivity.this,GoalsActivity.class);
                Theme theme = (Theme) parent.getAdapter().getItem(position);
                Log.d("MainActivity","Clicked Theme Id: "+theme.getId());
                themeIntent.putExtra(LocalConstants.INTENT_GOALSACTIVITY_THEME_ID,theme.getId());
                startActivity(themeIntent);
            }
        });

        addNewThemeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddNewThemeActivity.class);
                startActivityForResult(intent, LocalConstants.REQUESTCODE_ADD_THEME);
            }
        });

    }

    /**
     * To set initial Data to Database
     * @param dbHandler Database handler
     */
    private void setInitialData(DataBaseHandler dbHandler) {
        dbHandler.addTheme(new Theme(1,"First Theme", "Description for first theme",LocalConstants.INVALID_INTEGER_VALUE));
        dbHandler.addTheme(new Theme(2,"Second Theme", "Description for Second theme",LocalConstants.INVALID_INTEGER_VALUE));
        dbHandler.addTheme(new Theme(3,"Third Theme", "Description for Third theme",LocalConstants.INVALID_INTEGER_VALUE));
        dbHandler.addTheme(new Theme(4,"Fourth Theme", "Description for Fourth theme",LocalConstants.INVALID_INTEGER_VALUE));

        dbHandler.addGoal(new Goal(1,"Goal 1.1","Goal 1.1 Description",1));
        dbHandler.addGoal(new Goal(2,"Goal 1.2","Goal 1.2 Description",1));
        dbHandler.addGoal(new Goal(3,"Goal 1.3","Goal 1.3 Description",1));
        dbHandler.addGoal(new Goal(4,"Goal 2.1","Goal 2.1 Description",2));
        dbHandler.addGoal(new Goal(5,"Goal 2.2","Goal 2.2 Description",2));

        dbHandler.addTask(new Task(1,"Task 1.1.1","Task 1 Description",1));
        dbHandler.addTask(new Task(2,"Task 1.1.2","Task 2 Description",1));
        dbHandler.addTask(new Task(3,"Task 1.2.1","Task 3 Description",2));

    }

    /**
     * Get views from FrontEnd
     */
    private void getViews() {
        themeList = (ListView)findViewById(R.id.themeListView);
        addNewThemeButton = (Button) findViewById(R.id.addNewThemeBUtton);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LocalConstants.REQUESTCODE_ADD_THEME) {
            if (resultCode == RESULT_OK) {
                String theme_name = data.getStringExtra(LocalConstants.INTENT_ADD_THEME_THEME_NAME);
                String theme_desc = data.getStringExtra(LocalConstants.INTENT_ADD_THEME_THEME_DESC);

                Log.d("MainActivity","Received theme name : "+theme_name);
                dbHandler.addTheme(new Theme(theme_name,theme_desc));
                refreshThemeListView();
            }
        }
    }

    private void refreshThemeListView() {
        Log.d("MainActivity","Refreshing ListView");
        ArrayList<Theme> list = (ArrayList<Theme>) dbHandler.getAllThemes();
        CustomThemeArrayAdapter adapter = new CustomThemeArrayAdapter(this, R.id.theme_list_item, list);
        themeList.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        dbHandler.close();
    }
}
