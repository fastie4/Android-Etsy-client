package com.fastie4.etsyclient.presentation.di.module;

import android.content.Context;

import com.fastie4.etsyclient.data.repository.CategoryRepositoryImpl;
import com.fastie4.etsyclient.data.repository.ListingRepositoryImpl;
import com.fastie4.etsyclient.domain.repository.CategoryRepository;
import com.fastie4.etsyclient.domain.repository.ListingRepository;
import com.fastie4.etsyclient.presentation.App;
import com.fastie4.etsyclient.presentation.di.qualifier.AppContext;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class AppModule {
    @Binds
    @AppContext
    abstract Context context(App app);
    @Binds
    abstract CategoryRepository categoryRepository(CategoryRepositoryImpl repository);
    @Binds
    abstract ListingRepository listingRepository(ListingRepositoryImpl repository);
}
