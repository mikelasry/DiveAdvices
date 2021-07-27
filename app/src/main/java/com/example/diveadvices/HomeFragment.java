package com.example.diveadvices;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment {

    private Button loginBtn;
    private Button signupBtn;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        this.loginBtn = view.findViewById(R.id.home_login_btn);
        this.signupBtn = view.findViewById(R.id.home_signup_btn);

        this.loginBtn.setOnClickListener((v) -> Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_loginFragment));
        this.signupBtn.setOnClickListener((v) -> Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_signupFragment2));

        return view;
    }
}