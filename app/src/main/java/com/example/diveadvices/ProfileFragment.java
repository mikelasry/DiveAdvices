package com.example.diveadvices;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ProfileFragment extends Fragment {

    private TextView nameTv;
    private TextView emailTv;

    private Button myPostsBtn;
    private Button backBtn;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        this.nameTv = view.findViewById(R.id.profile_name_tv);
        this.emailTv = view.findViewById(R.id.profile_email_tv);

        this.myPostsBtn = view.findViewById(R.id.profile_my_posts_btn);
        this.backBtn = view.findViewById(R.id.profile_back_btn);

        this.myPostsBtn.setOnClickListener((v) -> {
            this.post();
        });

        this.backBtn.setOnClickListener((v) -> {
            Navigation.findNavController(view).navigateUp();
        });


        return view;
    }

    private void post() {
        /**
         * TODO: post the advice
         */
    }
}