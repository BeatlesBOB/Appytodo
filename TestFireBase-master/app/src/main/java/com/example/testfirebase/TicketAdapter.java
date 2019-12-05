package com.example.testfirebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.List;


public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<String> data;

    public TicketAdapter(Context context, List<String> data){

        this.layoutInflater = LayoutInflater.from(context);
        this.data=data;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = layoutInflater.inflate(R.layout.layout_ticket, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        String titre = data.get(i);
        viewHolder.txttitre.setText(titre);


    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txttitre,lieu;
        public  ViewHolder(@NonNull View itemView){

            super(itemView);
            txttitre = itemView.findViewById(R.id.tickettitle);
            lieu = itemView.findViewById(R.id.ticketlieu);
        }

    }


}