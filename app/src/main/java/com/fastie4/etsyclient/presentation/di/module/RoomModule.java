package com.fastie4.etsyclient.presentation.di.module;

import android.content.Context;

import com.fastie4.etsyclient.data.db.AppDatabase;
import com.fastie4.etsyclient.data.db.ListingDao;
import com.fastie4.etsyclient.presentation.di.qualifier.AppContext;

import javax.inject.Named;
import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {
    @Provides
    @Singleton
    AppDatabase database(@AppContext Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "db")
                .build();
    }

    @Provides
    @Singleton
    ListingDao listingDao(AppDatabase database) {
        return database.listingDao();
    }
}
