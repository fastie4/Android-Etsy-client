package com.fastie4.etsyclient.data.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryResponse {
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("results")
    @Expose
    private List<CategoryEntity> results = null;
    @SerializedName("params")
    @Expose
    private Object params;
    @SerializedName("type")
    @Expose
    private String type;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<CategoryEntity> getResults() {
        return results;
    }

    public void setResults(List<CategoryEntity> results) {
        this.results = results;
    }

    public Object getParams() {
        return params;
    }

    public void setParams(Object params) {
        this.params = params;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
