package com.example.diveadvices;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.diveadvices.model.Model;
import com.example.diveadvices.model.User;

public class SignupFragment extends Fragment {

    private EditText nameEt;
    private EditText emailEt;
    private EditText passwordEt;
    private EditText cPasswordEt;

    private Button submitBtn;
    private Button backBtn;


    public SignupFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        this.nameEt = view.findViewById(R.id.signup_name_et);
        this.emailEt = view.findViewById(R.id.signup_email_et);
        this.passwordEt = view.findViewById(R.id.signup_password_et);
        this.cPasswordEt = view.findViewById(R.id.signup_cpassword_et);

        this.submitBtn = view.findViewById(R.id.signup_submit_btn);
        this.backBtn = view.findViewById(R.id.signup_back_btn);


        this.submitBtn.setOnClickListener((v) -> submit(view));
        this.backBtn.setOnClickListener((v) -> Navigation.findNavController(view).navigateUp());

        return view;
    }

    private void submit(View view){
        if(isValid()){
            // pb.setVisibility(View.VISIBLE);
            this.submitBtn.setEnabled(false);
            Model.instance.addUser(
                    new User(this.nameEt.getText().toString(), this.emailEt.getText().toString())
                    ,this.passwordEt.getText().toString(),
                    (success, msg) -> {
                        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                        if(success){
                            Navigation.findNavController(view).navigate(R.id.action_signupFragment_to_loginFragment);
                        } this.submitBtn.setEnabled(true);
                    });
        }

    }

    private boolean isValid(){
        if(this.nameEt.getText().toString().isEmpty()){
            this.nameEt.setError("Name is missing");
            return false;
        }

        if(this.emailEt.getText().toString().isEmpty()){
            this.emailEt.setError("Email is missing");
            return false;
        }

        if(this.passwordEt.getText().toString().isEmpty()){
            this.passwordEt.setError("Password is missing");
            return false;
        }
        if(this.cPasswordEt.getText().toString().isEmpty()){
            this.cPasswordEt.setError("Please write password again");
            return false;
        }
        if(!this.passwordEt.getText().toString().equals(this.cPasswordEt.getText().toString())){
            this.cPasswordEt.setError("Passwords must match");
            return false;
        }
        if(this.passwordEt.getText().toString().length()<6){
            this.passwordEt.setError("Password must be at least 6 characters");
        }

        return true;
    }

    private void resetCreds(){
        this.nameEt.setText(Model.EMPTY);
        this.emailEt.setText(Model.EMPTY);
        this.passwordEt.setText(Model.EMPTY);
        this.cPasswordEt.setText(Model.EMPTY);
        this.submitBtn.setEnabled(true);
    }
}