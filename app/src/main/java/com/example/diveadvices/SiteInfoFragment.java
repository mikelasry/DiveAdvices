package com.example.diveadvices;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SiteInfoFragment extends Fragment {

    private ImageView imageIv;
    private TextView descriptionTv;
    private TextView minDepthTv;
    private TextView maxDepthTv;
    private Button doneBtn;


    public SiteInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_site_info, container, false);

        this.imageIv = view.findViewById(R.id.sinfo_img_iv);
        this.descriptionTv = view.findViewById(R.id.sinfo_desc_tv);
        this.minDepthTv = view.findViewById(R.id.sinfo_mindepth_tv);
        this.maxDepthTv = view.findViewById(R.id.sinfo_maxdepth_tv);
        this.doneBtn = view.findViewById(R.id.sinfo_done_btn);

        this.doneBtn.setOnClickListener((v) -> Navigation.findNavController(view).navigateUp());

        return view;
    }
}