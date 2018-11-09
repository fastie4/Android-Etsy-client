package com.fastie4.etsyclient.presentation.mvp.presenter;

import com.fastie4.etsyclient.domain.interactor.FavoriteListing;
import com.fastie4.etsyclient.presentation.mvp.ListingDetailsContract;
import com.fastie4.etsyclient.presentation.mvp.model.ListingModel;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;

public class ListingDetailsPresenterImpl implements ListingDetailsContract.Presenter {
    private final ListingDetailsContract.View mView;
    private final FavoriteListing mFavoriteListing;
    private final ListingModel.Mapper mMapper;

    @Inject
    public ListingDetailsPresenterImpl(ListingDetailsContract.View view,
                                       FavoriteListing favoriteListing,
                                       ListingModel.Mapper mapper) {
        mView = view;
        mFavoriteListing = favoriteListing;
        mMapper = mapper;
    }

    @Override
    public void favourite(ListingModel item) {
        Completable.fromAction(() -> mFavoriteListing.execute(mMapper.transformL(item)))
                .subscribeOn(Schedulers.io())
                .subscribe();
    }
}
