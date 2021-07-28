package com.example.diveadvices;

import androidx.lifecycle.ViewModel;

import com.example.diveadvices.model.SiteAdvice;

import java.util.LinkedList;
import java.util.List;

public class SitesListViewModel extends ViewModel {

    List<SiteAdvice> data;

    public SitesListViewModel() {
        this.data = new LinkedList<SiteAdvice>();
        for(int i=0; i<10; i++){
            SiteAdvice sa = new SiteAdvice("name "+i);
            sa.setCountry("country "+i);
            sa.setDescription("Desc "+i);
            sa.setMinDepth(11+i);
            sa.setMaxDepth(21+i);
            this.data.add(sa);
        }
    }

    public List<SiteAdvice> getData() { return data; }

    public List<SiteAdvice> getSitesAdvicesList(){
        return new LinkedList<SiteAdvice>();
    }

    public SiteAdvice getOne(int position) {
        return (this.data != null) ? this.data.get(position) : null;
    }
}
