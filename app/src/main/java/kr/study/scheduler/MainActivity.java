package kr.study.scheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

class DBHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "schedule.db";
    private static final int DATABASE_VERSION = 1;
    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE schedule(_id INTEGER PRIMARY KEY AUTOINCREMENT, "+" name TEXT, prof TEXT, time TEXT, room TEXT)");
        db.execSQL("INSERT INTO schedule VALUES(1, '모바일프로그래밍', '윤현구', '(수)15:00~18:00', '공510')");
        db.execSQL("INSERT INTO schedule VALUES(2, '웹프로그래밍II', '웹교수', '(수)12:00~15:00', '공501')");
        db.execSQL("INSERT INTO schedule VALUES(3, 'ECAD', 'ECAD교수', '(목)12:00~15:00', '공510')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS schedule");
        onCreate(db);
    }
}

public class MainActivity extends AppCompatActivity {

    ListView list;
    DBHelper helper;
    SQLiteDatabase db;
    ScheduleDetail scheduleDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.list);

        helper = new DBHelper(this);
        db = helper.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM schedule", null);
        String [] from = {"name"};
        int [] to = {android.R.id.text1};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, from, to);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, ShowDetail.class);
                Object position = (Object)adapterView.getAdapter().getItemId(i);  //리스트뷰의 포지션 내용을 가져옴.
                String index = position.toString();

                cursor.moveToFirst();
                do{
                    if(cursor.getInt(0) == Integer.valueOf(index)){
                        ScheduleDetail scheduleDetail = new ScheduleDetail(cursor.getString(1),cursor.getString(2),
                                cursor.getString(3),cursor.getString(4));
                    }

                }while (cursor.moveToNext());

                startActivity(intent);
            }
        });
    }
}