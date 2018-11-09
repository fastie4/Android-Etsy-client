package com.fastie4.etsyclient.presentation.di.module;

import android.content.Context;

import com.fastie4.etsyclient.presentation.mvp.ListingDetailsContract;
import com.fastie4.etsyclient.presentation.mvp.presenter.ListingDetailsPresenterImpl;
import com.fastie4.etsyclient.presentation.ui.ListingDetailsActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ListingDetailsActivityModule {
    @Provides
    @Named("list")
    Context context(ListingDetailsActivity activity) {
        return activity;
    }

    @Provides
    ListingDetailsContract.Presenter presenter(ListingDetailsPresenterImpl presenter) {
        return presenter;
    }

    @Provides
    ListingDetailsContract.View view(ListingDetailsActivity activity) {
        return activity;
    }
}
