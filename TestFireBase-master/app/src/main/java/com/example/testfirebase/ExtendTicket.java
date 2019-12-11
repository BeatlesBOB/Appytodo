package com.example.testfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ExtendTicket extends AppCompatActivity {

    private static final String TAG = "Extend";
    TextView tickettitre,ticketdate,ticketdesc,ticketlieu;
    ImageView ticketimg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extend_ticket);
        Log.d(TAG, "onCreate : called.");
        Intent intent = getIntent();
        String ticket_id = intent.getStringExtra("ticket id");
        String ticket_auteurid = intent.getStringExtra("ticket auteurid");
        String ticket_titre = intent.getStringExtra("ticket titre");
        String ticket_date = intent.getStringExtra("ticket date");
        String ticket_image = intent.getStringExtra("ticket image");
        String ticket_lieu = intent.getStringExtra("ticket lieu");
        String ticket_desc = intent.getStringExtra("ticket desc");

        tickettitre = findViewById(R.id.txttitre);
        ticketdate = findViewById(R.id.txtdate);
        ticketdesc = findViewById(R.id.txtdesc);
        ticketlieu = findViewById(R.id.txtlieu);
        ticketimg = findViewById(R.id.imgtick);

        tickettitre.setText(ticket_titre);
        ticketdate.setText(ticket_date);
        ticketlieu.setText(ticket_lieu);
        ticketdesc.setText(ticket_desc);
        Picasso.get().load(ticket_image).into(ticketimg);
    }
}