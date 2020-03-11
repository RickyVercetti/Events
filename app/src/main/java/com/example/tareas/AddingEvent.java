package com.example.tareas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Logger;

public class AddingEvent extends Activity {

    private static final Logger LOG = Logger.getLogger(AddingEvent.class.getName());
    private LocalDate daySelected;
    private LocalDate today;
    private EditText text;
    private CalendarView date;
    private Button buttonCreateEvent;

    public AddingEvent(){
        daySelected = LocalDate.now();
        today = LocalDate.now();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_event);

        text = findViewById(R.id.inputText);
        date = findViewById(R.id.inputDate);
        buttonCreateEvent = findViewById(R.id.buttonCreateEvent);

        date.setDate(new Date().getTime());
        addListenerOnButtonCreateEvent();
        date.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
            daySelected = LocalDate.of(year,month+1,dayOfMonth);
            today = LocalDate.now();
            }
        });
    }


    private void addListenerOnButtonCreateEvent() {

        final Context context = this;

        buttonCreateEvent.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                String inputText = text.getText().toString();

                if (inputText.isEmpty()){
                    Toast.makeText(context,"Debes introducir un nombre.",Toast.LENGTH_LONG).show();
                }else{

                    LOG.info("\ndaySelected: "+ daySelected + ",\ntoday: "+ today);
                    if (daySelected.isAfter(today)){

                        BBDD bd = new BBDD(getApplicationContext(),"EVENTOS", null, 1);
                        SQLiteDatabase db = bd.getWritableDatabase();
                        String sql = "INSERT INTO EVENTOS (nombre,fecha) VALUES ('"+inputText+"','"+daySelected.toString()+"');";
                        db.execSQL(sql);
                        db.close();

                        Intent intent = new Intent(context, MainActivity.class);
                        startActivity(intent);

                    }else{
                        Toast.makeText(context,"Debes introducir una fecha posterior a hoy.",Toast.LENGTH_LONG).show();
                    }

                }
           }
        });
    }
}
