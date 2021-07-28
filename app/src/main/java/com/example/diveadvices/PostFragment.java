package com.example.diveadvices;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class PostFragment extends Fragment {

    private EditText nameEt;
    private EditText countryEt;
    private EditText descEt;

    private ImageView imgIv;

    private Button addImgBtn;
    private Button postBtn;
    private Button backBtn;


    public PostFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post, container, false);

        EditText nameEt = view.findViewById(R.id.post_name_et);
        EditText countryEt = view.findViewById(R.id.post_country_et);
        EditText descEt = view.findViewById(R.id.post_desc_et);
        ImageView imgIv = view.findViewById(R.id.post_img_iv);
        Button addImgBtn = view.findViewById(R.id.post_addimg_btn);
        Button postBtn = view.findViewById(R.id.post_submit_btn);
        Button backBtn = view.findViewById(R.id.post_back_btn);

        this.addImgBtn.setOnClickListener((v) -> {
            /**
             * TODO: add image through file system
             */
        });

        this.postBtn.setOnClickListener((v) -> {
            this.nameEt.getText().toString();
            this.countryEt.getText().toString();
            this.descEt.getText().toString();

            /**
             * post...
             */
        });

        this.backBtn.setOnClickListener((v) -> {
            Navigation.findNavController(view).navigateUp();
        });


        return view;
    }

    private void post(){}
}