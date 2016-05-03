package net.yoogloo.databasedemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            SQLiteDatabase myDatabase = this.openOrCreateDatabase("Events", MODE_PRIVATE, null);

            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS events (event VARCHAR, year INT(4))");

            myDatabase.execSQL("INSERT INTO events (event, year) VALUES ('Declaration of Independence', 1776)");

            myDatabase.execSQL("INSERT INTO events (event, year) VALUES ('Wedding', 2009)");

            Cursor c = myDatabase.rawQuery("SELECT * FROM events", null);

            int eventIndex = c.getColumnIndex("event");
            int yearIndex = c.getColumnIndex("year");

            c.moveToFirst();
            while (c != null) {

                Log.i("Results - event", c.getString(eventIndex));
                Log.i("Results - year", Integer.toString(c.getInt(yearIndex)));

                c.moveToNext();

            }



        }
        catch (Exception e) {

            e.printStackTrace();

        }

    }
}
