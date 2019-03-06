package com.netizen.netizenittest.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.netizen.netizenittest.model.StudentInfo;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "contactsManager";

    // Contacts table name
    private static final String TABLE_CONTACTS = "contacts";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_FNAME = "SName";
    private static final String KEY_SESSION = "SSession";
    private static final String KEY_CLASS = "SClass";
    private static final String KEY_EMAIL = "SEmail";
    private static final String KEY_BIRTH = "SBirthDate";
    private static final String KEY_BLOOD = "SBloodGroup";
    private static final String KEY_CONTACT_PERSON = "SContactPersion";
    private static final String KEY_AREA = "SArea";
    private static final String KEY_PHONE = "SPhone";
    private static final String KEY_CITY = "SCity";
    private static final String KEY_PINCODE = "SPinCode";
    private static final String KEY_GENDAR = "SGender";
    private static final String KEY_AGREE = "SAgree";
    private static final String KEY_PHOTO = "SImage";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Create tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_CONTACTS = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_FNAME + " TEXT,"
                + KEY_SESSION + " TEXT,"
                + KEY_CLASS + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_BIRTH + " TEXT,"
                + KEY_BLOOD + " TEXT,"
                + KEY_CONTACT_PERSON + " TEXT,"
                + KEY_AREA + " TEXT,"
                + KEY_PHONE + " TEXT,"
                + KEY_CITY + " TEXT,"
                + KEY_PINCODE + " TEXT,"
                + KEY_GENDAR + " TEXT,"
                + KEY_AGREE + " TEXT,"
                + KEY_PHOTO + " BLOB" + ")";
        db.execSQL(CREATE_TABLE_CONTACTS);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    //Insert values to the table contacts
    public void addContacts(StudentInfo contact) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();


        values.put(KEY_FNAME, contact.getSName());
        values.put(KEY_SESSION, contact.getSSession());
        values.put(KEY_CLASS, contact.getSClass());
        values.put(KEY_EMAIL, contact.getSEmail());
        values.put(KEY_BIRTH, contact.getSBirthDate());
        values.put(KEY_BLOOD, contact.getSBloodGroup());
        values.put(KEY_CONTACT_PERSON, contact.getSContactPersion());
        values.put(KEY_AREA, contact.getSArea());
        values.put(KEY_PHONE, contact.getSPhone());
        values.put(KEY_CITY, contact.getSCity());
        values.put(KEY_PINCODE, contact.getSPinCode());
        values.put(KEY_GENDAR, contact.getSGender());
        values.put(KEY_AGREE, contact.getSAgree());
        values.put(KEY_PHOTO, contact.getSImage());

        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }


    /**
     * Getting All Contacts
     **/

    public ArrayList<StudentInfo> getAllContacts() {
        ArrayList<StudentInfo> contactList = new ArrayList<StudentInfo>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                StudentInfo contact = new StudentInfo();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setSName(cursor.getString(1));
                contact.setSSession(cursor.getString(2));
                contact.setSClass(cursor.getString(3));
                contact.setSEmail(cursor.getString(4));
                contact.setSBirthDate(cursor.getString(5));
                contact.setSBloodGroup(cursor.getString(6));
                contact.setSContactPersion(cursor.getString(7));
                contact.setSArea(cursor.getString(8));
                contact.setSPhone(cursor.getString(9));
                contact.setSCity(cursor.getString(10));
                contact.setSPinCode(cursor.getString(11));
                contact.setSGender(cursor.getString(12));
                contact.setSAgree(cursor.getString(13));
                contact.setSImage(cursor.getBlob(14));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }


    /**
     * Updating single contact
     **/

    public int updateSInfo(StudentInfo contact, int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FNAME, contact.getSPhone());
        values.put(KEY_PHOTO, contact.getSImage());

        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    /**
     * Deleting single contact
     **/

    public void deleteStudentInfo(int Id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[]{String.valueOf(Id)});
        db.close();
    }

}