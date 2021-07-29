package com.example.diveadvices;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diveadvices.model.SiteAdvice;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SitesListFragment extends Fragment {
    LiveData<List<SiteAdvice>> sitesAdvicesList;
    private ProgressBar progressBar;
    private SitesListViewModel viewModel;


    public SitesListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sites_list, container, false);
        viewModel = new ViewModelProvider(this).get(SitesListViewModel.class);

        this.sitesAdvicesList = viewModel.getData();

        RecyclerView recyclerView = view.findViewById(R.id.sites_list_recycler);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(manager);

        SitesAdapter adapter = new SitesAdapter();
        recyclerView.setAdapter(adapter);

        this.progressBar = view.findViewById(R.id.site_load_pb);
        this.progressBar.setVisibility(View.GONE);

        adapter.setOnItemClickListener((int position) -> {
            SitesListFragmentDirections.ActionSitesListFragmentToSiteInfoFragment actionToSinfo = SitesListFragmentDirections.actionSitesListFragmentToSiteInfoFragment(this.sitesAdvicesList.getValue().get(position));
            Navigation.findNavController(view).navigate(actionToSinfo);
        });

        viewModel.getData().observe(getViewLifecycleOwner(), (aPList) -> adapter.notifyDataSetChanged());
        return view;
    }

    static class AdviceViewHolder extends RecyclerView.ViewHolder {
        TextView siteName;
        ImageView siteImg;
        OnItemClickListener listener;

        public AdviceViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            this.listener = listener;
            itemView.setOnClickListener((v) -> {
                        if (listener != null) {
                            int position = getAdapterPosition();
                            if (position != RecyclerView.NO_POSITION) {
                                listener.onClick(position);
                            }
                        }
                    }
            );

            siteName = itemView.findViewById(R.id.li_name_tv);
            siteImg = itemView.findViewById(R.id.li_img_iv);
        }

        public void bind(SiteAdvice advice) {
            this.siteName.setText(advice.getName());

            String url = advice.getImgUrl();
//            Log.d("TAG", url);
            if ((url != null) && (url != "")) {//
                Picasso.get()
                        .load(url)
                        .placeholder(R.drawable.ic_launcher_foreground) // menu gallery?
                        .error(R.drawable.ic_launcher_foreground)
                        .into(this.siteImg);
            }
        }
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }


    // Managing View logic.
    class SitesAdapter extends RecyclerView.Adapter<AdviceViewHolder> {
        OnItemClickListener listener;

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }

        @NonNull
        @Override
        public AdviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.site_list_item, parent, false);
            AdviceViewHolder holder = new AdviceViewHolder(view, listener);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull AdviceViewHolder holder, int position) {
            SiteAdvice advice = sitesAdvicesList.getValue().get(position);
            holder.bind(advice);
        }

        // get number of
        @Override
        public int getItemCount() {
            return (sitesAdvicesList.getValue() == null ? 0 : sitesAdvicesList.getValue().size());
        }
    }

}