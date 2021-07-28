package com.example.diveadvices;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.diveadvices.model.Model;

public class MenuFragment extends Fragment {

    private Button findBtn;
    private Button postBtn;
    private Button profileBtn;
    private Button logoutBtn;

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        this.findBtn = view.findViewById(R.id.menu_feed_btn);
        this.postBtn = view.findViewById(R.id.menu_post_btn);
        this.profileBtn = view.findViewById(R.id.menu_profile_btn);
        this.logoutBtn = view.findViewById(R.id.menu_logout_btn);

        this.findBtn.setOnClickListener((v)->{
            // Navigate (not global) to recycler view
        });

        this.postBtn.setOnClickListener((v)->{
            Navigation.findNavController(view).navigate(R.id.action_menuFragment_to_postFragment);
        });

        this.profileBtn.setOnClickListener((v)->{
            Navigation.findNavController(view).navigate(R.id.action_menuFragment_to_profileFragment);
        });

        this.logoutBtn.setOnClickListener((v)->{
            Model.instance.logout("1"); //TODO: get id from live data (??)
//            Toast.makeText(getContext(), "Got here", Toast.LENGTH_LONG).show();
            Navigation.findNavController(view).navigate(R.id.action_menuFragment_to_homeFragment);
        });

        return view;
    }
}