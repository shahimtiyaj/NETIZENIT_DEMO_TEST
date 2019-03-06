/*
package com.netizen.netizenittest.Database;


import android.content.Context;
import android.content.res.AssetManager;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = SQLiteOpenHelper.class.getSimpleName();

    final static int DB_VERSION = 1;
    final static String DB_NAME = "student.ndb";

    public static final String TABLE_STUDENT_INFO = "StudentInfo";

    // TBL_NOTIFICATION SINGLE field names----------------------------------
    private final static String FLD_STUDENT_INFO_ID = "[id]";
    private final static String FLD_STUDENT_INFO="[SName]";
    private final static String FLD_STUDENT_INFO_IMAGE="[SImage]";

    Context context;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        // Store the context for later use
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        try {
            executeSQLScript(db, "create.sql");
        } catch (SQLException e) {
        } catch (IOException e) {
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {
            if (newVersion > oldVersion) {
                switch (oldVersion) {
                    case 1:
                         executeSQLScript(db, "update_v2.sql");
                }
            }
        } catch (SQLException e) {
        } catch (IOException e) {
        }
    }

    private void executeSQLScript(SQLiteDatabase database, String dbname) throws IOException, SQLException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte buf[] = new byte[1024];
        int len;
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = null;

        try {
            inputStream = assetManager.open(dbname);
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();

            String[] createScript = outputStream.toString().split(";");
            for (int i = 0; i < createScript.length; i++) {
                String sqlStatement = createScript[i].trim();
                // TODO You may want to parse out comments here
                if (sqlStatement.length() > 0) {
                    try {
                        database.execSQL(sqlStatement + ";");
                    } catch (SQLException se) {
                        Log.e(TAG, se.toString(), se);
                    }
                }
            }
        } catch (IOException e) {
            // TODO Handle Script Failed to Load
            Log.e(TAG, e.toString(), e);
            throw e;
        } catch (SQLException e) {
            // TODO Handle Script Failed to Execute
            Log.e(TAG, e.toString(), e);
            throw e;
        }
    }

}
*/
