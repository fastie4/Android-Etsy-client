package com.fastie4.etsyclient.data.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListingImage {
    @SerializedName("url_75x75")
    @Expose
    private String url75x75;
    @SerializedName("url_170x135")
    @Expose
    private String url170x135;
    @SerializedName("url_570xN")
    @Expose
    private String url570xN;
    @SerializedName("url_fullxfull")
    @Expose
    private String urlFullxfull;

    public String getUrl75x75() {
        return url75x75;
    }

    public void setUrl75x75(String url75x75) {
        this.url75x75 = url75x75;
    }

    public String getUrl170x135() {
        return url170x135;
    }

    public void setUrl170x135(String url170x135) {
        this.url170x135 = url170x135;
    }

    public String getUrl570xN() {
        return url570xN;
    }

    public void setUrl570xN(String url570xN) {
        this.url570xN = url570xN;
    }

    public String getUrlFullxfull() {
        return urlFullxfull;
    }

    public void setUrlFullxfull(String urlFullxfull) {
        this.urlFullxfull = urlFullxfull;
    }
}