package com.example.tabulation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHelper extends SQLiteOpenHelper {

    public static final String TBL_ADMIN = "admins",
            TBL_ADMIN_ID = "id",
            TBL_ADMIN_USERNAME = "admin username",
            TBL_ADMIN_PASSWORD = "admin password";

    public static final String TBL_JUDGE = "Judge",
            TBL_JUDGE_ID = "judge_id",
            TBL_JUDGE_FULLNAME = "judge_fullname";

    public static final String TBL_CONTESTANT = "Contestant",
            TBL_CONTESTANT_ID = "contestant_id",
            TBL_CONTESTANT_FIRSTNAME = "Contestant_firstname",
            TBL_CONTESTANT_LASTNAME = "Contestant_lastname";

    SQLiteDatabase dbReadable = getReadableDatabase();
    SQLiteDatabase dbWritable = getWritableDatabase();

    public DbHelper(Context context){
        super(context,"TABULATION",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql1 = String.format("CREATE TABLE %s "+ "(%s INTEGER PRIMARY KEY AUTOINCREMENT," + " %s TEXT)",
                TBL_JUDGE,TBL_JUDGE_ID,TBL_JUDGE_FULLNAME);
        db.execSQL(sql1);

        String sql2 = String.format("CREATE TABLE %s"+ "(%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT,"+"%s TEXT)",
                TBL_CONTESTANT,TBL_CONTESTANT_ID,TBL_CONTESTANT_FIRSTNAME,TBL_CONTESTANT_LASTNAME);
        db.execSQL(sql2);

    }
    public int addContestant(HashMap<String,String>map_user){
        ContentValues val = new ContentValues();
        String sql2 = "SELECT * FROM " +TBL_CONTESTANT + " WHERE "
                + TBL_CONTESTANT_FIRSTNAME
                + " = '" + map_user.get(TBL_CONTESTANT_FIRSTNAME)+"'"
                + "AND " + TBL_CONTESTANT_LASTNAME + " = '" + map_user.get(TBL_CONTESTANT_LASTNAME)+"'";
        Cursor cur = dbReadable.rawQuery(sql2,null);
        int userId = 0;
        if (cur.moveToNext()){
            userId = cur.getInt(cur.getColumnIndex(TBL_CONTESTANT_ID));
        }else{
            val.put(TBL_CONTESTANT_FIRSTNAME, map_user.get(TBL_CONTESTANT_FIRSTNAME));
            val.put(TBL_CONTESTANT_LASTNAME, map_user.get(TBL_CONTESTANT_LASTNAME));
            dbWritable.insert(TBL_CONTESTANT,null,val);
        }
        return userId;
    }
    public ArrayList<HashMap<String,String>>getAllUser(){
        String sql2 ="SELECT * FROM " + TBL_CONTESTANT +
                " ORDER BY " + TBL_CONTESTANT_LASTNAME + " ASC ";
        Cursor cur = dbReadable.rawQuery(sql2,null);

        ArrayList<HashMap<String,String>> all_users = new ArrayList();

        while (cur.moveToNext()){
            HashMap<String,String>map_user = new HashMap();
            map_user.put(TBL_CONTESTANT_ID,cur.getString(cur.getColumnIndex(TBL_CONTESTANT_ID)));
            map_user.put(TBL_CONTESTANT_FIRSTNAME,cur.getString(cur.getColumnIndex(TBL_CONTESTANT_FIRSTNAME)));
            map_user.put(TBL_CONTESTANT_LASTNAME,cur.getString(cur.getColumnIndex(TBL_CONTESTANT_LASTNAME)));
        }
        cur.close();
        return all_users;
    }
    public int addJudge(HashMap<String,String>map_user){
        ContentValues val = new ContentValues();
        String sql1 = "SELECT * FROM " + TBL_JUDGE + " WHERE "
                + TBL_JUDGE_FULLNAME
                + " ='" + map_user.get(TBL_JUDGE_FULLNAME)+"'";
        Cursor cur = dbReadable.rawQuery(sql1,null);
        int judgeId = 0;
        if (cur.moveToNext()){
            judgeId = cur.getInt(cur.getColumnIndex(TBL_JUDGE_ID));
        }else{
            val.put(TBL_JUDGE_FULLNAME, map_user.get(TBL_JUDGE_FULLNAME));
            dbWritable.insert(TBL_JUDGE,null,val);
        }
        cur.close();
        return judgeId;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
