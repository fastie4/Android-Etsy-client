package com.fastie4.etsyclient.data.api.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListingsResponse {
    @SerializedName("results")
    @Expose
    private List<ListingEntity> results = null;

    public List<ListingEntity> getResults() {
        return results;
    }

    public void setResults(List<ListingEntity> results) {
        this.results = results;
    }
}
