package com.example.tabulation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button addContestant,addjudge,viewContestant,viewJudge,btntabulation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addContestant = findViewById(R.id.addContestant);
        addjudge = findViewById(R.id.addJudges);
        viewContestant = findViewById(R.id.viewContestant);
        viewJudge = findViewById(R.id.viewJudges);
        btntabulation = findViewById(R.id.btnTabulate);

        addContestant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.addContestant:
                        startActivity(new Intent(MainActivity.this,ContestantActivity.class));
                        break;
                }
            }
        });
        viewContestant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.viewContestant:
                        Toast.makeText(MainActivity.this, "ni gawas na", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this,ContestantViewActivity.class));
                        break;
                }
            }
        });
        addjudge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.addJudges:
                        startActivity(new Intent(MainActivity.this,JudgeActivity.class));
                        break;
                }
            }
        });
        viewJudge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.viewJudges:
                        startActivity(new Intent(MainActivity.this,JudgeViewActivity.class));
                        break;

                }
            }
        });
        btntabulation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btnTabulate:
                        startActivity(new Intent(MainActivity.this,TabulationActivity.class));
                        break;
                }
            }
        });
    }

//    public boolean onCreateOptionsMenu(Menu menu){
//        getMenuInflater().inflate(R.menu.menu_create,menu);
//        return super.onCreateOptionsMenu(menu);
//    }
}
