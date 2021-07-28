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
    private Button submitBtn;
    private Button backBtn;


    public PostFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post, container, false);

        this.nameEt = view.findViewById(R.id.post_name_et);
        this.countryEt = view.findViewById(R.id.post_country_et);
        this.descEt = view.findViewById(R.id.post_desc_et);
        this.imgIv = view.findViewById(R.id.post_img_iv);
        this.addImgBtn = view.findViewById(R.id.post_addimg_btn);
        this.submitBtn = view.findViewById(R.id.post_submit_btn);
        this.backBtn = view.findViewById(R.id.post_back_btn);

        this.addImgBtn.setOnClickListener((v) -> {
            /**
             * TODO: add image through file system
             */
        });

        this.submitBtn.setOnClickListener((v) -> {
//            this.nameEt.getText().toString();
//            this.countryEt.getText().toString();
//            this.descEt.getText().toString();

            this.post();
        });

        this.backBtn.setOnClickListener((v) -> {
            Navigation.findNavController(view).navigateUp();
        });


        return view;
    }

    private void post(){
        /**
         * implement
         */
    }
}