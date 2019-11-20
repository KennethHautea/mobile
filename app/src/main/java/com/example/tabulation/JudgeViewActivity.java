package com.example.tabulation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class JudgeViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_judge_view);
        Toast.makeText(this, "ni gawas na", Toast.LENGTH_SHORT).show();
    }
}
