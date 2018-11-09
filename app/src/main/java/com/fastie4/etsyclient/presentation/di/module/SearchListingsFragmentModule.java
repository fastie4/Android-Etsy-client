package com.fastie4.etsyclient.presentation.di.module;

import android.content.Context;

import com.fastie4.etsyclient.presentation.mvp.SearchListingsContract;
import com.fastie4.etsyclient.presentation.mvp.presenter.SearchListingsPresenterImpl;
import com.fastie4.etsyclient.presentation.ui.adapter.ListingsRecyclerViewAdapter;
import com.fastie4.etsyclient.presentation.ui.fragment.SearchListingsFragment;
import com.fastie4.etsyclient.presentation.ui.fragment.base.ListingsFragment;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchListingsFragmentModule {
    @Provides
    @Named("listings")
    Context context(SearchListingsFragment fragment) {
        return fragment.getActivity();
    }

    @Provides
    ListingsRecyclerViewAdapter adapter(SearchListingsFragment fragment) {
        return new ListingsRecyclerViewAdapter(fragment);
    }

    @Provides
    SearchListingsContract.Presenter presenter(SearchListingsPresenterImpl presenter) {
        return presenter;
    }

    @Provides
    SearchListingsContract.View view(SearchListingsFragment fragment) {
        return fragment;
    }
}
