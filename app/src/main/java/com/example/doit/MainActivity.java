package com.example.doit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    private static final int ADD_TASK1_REQUEST_CODE = 0;
    private static final int ADD_TASK2_REQUEST_CODE = 1;
    private static final int ADD_TASK3_REQUEST_CODE = 2;

    private static final HashMap<Integer, Integer> requestCodes = new HashMap<Integer, Integer>() {{
       put(R.id.task1Add, ADD_TASK1_REQUEST_CODE);
       put(R.id.task2Add, ADD_TASK2_REQUEST_CODE);
       put(R.id.task3Add, ADD_TASK3_REQUEST_CODE);
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        loadTaskTexts(sharedPreferences);
        loadTaskStates(sharedPreferences);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == ADD_TASK1_REQUEST_CODE) {
                setNewAddedTask(data, R.id.task1Text);
            } else if (requestCode == ADD_TASK2_REQUEST_CODE) {
                setNewAddedTask(data, R.id.task2Text);
            } else if (requestCode == ADD_TASK3_REQUEST_CODE) {
                setNewAddedTask(data, R.id.task3Text);
            }
        }
    }

    public void toggleTask(View view) {
        switch (view.getId()) {
            case (R.id.task1Text):
                CheckBox cb1 = findViewById(R.id.task1Checked);
                cb1.toggle();
                saveTaskState(R.id.task1Checked, cb1.isChecked());
                break;
            case (R.id.task2Text):
                CheckBox cb2 = findViewById(R.id.task2Checked);
                cb2.toggle();
                saveTaskState(R.id.task2Checked, cb2.isChecked());
                break;
            case (R.id.task3Text):
                CheckBox cb3 = findViewById(R.id.task3Checked);
                cb3.toggle();
                saveTaskState(R.id.task3Checked, cb3.isChecked());
                break;
        }
    }

    public void addNewTask(View view) {
        Intent intent = new Intent(this, AddNewTaskActivity.class);
        int requestCode = requestCodes.get(view.getId());
        this.startActivityForResult(intent, requestCode);
    }

    private void setNewAddedTask(@Nullable Intent data,  int taskId) {
            String taskText = data.getStringExtra(AddNewTaskActivity.NEW_TASK);
            TextView textView = findViewById(taskId);
            textView.setText(taskText);

            saveTaskTexts(taskId, taskText);
    }

    private void saveTaskTexts(int key, String value) {
        SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(String.valueOf(key), value);
        editor.commit();
    }

    private void saveTaskState(int key, boolean value) {
        SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(String.valueOf(key), value);
        editor.commit();
    }

    private void loadTaskTexts(SharedPreferences sharedPreferences) {
        String task1Text = sharedPreferences.getString(String.valueOf(R.id.task1Text), "");
        String task2Text = sharedPreferences.getString(String.valueOf(R.id.task2Text), "");
        String task3Text = sharedPreferences.getString(String.valueOf(R.id.task3Text), "");

        TextView task1TextView =  findViewById(R.id.task1Text);
        TextView task2TextView =  findViewById(R.id.task2Text);
        TextView task3TextView =  findViewById(R.id.task3Text);

        task1TextView.setText(task1Text);
        task2TextView.setText(task2Text);
        task3TextView.setText(task3Text);
    }

    private void loadTaskStates(SharedPreferences sharedPreferences) {
        boolean task1Checked = sharedPreferences.getBoolean(String.valueOf(R.id.task1Checked), false);
        boolean task2Checked = sharedPreferences.getBoolean(String.valueOf(R.id.task2Checked), false);
        boolean task3Checked = sharedPreferences.getBoolean(String.valueOf(R.id.task3Checked), false);

        CheckBox task1CheckBox = findViewById(R.id.task1Checked);
        CheckBox task2CheckBox = findViewById(R.id.task2Checked);
        CheckBox task3CheckBox = findViewById(R.id.task3Checked);

        task1CheckBox.setChecked(task1Checked);
        task2CheckBox.setChecked(task2Checked);
        task3CheckBox.setChecked(task3Checked);
    }
}
