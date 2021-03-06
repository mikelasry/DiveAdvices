package com.example.diveadvices;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.diveadvices.model.Model;


public class LoginFragment extends Fragment {

    private EditText emailEt;
    private EditText passwordEt;

    private Button submitBtn;
    private Button backBtn;

    public LoginFragment() {
    } // Required empty public constructor

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
//
        this.emailEt = view.findViewById(R.id.login_email_et);
        this.passwordEt = view.findViewById(R.id.login_password_et);

        this.submitBtn = view.findViewById(R.id.login_submit_btn);
        for (int i = 0; i < 10; i++)
            Log.d("TAG", "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ " + (this.submitBtn == null));
        this.backBtn = view.findViewById(R.id.login_back_btn);
//
        this.submitBtn.setOnClickListener((v) -> submit(view));
        this.backBtn.setOnClickListener((v) -> Navigation.findNavController(view).navigateUp());

        return view;
    }

    private void submit(View view) {

//        NavDirections actionToMenu = LoginFragmentDirections.actionGlobalMenuFragment();
//        Navigation.findNavController(view).navigate(actionToMenu);
//        return;

        this.submitBtn.setEnabled(false);

        if (this.passwordEt.getText().toString().isEmpty() ||
                this.emailEt.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "All credentials must be supplied!", Toast.LENGTH_LONG).show();
            this.submitBtn.setEnabled(true);
            return;
        }

        Model.instance.login(this.emailEt.getText().toString(),
                this.passwordEt.getText().toString(),
                (success) -> {

                    if (success) {
                        NavDirections actionToMenu = LoginFragmentDirections.actionGlobalMenuFragment();
                        Navigation.findNavController(view).navigate(actionToMenu);
                    } else Toast.makeText(getContext(), "Please try again!", Toast.LENGTH_LONG).show();

                    this.emailEt.setText("");
                    this.passwordEt.setText("");
                    this.submitBtn.setEnabled(true);
                }
        );
    }

    private void resetCreds() {
        this.emailEt.setText(Model.EMPTY);
        this.passwordEt.setText(Model.EMPTY);
        this.submitBtn.setEnabled(true);
    }
}