package com.example.tareas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Logger;

public class MainActivity extends Activity {

    private String name;
    private String dateString;
    private LocalDate date;
    private LocalDate today;
    private static final Logger LOG = Logger.getLogger(AddingEvent.class.getName());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        today = LocalDate.now();
        addListenerOnButtonCreate();
        showOnDataBase();
    }
    protected void showOnDataBase(){
        BBDD bd = new BBDD(getApplicationContext(),"EVENTOS", null, 1);
        SQLiteDatabase db = bd.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM EVENTOS ORDER BY FECHA ASC;",null);
        while(cursor.moveToNext()){
            name = cursor.getString(0);
            dateString = cursor.getString(1);
            LOG.info("name: "+ name + ", date: " + dateString);
            //TODO calcular días restantes y contenedores
        }
        cursor.close();
    }

    protected void addListenerOnButtonCreate() {

        final Context context = this;
        Button buttonCreate = (Button) findViewById(R.id.addButton);
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddingEvent.class);
                startActivity(intent);
            }
        });

    }
    public void onBackPressed() {
        this.finish();
    }


}
