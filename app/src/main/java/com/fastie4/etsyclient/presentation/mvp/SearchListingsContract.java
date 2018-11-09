package com.fastie4.etsyclient.presentation.mvp;

public class SearchListingsContract extends ListingsContract {
    public interface Presenter extends ListingsContract.Presenter {
        void loadFromOffset(int offset, String query, String category);
    }
}