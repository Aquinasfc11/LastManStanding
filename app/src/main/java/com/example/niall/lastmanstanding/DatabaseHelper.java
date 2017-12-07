package com.example.niall.lastmanstanding;

/**
 * Created by Niall on 06/12/2017.
 */




//imports used for the DatabaseHelper
        import android.content.ContentValues;
        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.database.Cursor;
        import android.util.Log;
        import java.util.ArrayList;
        import java.util.List;


/**
 * Created by Niall on 07/12/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    //Database Name
    public static final String DATABASE_NAME = "LMS.db";
    //Table names
    public static final String TABLE_NAME = "users_table";

    //Column Names users table
    public static final String COL_1 = "userID";
    public static final String COL_2 = "USERNAME";
    public static final String COL_3 = "PASSWORD";
    public static final String COL_4 = "EMAIL";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 3);

    }

    //Creation of the tables and their column names and type value
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (userID INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME TEXT,PASSWORD TEXT, EMAIL TEXT)");
    }

    //Upgrade statement when a change has been made to the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        //Creation of new version
        onCreate(db);
    }

    //Insert method to insert username, password and email from the registration and store in the users table
    public boolean insertData(String username, String password, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, username);
        contentValues.put(COL_3, password);
        contentValues.put(COL_4, email);
//Insert into users table
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    //Check to see if password and username equal the password and username in the database
    public Integer searchUserID(String USERNAME) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT userID, USERNAME FROM users_table";
        //Cursor declared to point to correct column in database
        Cursor cursor = db.rawQuery(query, null);
//Variables for username, userID
        String username;
        Integer userID;

        userID = 0;
        if (cursor.moveToFirst()) {
            do {
                username = cursor.getString(1);
                if (username.equals(USERNAME))

                {
                    userID = cursor.getInt(0);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return userID;
    }


    //Check to see if password and username equal the password and username in the database
    public String searchPass(String USERNAME) {
        SQLiteDatabase db = this.getReadableDatabase();
        //SQl statement to select username and password from users_table
        String query = "SELECT USERNAME, PASSWORD FROM users_table";
        //Cursor to get location of coloumn in database
        Cursor cursor = db.rawQuery(query, null);

    //Variables username and password
        String user, pass;
        pass = "No match";
        if (cursor.moveToFirst()) {
            do {

                user = cursor.getString(0);
                if (user.equals(USERNAME))

                {
                    pass = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return pass;
    }
}

























