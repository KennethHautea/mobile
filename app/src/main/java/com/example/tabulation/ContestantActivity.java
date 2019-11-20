package com.example.tabulation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class ContestantActivity extends AppCompatActivity {

    EditText etFirstname,etLastname;
    String Firstname, Lastname;
    int success, userId;
    SharedPreferences shared;
    DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contestant);
        db = new DbHelper(this);
        shared = getSharedPreferences("Tabulation", Context.MODE_PRIVATE);
        etFirstname = findViewById(R.id.etFnameContestant);
        etLastname = findViewById(R.id.etLnameContestant);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.save_cancel,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.btnSave:
                success = 2;
                Firstname = etFirstname.getText().toString();
                Lastname = etLastname.getText().toString();
                if (Firstname.equals("")||Lastname.equals("")){
                    etFirstname.setError("This Field is Required");
                    etLastname.setError("This Field is Required");
                    success--;
                }
                if(success==2){
                    HashMap<String,String>map_user = new HashMap();
                    map_user.put(db.TBL_CONTESTANT_FIRSTNAME,Firstname);
                    map_user.put(db.TBL_CONTESTANT_LASTNAME,Lastname);
                    userId = db.addContestant(map_user);
                    if (userId < 1){
                        SharedPreferences.Editor editor = shared.edit();
                        editor.putInt(db.TBL_CONTESTANT_ID,userId).commit();
                        Toast.makeText(this,"Contestant Successfully Added",Toast.LENGTH_SHORT).show();
                        //startActivity(new Intent(ContestantActivity.this,MainActivity.class));
                        this.finish();
                    }
                    else{

                        etFirstname.setError("Contestant Already Exists");
                    }

                }
                break;
            case R.id.btnCancel:
                startActivity(new Intent(ContestantActivity.this,MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

}
