package com.example.testfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.text.method.ScrollingMovementMethod;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class ExtendTicket extends AppCompatActivity {

    private static final String TAG = "Extend";
    TextView tickettitre,ticketdate,ticketdesc,ticketlieu;
    ImageView ticketimg;
    Button supprimerTicket,modifierticket;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extend_ticket);
        Log.d(TAG, "onCreate : called.");
        final Intent intent = getIntent();
        final String ticket_id = intent.getStringExtra("ticket id");
        String ticket_auteurid = intent.getStringExtra("ticket auteurid");
        final String ticket_titre = intent.getStringExtra("ticket titre");
        final String ticket_date = intent.getStringExtra("ticket date");
        String ticket_image = intent.getStringExtra("ticket image");
        final String ticket_lieu = intent.getStringExtra("ticket lieu");
        final String ticket_desc = intent.getStringExtra("ticket desc");

        db = FirebaseFirestore.getInstance();

        tickettitre = findViewById(R.id.txttitre);
        ticketdate = findViewById(R.id.txtdate);
        ticketdesc = findViewById(R.id.txtdesc);
        ticketlieu = findViewById(R.id.txtlieu);
        ticketimg = findViewById(R.id.imgtick);

        supprimerTicket = findViewById(R.id.suppTicket);
        modifierticket = findViewById(R.id.modifTicket);

        tickettitre.setText(ticket_titre);
        ticketdate.setText(ticket_date);
        ticketlieu.setText(ticket_lieu);
        ticketdesc.setText(ticket_desc);
        Picasso.get().load(ticket_image).into(ticketimg);

        ticketdesc.setMovementMethod(new ScrollingMovementMethod());

        supprimerTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TDRTEST",ticket_id);
                db.collection("tickets").document(ticket_id)
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "DocumentSnapshot successfully deleted!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error deleting document", e);
                            }
                        });
                Intent intent1 = new Intent(ExtendTicket.this, Home.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);

            }
        });


        modifierticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ExtendTicket.this, ModifTicket.class);
                intent.putExtra("ticket id",ticket_id);

                startActivity(intent);

            }
        });
    }

}
