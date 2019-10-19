package com.example.doit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.concurrent.TimeUnit;

public class AddNewTaskActivity extends AppCompatActivity {

    public static final String NEW_TASK = "com.example.doit.TASK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_task);
    }

    public void onCancel(View view) {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    public void onAdd(View view) {
        Intent intent = new Intent();
        EditText editText = findViewById(R.id.task);
        String message = editText.getText().toString();

        if (message.equals("")) {
            editText.requestFocus();
        }
        else {
            intent.putExtra(NEW_TASK, message);

            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
