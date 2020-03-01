package com.example.tareas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class AddingEvent extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_event);
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        addListenerOnButtonCreateEvent();
    }

    private void addListenerOnButtonCreateEvent() {

        final Context context = this;
        Button buttonCreateEvent = (Button) findViewById(R.id.buttonCreateEvent);
        buttonCreateEvent.setOnClickListener(new View.OnClickListener() {

            TextInputEditText text = (TextInputEditText) findViewById(R.id.inputText);
            CalendarView date = (CalendarView) findViewById(R.id.inputDate);
            @Override
            public void onClick(View v) {

                String inputText = text.getText().toString();
                long inputDate = date.getDate();
                Calendar calendarInputDate = Calendar.getInstance();
                calendarInputDate.setTimeInMillis(inputDate);
                Calendar today = Calendar.getInstance();
                System.out.println("calendarInputDate: "+ calendarInputDate + ", today: "+ today);


                if (inputText.isEmpty()){
                    Toast.makeText(context,"Debes introducir un nombre.",Toast.LENGTH_LONG).show();
                }else{
                    //Long today = new Date().getTime();
                    //int compare = Long.compare(today,inputDate);
                    System.out.println("inputDate: "+ inputDate + ", hoy: "+ today);
                    if (calendarInputDate.after(today)){
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
