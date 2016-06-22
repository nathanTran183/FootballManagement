package com.example.phuc.footballmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.phuc.footballmanagement.Model.District;
import com.example.phuc.footballmanagement.Model.Field;
import com.example.phuc.footballmanagement.Model.Match;
import com.example.phuc.footballmanagement.Model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Phuc on 4/20/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TAG = "DatabaseHelper";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FootballManagement";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate");
        /*createUserTable(db);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade");
        /*dropUserTable(db);
        onCreate(db);*/
    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && !db.isOpen())
            db.close();
    }

    //    private final String sqlCreateUserTbl = "CREATE TABLE tbl_user (" +
//            "user_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
//            "name TEXT NOT NULL, " +
//            "username TEXT NOT NULL, " +
//            "password TEXT NOT NULL " +
//            ");";
    private final String sqlDropUserTbl = "DROP TABLE IF EXISTS user_profiles";
    private final String sqlGetAllUser = "SELECT * FROM user_profiles";
    private final String sqlUserLogined = "SELECT * FROM user_profiles WHERE username = ? AND password = ?";
    private final String sqlGetUsername = "SELECT * FROM user_profiles WHERE username = ?";

    //Tao bang tbl_user
/*    public void createUserTable(SQLiteDatabase db) {
        db.execSQL(sqlCreateUserTbl);
    }*/

    //Xoa bang user
    public void dropUserTable(SQLiteDatabase db) {
        db.execSQL(sqlDropUserTbl);
    }

    //lay danh sach cac User
    public ArrayList<User> getAllUser() {
        ArrayList<User> lists = new ArrayList<User>();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery(sqlGetAllUser, null);
            if (cursor.moveToFirst()) {
                do {
                    User user = new User();
                    user.setIdUser(cursor.getInt(0));
                    user.setUsername(cursor.getString(1));
                    user.setPassword(cursor.getString(2));
                    user.setEmail(cursor.getString(3));
                    user.setPhone(cursor.getString(4));
                    lists.add(user);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
            Log.e(DatabaseHelper.TAG, e.getMessage());
        }
        return lists;
    }

    //Them User
    public long insertUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = 0;
        try {
            ContentValues values = new ContentValues();
            values.put("username", user.getUsername());
            values.put("password", user.getPassword());
            values.put("email", user.getEmail());
            values.put("phone_number", user.getPhone());
            result = db.insert("user_profiles", null, values);
        } catch (Exception e) {
            Log.e(DatabaseHelper.TAG, e.getMessage());
        }
        return result;
    }

    //Lay User dang dang nhap
    public User getUserLogined(String username, String password) {
        User user = null;
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery(sqlUserLogined, new String[]{username, password});
            if (cursor.moveToFirst()) {
                user = new User();
                user.setIdUser(cursor.getInt(0));
                user.setUsername(cursor.getString(1));
                user.setPassword(cursor.getString(2));
                user.setEmail(cursor.getString(3));
                user.setPhone(cursor.getString(4));
            }
            cursor.close();
        } catch (Exception e) {
            Log.e(DatabaseHelper.TAG, e.getMessage());
        }
        return user;
    }

    //Kiem tra co ton tai user
    public boolean checkUserExisted(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery(sqlGetUsername, new String[]{username});
            if (cursor.moveToFirst()) return true;
        } catch (Exception e) {
            Log.e(DatabaseHelper.TAG, e.getMessage());
        }
        return false;
    }

    private final String sqlGetAllDistricts = "SELECT * FROM districts";

    public ArrayList<District> getAllDistricts() {
        ArrayList<District> lists = new ArrayList<District>();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery(sqlGetAllDistricts, null);
            if (cursor.moveToFirst()) {
                do {
                    District district = new District();
                    district.setId(cursor.getInt(0));
                    district.setName(cursor.getString(1));
                    district.setCityId(cursor.getInt(2));
                    lists.add(district);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
            Log.e(DatabaseHelper.TAG, e.getMessage());
        }
        return lists;
    }

    private final String sqlGetAllMatches = "SELECT * FROM matches";

    public ArrayList getAllMatch() {
        ArrayList list = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery(sqlGetAllMatches, null);
            if (cursor.moveToFirst()) {
                do {
                    Match match = new Match();
                    match.setId(cursor.getInt(0));
                    match.setFieldId(cursor.getInt(1));
                    match.setHostId(cursor.getInt(2));
                    match.setStatus(cursor.getInt(3));
                    match.setMaxPlayer(cursor.getInt(4));
                    match.setPrice(cursor.getInt(5));
                    match.setStart(cursor.getString(6));
                    match.setEnd(cursor.getString(7));
                    match.setIsVerified(cursor.getInt(8)>0);
                    match.setDayCreate(cursor.getString(9));
                    list.add(match);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
            Log.e(DatabaseHelper.TAG, e.getMessage());
        }
        return list;
    }
    public long insertMatch(Match match) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = 0;
        try {
            ContentValues values = new ContentValues();
            values.put("field_id", match.getFieldId());
            values.put("host_id", match.getHostId());
            values.put("status", match.getStatus());
            values.put("maximum_players", match.getMaxPlayer());
            values.put("price", match.getPrice());
            values.put("start_time", match.getStart());
            values.put("end_time", match.getEnd());
            values.put("is_verified",match.isVerified());
            values.put("created",match.getDayCreate());
            result = db.insert("matches", null, values);
        } catch (Exception e) {
            Log.e(DatabaseHelper.TAG, e.getMessage());
        }
        return result;
    }

    private final String sqlGetAllFields = "SELECT * FROM fields";
    private final String sqlGetFIeldName = "SELECT field_name FROM fields WHERE field_id = ?";

    public List<Field> getAllFields() {
        List<Field> list = new ArrayList<Field>();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery(sqlGetAllFields, null);
            if (cursor.moveToFirst()) {
                do {
                    Field field = new Field();
                    field.setId(cursor.getInt(0));
                    field.setName(cursor.getString(1));
                    field.setDistrictId(cursor.getInt(2));
                    field.setAddress(cursor.getString(3));
                    field.setLatitude(cursor.getFloat(4));
                    field.setLongitude(cursor.getFloat(5));
                    field.setPhone(cursor.getString(6));
                    list.add(field);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
            Log.e(DatabaseHelper.TAG, e.getMessage());
        }
        return list;
    }
    public long insertField(Field field) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = 0;
        try {
            ContentValues values = new ContentValues();
            values.put("field_name", field.getName());
            values.put("district_id", field.getDistrictId());
            values.put("address", field.getAddress());
            values.put("latitude", field.getLatitude());
            values.put("longitude", field.getLongitude());
            values.put("phone_number", field.getPhone());
            result = db.insert("fields", null, values);
        } catch (Exception e) {
            Log.e(DatabaseHelper.TAG, e.getMessage());
        }
        return result;
    }
    public String getFieldName(int id){
        String name = "";
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery(sqlGetAllFields, new String[]{id + ""});
            if (cursor.moveToFirst()) {
                do {
                    name = cursor.getString(1);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
            Log.e(DatabaseHelper.TAG, e.getMessage());
        }
        return name;
    }
}
