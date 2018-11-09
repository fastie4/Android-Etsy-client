package com.fastie4.etsyclient.presentation.di.module;

import com.fastie4.etsyclient.presentation.ui.ListingDetailsActivity;
import com.fastie4.etsyclient.presentation.ui.MainActivity;
import com.fastie4.etsyclient.presentation.ui.fragment.SavedListingsFragment;
import com.fastie4.etsyclient.presentation.ui.fragment.SearchFragment;
import com.fastie4.etsyclient.presentation.ui.fragment.SearchListingsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AndroidBindingModule {
    @ContributesAndroidInjector
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector(modules = {ListingDetailsActivityModule.class})
    abstract ListingDetailsActivity listingDetailsActivity();

    @ContributesAndroidInjector(modules = {SearchFragmentModule.class})
    abstract SearchFragment searchFragment();

    @ContributesAndroidInjector(modules = {SearchListingsFragmentModule.class})
    abstract SearchListingsFragment searchListingsFragment();

    @ContributesAndroidInjector(modules = {SavedListingsFragmentModule.class})
    abstract SavedListingsFragment savedListingsFragment();
}
