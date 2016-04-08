package cz.ackee.codecamp05.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import cz.ackee.codecamp05.domain.User;

/**
 * Database helper for managing our access operations
 * Created by David Bilik[david.bilik@ackee.cz] on {07/04/16}
 **/
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TAG = DatabaseHelper.class.getName();
    private static final String DB_NAME = "data.db";
    private static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(User.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        Cursor c = getWritableDatabase().query(User.TABLE_NAME, null, null, null, null, null, null);
//        getWritableDatabase().rawQuery("SELECT * FROM USERS WHERE NAME LIKE ?", new String [] {"neco"});
        if (c.moveToFirst()) {
            do {
                String name = c.getString(c.getColumnIndex(User.COL_FIRST_NAME));
                String surname = c.getString(c.getColumnIndex(User.COL_SURNAME));
                int age = c.getInt(c.getColumnIndex(User.COL_AGE));
                result.add(new User(name, surname, age));
            } while (c.moveToNext());
        }
        c.close();
        return result;
    }

    public void insertUser(User user) {
       long id = getWritableDatabase().insert(User.TABLE_NAME, null, user.getContentValues());

    }

    public void updateUser(long userId, User user) {
        getWritableDatabase().update(User.TABLE_NAME, user.getContentValues(), User.COL_ID + " = ?", new String[]{String.valueOf(userId)});
    }


    public void deleteUser(long userId) {
        getWritableDatabase().delete(User.TABLE_NAME, User.COL_ID + " = ?", new String[]{String.valueOf(userId)});
    }
}
