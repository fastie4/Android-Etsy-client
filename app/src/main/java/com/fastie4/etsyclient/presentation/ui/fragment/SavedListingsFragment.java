package com.fastie4.etsyclient.presentation.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fastie4.etsyclient.presentation.mvp.SavedListingsContract;
import com.fastie4.etsyclient.presentation.mvp.model.ListingModel;
import com.fastie4.etsyclient.presentation.ui.fragment.base.ListingsFragment;

import javax.inject.Inject;

import androidx.annotation.NonNull;

public class SavedListingsFragment extends ListingsFragment {

    @Inject
    SavedListingsContract.Presenter mPresenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        loadFromOffset(0);
        return view;
    }

    @Override
    public void loadFromOffset(int offset) {
        mPresenter.loadFromOffset(offset);
    }

    @Override
    public void loadImageUrl(ListingModel item) {
        mPresenter.loadListingImage(item);
    }
}