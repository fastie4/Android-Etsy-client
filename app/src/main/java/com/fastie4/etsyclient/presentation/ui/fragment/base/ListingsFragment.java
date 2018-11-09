package com.fastie4.etsyclient.presentation.ui.fragment.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.fastie4.etsyclient.R;
import com.fastie4.etsyclient.presentation.mvp.ListingsContract;
import com.fastie4.etsyclient.presentation.mvp.model.ListingModel;
import com.fastie4.etsyclient.presentation.ui.ListingDetailsActivity;
import com.fastie4.etsyclient.presentation.ui.adapter.ListingsRecyclerViewAdapter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public abstract class ListingsFragment extends Fragment
        implements ListingsContract.View, ListingsRecyclerViewAdapter.OnListFragmentInteractionListener {
    private LinearLayoutManager mLayoutManager;
    private boolean isLoading = false;
    private final int VISIBLE_THRESHOLD = 1;
    private int lastVisibleItem, totalItemCount;

    @Inject
    ListingsRecyclerViewAdapter mAdapter;
    @Inject
    @Named("listings")
    Context mContext;
    @BindView(R.id.listing_recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.listing_swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    public ListingsFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listing_list, container, false);
        ButterKnife.bind(this, view);

        mLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = mLayoutManager.getItemCount();
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + VISIBLE_THRESHOLD)) {
                    isLoading = true;
                    mRecyclerView.post(() -> loadFromOffset(mAdapter.getItemCount()));
                }
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(() -> loadFromOffset(0));
        return view;
    }

    public ListingsRecyclerViewAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onClick(ListingModel item) {
        Intent intent = new Intent(mContext, ListingDetailsActivity.class);
        intent.putExtra(ListingDetailsActivity.EXTRA_LISTING, item);
        mContext.startActivity(intent);
    }

    public abstract void loadFromOffset(int offset);

    @Override
    public void showItems(List<ListingModel> items, boolean force) {
        if (force) {
            mAdapter.setItems(items);
        } else {
            mAdapter.addItems(items);
        }
        mAdapter.notifyDataSetChanged();
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showListingImage(ListingModel listingModel) {
        mAdapter.refreshItem(listingModel);
    }

    @Override
    public void showProgress() {
        isLoading = true;
        mAdapter.addLoading();
    }

    @Override
    public void hideProgress() {
        mAdapter.removeLoading();
        isLoading = false;
    }
}