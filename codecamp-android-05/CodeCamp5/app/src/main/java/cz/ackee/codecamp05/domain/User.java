package cz.ackee.codecamp05.domain;

import android.content.ContentValues;
import android.provider.BaseColumns;

/**
 * User entity
 * Created by David Bilik[david.bilik@ackee.cz] on {07/04/16}
 **/
public class User {
    public static final String TAG = User.class.getName();
    public static final String COL_FIRST_NAME = "first_name";
    public static final String COL_SURNAME = "surname";
    public static final String COL_AGE = "age";
    public static final String COL_ID = BaseColumns._ID;
    public static final String TABLE_NAME = "users";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_FIRST_NAME + " TEXT, " +
            COL_SURNAME + " TEXT, " +
            COL_AGE + ")";


    String name;
    String surname;
    int age;
    long id;

    public User(String name, String surname, int age, long id) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.id = id;
    }

    public User(String name, String surname, int age) {
        this(name, surname, age, -1);
    }


    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public ContentValues getContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(COL_FIRST_NAME, name);
        cv.put(COL_SURNAME, surname);
        cv.put(COL_AGE, age);
        return cv;
    }
}
