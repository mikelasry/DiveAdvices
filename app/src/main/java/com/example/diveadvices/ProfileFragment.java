package com.example.diveadvices;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.diveadvices.model.Model;

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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

//        this.nameTv = view.findViewById(R.id.profile_name_tv);
        this.emailTv = view.findViewById(R.id.profile_email_tv);

        this.myPostsBtn = view.findViewById(R.id.profile_my_posts_btn);
        this.backBtn = view.findViewById(R.id.profile_back_btn);

        String name = Model.instance.getCurrentUser().getDisplayName();
        String email = Model.instance.getCurrentUser().getEmail();
//        this.nameTv.setText(name);
        this.emailTv.setText(email);

        this.backBtn.setOnClickListener((v) -> {
            Navigation.findNavController(view).navigateUp();
        });

        return view;
    }

}