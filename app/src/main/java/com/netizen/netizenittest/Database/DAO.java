/*
package com.netizen.netizenittest.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.netizen.netizenittest.app.AppController;
import com.netizen.netizenittest.model.StudentInfo;

import java.util.ArrayList;

import static com.netizen.netizenittest.Database.DBHelper.TABLE_STUDENT_INFO;

public class DAO {
    private static final String TAG = DAO.class.getSimpleName();

    // Database fields
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public DAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    protected void finalize() throws Throwable {
        // TODO Auto-generated method stub
        if (db != null && db.isOpen())
            db.close();
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Cursor getRecordsCursor(String sql, String[] param) {
        Cursor curs = null;
        curs = db.rawQuery(sql, param);
        return curs;
    }


    public void execSQL(String sql, String[] param) throws SQLException {
        db.execSQL(sql, param);
    }

    public static void executeSQL(String sql, String[] param) {
        DAO da = new DAO(AppController.getInstance());
        da.open();
        try {
            da.execSQL(sql, param);
        } catch (Exception e) {
            throw e;
        } finally {
            da.close();
        }
    }

    public ArrayList<StudentInfo> gettingAllStudentInfo() {
        ArrayList<StudentInfo> itemArrayList = new ArrayList<StudentInfo>();
        StudentInfo studentInfo = null;
        Cursor curs = null;

        try {
            curs = db.query(TABLE_STUDENT_INFO, new String[] {"[SName]",
                            "[SImage]", "[SEmail]", "[SBirthDate]", "[SBloodGroup]",
                            "[SContactPersion]", "[SArea]", "[SPhone]",
                            "[SCity]", "[SPinCode]", "[SGender]", "[SAgree]"},
                    null, null, null, null, null);

            if (curs.moveToFirst()) {
                do {
                    studentInfo = new StudentInfo(curs.getString(0),
                            curs.getBlob(1), curs.getString(2), curs.getString(3), curs.getString(4),
                            curs.getString(5), curs.getString(6), curs.getString(7), curs.getString(8),
                            curs.getString(9), curs.getString(10), curs.getString(11));
                    itemArrayList.add(studentInfo);
                } while (curs.moveToNext());
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.e(TAG, e.toString());

        } finally {
            if (curs != null)
                curs.close();
        }
        return itemArrayList;
    }

  */
/*  public StudentInfo getUserDetails() {
        String selectQuery = "SELECT * \n" +
                "    FROM    user\n" +
                "    WHERE   user_id = (SELECT MAX(user_id)  FROM user);";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        User user = new User();
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                user.setName(cursor.getString(1));
                user.setProfession(cursor.getString(2));
                user.setDateOfBirth(cursor.getString(3));
                user.setPassportNo(cursor.getString(4));
                user.setNationalId(cursor.getString(5));
                user.setEmail(cursor.getString(6));
                user.setPhone(cursor.getString(7));
                user.setAddress(cursor.getString(8));
                user.setGender(cursor.getString(9));
                user.setPicturePath(cursor.getString(10));



            } while (cursor.moveToNext());
        }

        return  user;

    }*//*

}
*/
