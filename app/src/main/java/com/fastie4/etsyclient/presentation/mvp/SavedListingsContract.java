package com.fastie4.etsyclient.presentation.mvp;

public class SavedListingsContract extends ListingsContract {
    public interface Presenter extends ListingsContract.Presenter {
        void loadFromOffset(int offset);
    }
}
