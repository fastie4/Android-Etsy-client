package com.fastie4.etsyclient.data.api.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListingImagesResponse {

    @SerializedName("results")
    @Expose
    private List<ListingImage> results = null;

    public List<ListingImage> getResults() {
        return results;
    }

    public void setResults(List<ListingImage> results) {
        this.results = results;
    }
}