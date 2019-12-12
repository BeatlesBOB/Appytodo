package com.example.testfirebase.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testfirebase.ExtendTicket;
import com.example.testfirebase.R;
import com.example.testfirebase.TicketAdapter;
import com.example.testfirebase.models.Tickets;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class HomeFragment extends Fragment implements TicketAdapter.OnTicketListener  {

    private RecyclerView recyclerview;
    TicketAdapter adapter;
    private FirebaseFirestore db;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    final List<Tickets> items = new ArrayList<>();




    private HomeViewModel homeViewModel;



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerview= root.findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager((getActivity())));
        adapter =  new TicketAdapter(this.getContext() ,items, this);
        recyclerview.setAdapter(adapter);


        return root;



    }

    public void funtion (){

        items.clear();

        db = FirebaseFirestore.getInstance();

//
//        items.add("deux titre");
//        items.add("trois titre");
//        items.add("oui titre");
//        items.add("quatre titre");

        if (user != null) {

            String uid = user.getUid();

            db.collection("tickets")
                    .whereEqualTo("auteurTicket" , uid)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d("TEST", document.getId() + " => " + document.getData());
                                    Tickets nTicket=new Tickets(document.getId(),document.getString("auteurTicket"),document.getString("nomTicket"),document.getString("dateTicket"),document.getString("imageTicket"),document.getString("lieuTicket"),document.getString("descTicket"));

                                    items.add(nTicket);


                                    recyclerview.removeAllViews();

                                }
                            } else {
                                Log.d("TEST", "Error getting documents: ", task.getException());
                            }
                        }
                    });
        }



    }
    @Override
    public void onResume() {
        super.onResume();
        funtion ();
    }



    @Override
    public void onTicketClick(int position) {

        Log.d(TAG,"onTicketClick: clicked.");
        Intent intent = new Intent(this.getContext(), ExtendTicket.class);
        intent.putExtra("ticket id", items.get(position).getId());
        intent.putExtra("ticket auteurid", items.get(position).getAuteurId());
        intent.putExtra("ticket titre", items.get(position).getTitre());
        intent.putExtra("ticket date", items.get(position).getDate());
        intent.putExtra("ticket image", items.get(position).getImage());
        intent.putExtra("ticket lieu", items.get(position).getLieu());
        intent.putExtra("ticket desc", items.get(position).getDesc());

        startActivity(intent);
    }
}