package com.example.diveadvices.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Entity
public class SiteAdvice implements Serializable {

    @PrimaryKey
    @NonNull
    private String id;

    private String name;
    private String country;
    private String description;
    private String imgUrl;
    private String owner;


    private String minDepth;
    private String maxDepth;

    @Ignore
    public SiteAdvice(String id, String name, String country, String description, String imgUrl, String owner, String minDepth, String maxDepth) {
        this.name = name;
        this.country = country;
        this.description = description;
        this.imgUrl = imgUrl;
        this.owner = owner;
        this.minDepth = minDepth;
        this.maxDepth = maxDepth;
        this.id = id;
    }

    public SiteAdvice() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMinDepth() {
        return minDepth;
    }

    public void setMinDepth(String minDepth) { this.minDepth = minDepth; }

    public String getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(String maxDepth) { this.maxDepth = maxDepth; }

    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {return imgUrl;}

    public void setImgUrl(String imgUrl) {this.imgUrl = imgUrl;}

    public String getId() { return id; }

    public void setId(String id) { this.id = id;}

    public String getOwner() { return owner; }

    public void setOwner(String owner) { this.owner = owner; }

    public static SiteAdvice fromMap(Map<String,Object> map){
        SiteAdvice sa = new SiteAdvice();
        if(map.containsKey("name")) sa.setName(map.get("name").toString());
        if(map.containsKey("description")) sa.setDescription(map.get("description").toString());
        if(map.containsKey("country")) sa.setCountry(map.get("country").toString());
        if(map.containsKey("imgUrl")) sa.setImgUrl(map.get("imgUrl").toString());
        if(map.containsKey("owner")) sa.setOwner(map.get("owner").toString());
        if(map.containsKey("id")) sa.setId(map.get("id").toString());
        if(map.containsKey("minDepth")) sa.setMinDepth(map.get("minDepth").toString());
        if(map.containsKey("maxDepth")) sa.setMaxDepth(map.get("maxDepth").toString());
        return sa;
    }

    public Map<String, String> jsonify(){
        Map<String, String> json = new HashMap<>();
        json.put("name",this.name);
        json.put("description",this.description);
        json.put("country",this.country);
        json.put("imgUrl",this.imgUrl);
        json.put("owner",this.owner);
        json.put("id",this.id);
        json.put("minDepth",this.minDepth);
        json.put("maxDepth",this.maxDepth);
        return json;
    }

}
