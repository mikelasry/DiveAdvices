package com.example.diveadvices;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.diveadvices.model.Model;
import com.example.diveadvices.model.SiteAdvice;

import java.util.List;

public class SitesListViewModel extends ViewModel {

    LiveData<List<SiteAdvice>> data;

    public SitesListViewModel() {
        this.data = Model.instance.getAllSiteAdvices();
    }

    public LiveData<List<SiteAdvice>> getData() {
        return data;
    }
}
