package com.example.tareas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

public class AddingEvent extends Activity {


    private LocalDate daySelected;
    private LocalDate today;
    private static final Logger LOG = Logger.getLogger(AddingEvent.class.getName());

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

        text = (EditText) findViewById(R.id.inputText);
        date = (CalendarView) findViewById(R.id.inputDate);
        buttonCreateEvent = (Button) findViewById(R.id.buttonCreateEvent);

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
                        Intent intent = new Intent(context, MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(context,"Debes introducir una fecha posterior a hoy.",Toast.LENGTH_LONG).show();
                    }

                }
                /*
                Date conversorFechaSeleccionada = new Date(inputDate * 1000);
                Date hoy = new Date();
                long diff = hoy.getTime() - conversorFechaSeleccionada.getTime();
                int i = (int) diff;
                */
           }
        });
    }
}
