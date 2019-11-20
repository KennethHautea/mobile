package com.example.tabulation;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;

public class ContestantViewActivity extends AppCompatActivity {

    DbHelper db;
    ListView lvContestant;
    ListViewAdapter adapter;
    ArrayList<HashMap<String,String>>all_users;
    SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contestant_view);
        db = new DbHelper(this);
        shared = getSharedPreferences("Tabulation",Context.MODE_PRIVATE);
        lvContestant = findViewById(R.id.lvContestant);
        fetch_users();
    }

    private void fetch_users() {
        all_users = db.getAllUser();
        adapter = new ListViewAdapter(this,R.layout.adapter_contestant,all_users);
        lvContestant.setAdapter(adapter);
        registerForContextMenu(lvContestant);
    }

    private class ListViewAdapter extends ArrayAdapter {
        LayoutInflater inflater;
        TextView tvFirstname, tvLastname;

        ArrayList<HashMap<String,String>> all_users;
        public ListViewAdapter(Context context,int resource, ArrayList<HashMap<String,String>>all_users){
            super(context,resource,all_users);

            inflater = LayoutInflater.from(context);
            this.all_users = all_users;
        }
        public View getView(final int position, View convertView, ViewGroup parent){
            convertView = inflater.inflate(R.layout.adapter_contestant,parent,false);
            tvFirstname = convertView.findViewById(R.id.tvFirstname);
            tvLastname = convertView.findViewById(R.id.tvLastname);

            tvFirstname.setText(all_users.get(position).get(db.TBL_CONTESTANT_FIRSTNAME));
            tvLastname.setText(all_users.get(position).get(db.TBL_CONTESTANT_LASTNAME));
            fetch_users();
            return convertView;
        }

    }
}
