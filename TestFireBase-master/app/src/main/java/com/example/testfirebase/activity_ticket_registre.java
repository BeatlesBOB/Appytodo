
package com.example.testfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testfirebase.models.Tickets;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

/**
 * Class pour le registre et modification de ticket
 * @author Nathanel
 */

public class activity_ticket_registre extends AppCompatActivity {

    EditText nom, group, date, description, lieu,participant;
    String NomData, GroupData, DateData, DescriptionData, LieuData, ParticipantData, ParticipantData2;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_registre);

        nom = (EditText) findViewById(R.id.nomTicket);
        group = (EditText) findViewById(R.id.groupTicket);
        date = (EditText) findViewById(R.id.dateTicket);
        description = (EditText) findViewById(R.id.descriptionTicket);
        lieu = (EditText) findViewById(R.id.lieuTicket);
        participant = findViewById(R.id.participantTicket);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    /**
     * Méthode qui permet mettre à jour l'etat d'un ticket et ses details en utilisant l'id de registre
     */
    public void UpdateTicket() {
        FirebaseUser user = mAuth.getCurrentUser();
        String idUser = user.getUid();

        Map<String, Object> Ticket = new HashMap<>();
        Ticket.put("nomTicket", NomData);
        Ticket.put("groupTicket", GroupData);
        Ticket.put("dateTicket", DateData);
        Ticket.put("imageTicket", "https://www.freshnrebel.com/wp-content/uploads/fnr-ss-selfiestick-bl2.jpg");
        Ticket.put("descTicket", DescriptionData);
        Ticket.put("lieuTicket", LieuData);
        Ticket.put("participantTicket", ParticipantData2);


        Ticket.put("auteurTicket", idUser);

        db.collection("tickets").document().set(Ticket).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                Toast.makeText(activity_ticket_registre.this, "Creation reussi.",
                        Toast.LENGTH_SHORT).show();
                onBackPressed();




            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity_ticket_registre.this, "Creation non reussi.",
                        Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });

    }

    /**
     * Methode qui permets de creer un neuvel ticket en fireStore
     * @param view
     */
    public void Registre(View view) {
        NomData = nom.getText().toString();
        GroupData = group.getText().toString();
        DateData = date.getText().toString();
        DescriptionData = description.getText().toString();
        LieuData = lieu.getText().toString();
        ParticipantData = participant.getText().toString();

        if (ParticipantData.length() > 0) {
            db.collection("users")
                    .whereEqualTo("Mail", ParticipantData)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    ParticipantData2 = document.getId();
                                    Log.d("TEST1", ParticipantData2);
                                    UpdateTicket();
                                }
                            } else {
                                Log.d("TEST", "Error getting documents: ", task.getException());
                                ParticipantData2 = "";
                                UpdateTicket();

                            }
                        }
                    });
        } else {
            UpdateTicket();
        }
    }




}

