package com.fastie4.etsyclient.presentation.di.module;

import android.content.Context;

import com.fastie4.etsyclient.presentation.mvp.SavedListingsContract;
import com.fastie4.etsyclient.presentation.mvp.SearchListingsContract;
import com.fastie4.etsyclient.presentation.mvp.presenter.SavedListingsPresenterImpl;
import com.fastie4.etsyclient.presentation.mvp.presenter.SearchListingsPresenterImpl;
import com.fastie4.etsyclient.presentation.ui.adapter.ListingsRecyclerViewAdapter;
import com.fastie4.etsyclient.presentation.ui.fragment.SavedListingsFragment;
import com.fastie4.etsyclient.presentation.ui.fragment.SearchListingsFragment;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class SavedListingsFragmentModule {
    @Provides
    @Named("listings")
    Context context(SavedListingsFragment fragment) {
        return fragment.getActivity();
    }

    @Provides
    ListingsRecyclerViewAdapter adapter(SavedListingsFragment fragment) {
        return new ListingsRecyclerViewAdapter(fragment);
    }

    @Provides
    SavedListingsContract.Presenter presenter(SavedListingsPresenterImpl presenter) {
        return presenter;
    }

    @Provides
    SavedListingsContract.View view(SavedListingsFragment fragment) {
        return fragment;
    }
}