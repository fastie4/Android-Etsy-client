package com.fastie4.etsyclient.presentation.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fastie4.etsyclient.presentation.mvp.ListingsContract;
import com.fastie4.etsyclient.presentation.mvp.SearchListingsContract;
import com.fastie4.etsyclient.presentation.mvp.model.ListingModel;
import com.fastie4.etsyclient.presentation.ui.fragment.base.ListingsFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;

public class SearchListingsFragment extends ListingsFragment {
    private static final String SAVED_LISTINGS = "listings";
    private static final String ARG_QUERY = "arg_query";
    private static final String ARG_CATEGORY = "arg_category";
    private String mQuery;
    private String mCategory;

    @Inject
    SearchListingsContract.Presenter mPresenter;

    public static ListingsFragment newInstance(String query, String category) {
        ListingsFragment fragment = new SearchListingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_QUERY, query);
        args.putString(ARG_CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mQuery = getArguments().getString(ARG_QUERY);
            mCategory = getArguments().getString(ARG_CATEGORY);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        if (savedInstanceState != null) {
            List<ListingModel> list = savedInstanceState.getParcelableArrayList(SAVED_LISTINGS);
            if (list != null) {
                showItems(list, true);
                return view;
            }
        }
        loadFromOffset(0);
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (getAdapter().getItemCount() > 0) {
            outState.putParcelableArrayList(SAVED_LISTINGS, (ArrayList<ListingModel>) getAdapter().getItems());
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void loadFromOffset(int offset) {
        mPresenter.loadFromOffset(offset, mQuery, mCategory);
    }

    @Override
    public void loadImageUrl(ListingModel item) {
        mPresenter.loadListingImage(item);
    }
}
