package com.example.testfirebase.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testfirebase.R;
import com.example.testfirebase.TicketAdapter;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private RecyclerView recyclerview;
    TicketAdapter adapter;
    ArrayList<String> items;


    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        recyclerview= root.findViewById(R.id.recyclerview);
        items = new ArrayList<>();

        items.add("Prermier titre");
        items.add("deux titre");
        items.add("trois titre");
        items.add("oui titre");
        items.add("quatre titre");

        recyclerview.setLayoutManager(new LinearLayoutManager((getActivity())));
        adapter =  new TicketAdapter(this.getContext() ,items);
        recyclerview.setAdapter((RecyclerView.Adapter) adapter);




        return root;
    }






}