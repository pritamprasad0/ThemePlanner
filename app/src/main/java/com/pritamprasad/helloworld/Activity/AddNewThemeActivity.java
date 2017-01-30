package com.pritamprasad.helloworld.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.pritamprasad.helloworld.R;
import com.pritamprasad.helloworld.Utility.LocalConstants;

public class AddNewThemeActivity extends AppCompatActivity {

    private Button addThemeButton;
    private EditText themeNameEditText;
    private EditText themeDescEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_theme);
        getViews();
    }

    private void getViews() {
        addThemeButton = (Button)findViewById(R.id.addThemeButton);
        themeNameEditText = (EditText)findViewById(R.id.add_theme_activity_name_text);
        themeDescEditText = (EditText)findViewById(R.id.add_theme_activity_desc_text);
    }

    public void addThemeFromForm(View view) {
        Intent data = new Intent();
        data.putExtra(LocalConstants.INTENT_ADD_THEME_THEME_NAME,themeNameEditText.getText().toString());
        data.putExtra(LocalConstants.INTENT_ADD_THEME_THEME_DESC,themeDescEditText.getText().toString());
        setResult(RESULT_OK, data);
        finish();
    }
}
