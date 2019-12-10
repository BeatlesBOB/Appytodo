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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class activity_ticket_registre extends AppCompatActivity {

    EditText nom, group, date, description, lieu;
    String NomData, GroupData, DateData, DescriptionData, LieuData;
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
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    public void Registre(View view) {
        NomData = nom.getText().toString();
        GroupData = group.getText().toString();
        DateData = date.getText().toString();
        DescriptionData = description.getText().toString();
        LieuData = lieu.getText().toString();
        FirebaseUser user = mAuth.getCurrentUser();
        String idUser = user.getUid();

        Map<String, Object> Ticket = new HashMap<>();
        Ticket.put("nom", NomData);
        Ticket.put("group", GroupData);
        Ticket.put("date", DateData);
        Ticket.put("description", DescriptionData);
        Ticket.put("lieu", LieuData);
        Ticket.put("auteurTicket", idUser);

        db.collection("tickets").document().set(Ticket).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                Toast.makeText(activity_ticket_registre.this, "Creation reussi.",
                        Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(activity_ticket_registre.this, Home.class);
                startActivity(intent2);


            }
        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(activity_ticket_registre.this, "Creation non reussi.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }


        public void Back (View view){
            Intent intent = new Intent(activity_ticket_registre.this, Home.class);
            startActivity(intent);
        }
    }
