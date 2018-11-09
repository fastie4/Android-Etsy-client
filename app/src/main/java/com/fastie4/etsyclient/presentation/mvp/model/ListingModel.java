package com.fastie4.etsyclient.presentation.mvp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fastie4.etsyclient.domain.Listing;
import com.fastie4.etsyclient.domain.mapper.AbstractMapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.Nullable;

public class ListingModel implements Parcelable {
    private int listingId;
    private String title;
    private String description;
    private String price;
    private String imageSmallUrl;
    private String imageFullUrl;
    private boolean isFavorite;

    public ListingModel() {}

    protected ListingModel(Parcel in) {
        listingId = in.readInt();
        title = in.readString();
        description = in.readString();
        price = in.readString();
        imageSmallUrl = in.readString();
        imageFullUrl = in.readString();
        isFavorite = in.readByte() != 0;
    }

    public static final Creator<ListingModel> CREATOR = new Creator<ListingModel>() {
        @Override
        public ListingModel createFromParcel(Parcel in) {
            return new ListingModel(in);
        }

        @Override
        public ListingModel[] newArray(int size) {
            return new ListingModel[size];
        }
    };

    public int getListingId() {
        return listingId;
    }

    public void setListingId(int listingId) {
        this.listingId = listingId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageSmallUrl() {
        return imageSmallUrl;
    }

    public void setImageSmallUrl(String imageSmallUrl) {
        this.imageSmallUrl = imageSmallUrl;
    }

    public String getImageFullUrl() {
        return imageFullUrl;
    }

    public void setImageFullUrl(String imageFullUrl) {
        this.imageFullUrl = imageFullUrl;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof ListingModel)) return false;
        return this.listingId == ((ListingModel) obj).listingId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(listingId);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(price);
        parcel.writeString(imageSmallUrl);
        parcel.writeString(imageFullUrl);
        parcel.writeByte((byte) (isFavorite ? 1 : 0));
    }

    public static class Mapper extends AbstractMapper<ListingModel, Listing> {
        @Inject
        public Mapper() {}

        @Override
        public ListingModel transformR(Listing listing) {
            ListingModel listingModel = new ListingModel();
            listingModel.setListingId(listing.getListingId());
            listingModel.setTitle(listing.getTitle());
            listingModel.setDescription(listing.getDescription());
            listingModel.setPrice(listing.getPrice());
            listingModel.setImageSmallUrl(listing.getImageSmallUrl());
            listingModel.setImageFullUrl(listing.getImageFullUrl());
            listingModel.setFavorite(listing.isFavorite());
            return listingModel;
        }

        @Override
        public Listing transformL(ListingModel listingModel) {
            Listing listing = new Listing();
            listing.setListingId(listingModel.getListingId());
            listing.setTitle(listingModel.getTitle());
            listing.setDescription(listingModel.getDescription());
            listing.setPrice(listingModel.getPrice());
            listing.setImageSmallUrl(listingModel.getImageSmallUrl());
            listing.setImageFullUrl(listingModel.getImageFullUrl());
            listing.setFavorite(listingModel.isFavorite());
            return listing;
        }
    }
}
