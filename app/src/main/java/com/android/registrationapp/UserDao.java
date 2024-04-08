package com.android.registrationapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public static final String TABLE_NAME = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DOB = "dob";
    public static final String COLUMN_EMAIL = "email";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_DOB + " TEXT,"
                    + COLUMN_EMAIL + " TEXT"
                    + ")";

    private DatabaseHelper dbHelper;

    public UserDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public long addUser(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_DOB, user.getDob());
        values.put(COLUMN_EMAIL, user.getEmail());

        long id = db.insert(TABLE_NAME, null, values);

        db.close();

        return id;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                User user = new User(
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_DOB)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL))
                );
                users.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return users;
    }
}
