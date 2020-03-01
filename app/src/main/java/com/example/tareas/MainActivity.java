package com.example.tareas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButtonCreate();
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
