package com.example.yeld;

import android.content.Intent;

public class Model {
    private String businessName, businessId;
    private Double businessDistance;

    public Model(String businessId, String businessName, Double businessDistance) {
        this.businessName = businessName;
        this.businessId = businessId;
        this.businessDistance = businessDistance;
    }

    public Model() {
        this.businessName = "";
        this.businessId = "";
        this.businessDistance = 0.0;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public Double getBusinessDistance() {
        return businessDistance;
    }

    public void setBusinessDistance(Double businessDistance) {
        this.businessDistance = businessDistance;
    }

}

