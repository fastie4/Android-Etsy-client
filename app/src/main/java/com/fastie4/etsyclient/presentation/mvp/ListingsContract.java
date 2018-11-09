package com.fastie4.etsyclient.presentation.mvp;

import com.fastie4.etsyclient.presentation.mvp.model.ListingModel;

import java.util.List;

public class ListingsContract {
    public interface Presenter {
        void loadListingImage(ListingModel listingModel);
    }
    public interface View {
        void showItems(List<ListingModel> items, boolean force);
        void showListingImage(ListingModel listingModel);
        void showProgress();
        void hideProgress();
    }
}
