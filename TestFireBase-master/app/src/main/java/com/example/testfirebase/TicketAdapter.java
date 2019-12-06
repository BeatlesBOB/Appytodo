package com.example.testfirebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.testfirebase.models.Tickets;
import com.squareup.picasso.Picasso;

import java.util.List;


public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Tickets> data;
    private OnTicketListener mOnTicketListener;

    public TicketAdapter(Context context, List<Tickets> data, OnTicketListener onTicketListener){

        this.layoutInflater = LayoutInflater.from(context);
        this.data=data;
        this.mOnTicketListener = onTicketListener;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = layoutInflater.inflate(R.layout.layout_ticket, viewGroup, false);
        return new ViewHolder(view,mOnTicketListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Tickets mont = data.get(i);


        String titre = mont.getTitre();
        String lieu = mont.getLieu();
        String date = mont.getDate();
        String imageurl = mont.getImage();

        viewHolder.txttitre.setText(titre);
        viewHolder.txtlieu.setText(lieu);
        viewHolder.txtdate.setText(date);
        Picasso.get().load(imageurl).into(viewHolder.imgimage);


    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

        TextView txttitre,txtlieu,txtdate;
        ImageView imgimage;
         OnTicketListener onTicketListener;

        public  ViewHolder(@NonNull View itemView, OnTicketListener onTicketListener){

            super(itemView);
            txttitre = itemView.findViewById(R.id.titreTicket);
            txtlieu = itemView.findViewById(R.id.lieuTicket);
            txtdate = itemView.findViewById(R.id.dateTicket);
            imgimage = itemView.findViewById(R.id.imageTicket);
            this.onTicketListener = onTicketListener;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onTicketListener.onTicketClick(getAdapterPosition());

        }
    }

    public interface OnTicketListener{
        void onTicketClick(int position);
    }


}