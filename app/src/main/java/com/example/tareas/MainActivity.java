package com.example.tareas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MainActivity extends Activity {

    private static final Logger LOG = Logger.getLogger(AddingEvent.class.getName());
    private RecyclerView recyclerView;
    private EventAdapter eventAdapter;
    private List<Event> eventList;
    private TextView emptyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv);
        emptyList = findViewById(R.id.emptyList);
        addListenerOnButtonCreate();
        deleteOldEvents();
        Cursor c = getCursorFromDataBase();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        showEvents(c);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.about)
        {

        }
        else if (id == R.id.truncate)
        {
            //ConfirmationPrompt prompt =
        }
        return super.onOptionsItemSelected(item);
    }

    private void showEvents(Cursor c) {

        eventList = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (c.getCount()!=0){
            emptyList.setVisibility(View.GONE);
        }
        while (c.moveToNext()){
            Event e = new Event(c.getString(0),c.getString(1));
            LOG.info(e.getDate()+" son los días, el nombre: "+e.getName());
            eventList.add(e);
        }
        eventAdapter = new EventAdapter(this,eventList);
        recyclerView.setAdapter(eventAdapter);
        c.close();
    }

    private void deleteOldEvents() {

        BBDD bd = new BBDD(getApplicationContext(),"EVENTOS", null, 1);
        SQLiteDatabase db = bd.getWritableDatabase();

        //int filesDeleted = db.delete("EVENTOS","fecha < ?",new String[] {LocalDate.now().plusDays(2000L).toString()});
        int filesDeleted = db.delete("EVENTOS","fecha < ?",new String[] {LocalDate.now().toString()});
        LOG.info("Filas borradas = "+filesDeleted);

    }

    protected Cursor getCursorFromDataBase(){

        BBDD bd = new BBDD(getApplicationContext(),"EVENTOS", null, 1);
        SQLiteDatabase db = bd.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM EVENTOS ORDER BY FECHA ASC;",null);
        return cursor;
    }

    protected void addListenerOnButtonCreate() {

        final Context context = this;
        //FloatingActionButton buttonCreate = findViewById(R.id.addButton);
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