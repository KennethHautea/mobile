package com.example.tabulation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class JudgeActivity extends AppCompatActivity {

    EditText etFullname;
    String Fullname;
    int judgeId;
    DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_judge);
        db = new DbHelper(this);
        etFullname = findViewById(R.id.etFullname);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.save_cancel,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.btnSave:
                int success = 1;
                Fullname = etFullname.getText().toString();
                if (Fullname.equals("")){ etFullname.setError("This field is required"); success --;}
                if (success ==1){
                    HashMap<String,String>map_user = new HashMap();
                    map_user.put(db.TBL_JUDGE_FULLNAME,Fullname);
                    judgeId = db.addJudge(map_user);
                    if (judgeId < 1){
                        Toast.makeText(this,"Judge Successfully Added", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(JudgeActivity.this,MainActivity.class));
                    }
                    else{

                        etFullname.setError("Judge already Exists");
                    }
                }
                break;
            case R.id.btnCancel:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
