package com.fastie4.etsyclient.presentation.mvp;

import com.fastie4.etsyclient.presentation.mvp.model.ListingModel;

public class ListingDetailsContract {
    public interface Presenter {
        void favourite(ListingModel item);
    }
    public interface View {
    }
}
