package com.example.diveadvices.model;

public class SiteAdvice {

    private String name;
    private String country;
    private String description;
    private String imgUrl;
    private String owner;

    private Long lastUpdated;

    private int minDepth;
    private int maxDepth;

    public SiteAdvice(String name) {
        this.setName(name);
        this.setLastUpdated(System.currentTimeMillis());
        this.setMinDepth(0);
        this.setMaxDepth(18);
    }

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

    public int getMinDepth() {
        return minDepth;
    }

    public boolean setMinDepth(int minDepth) {
        if(minDepth<1 || minDepth>100)
            return false;
        this.minDepth = minDepth;
        return true;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public boolean setMaxDepth(int maxDepth) {
        if(maxDepth>100 || maxDepth<1) return false;
        this.maxDepth = maxDepth;
        return true;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {return imgUrl;}

    public void setImgUrl(String imgUrl) {this.imgUrl = imgUrl;}

    public Long getLastUpdated() { return lastUpdated; }

    public void setLastUpdated(Long lastUpdated) { this.lastUpdated = lastUpdated;}

    public String getOwner() { return owner; }

    public void setOwner(String owner) { this.owner = owner; }
}
