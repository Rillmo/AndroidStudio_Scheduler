package kr.study.scheduler;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ShowDetail extends AppCompatActivity {

    TextView name, prof, time, room;
    Button close;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        name = (TextView) findViewById(R.id.name);
        name.setText(ScheduleDetail.name);

        prof = (TextView) findViewById(R.id.prof);
        prof.setText(ScheduleDetail.prof);

        time = (TextView) findViewById(R.id.time);
        time.setText(ScheduleDetail.time);

        room = (TextView) findViewById(R.id.room);
        room.setText(ScheduleDetail.room);

        close = (Button) findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowDetail.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
