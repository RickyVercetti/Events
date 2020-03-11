package com.example.tareas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import static java.time.temporal.ChronoUnit.DAYS;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {

    private static final Logger LOG = Logger.getLogger(EventAdapter.class.getName());
    private Context context;
    private List<Event> eventList = new ArrayList<>();

    public EventAdapter(Context context, List<Event> eventList) {
        this.context = context;
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_recycler,null);
        MyViewHolder mvh = new MyViewHolder(view);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        //Event event = eventList.get(i);
        holder.textViewName.setText(eventList.get(i).getName());
        LocalDate today = LocalDate.now();
        String dateString = eventList.get(i).getDate();
        LocalDate date = LocalDate.parse(dateString);
        long diff = DAYS.between(today,date);
        int days = ((int) diff);
        holder.textViewDate.setText(String.valueOf(days));
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView textViewName, textViewDate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            textViewName = itemView.findViewById(R.id.tvName);
            textViewDate = itemView.findViewById(R.id.tvDate);
        }
    }
}
