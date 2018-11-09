package com.fastie4.etsyclient.presentation.mvp.presenter;

import com.fastie4.etsyclient.domain.interactor.GetListingImage;
import com.fastie4.etsyclient.domain.interactor.GetListings;
import com.fastie4.etsyclient.presentation.mvp.ListingsContract;
import com.fastie4.etsyclient.presentation.mvp.SearchListingsContract;
import com.fastie4.etsyclient.presentation.mvp.model.ListingModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchListingsPresenterImpl implements SearchListingsContract.Presenter {
    private final SearchListingsContract.View mView;
    private final GetListings mGetListings;
    private final GetListingImage mGetListingImage;
    private final ListingModel.Mapper mMapper;

    @Inject
    public SearchListingsPresenterImpl(ListingsContract.View view,
                                       GetListings getListings,
                                       GetListingImage getListingImage, ListingModel.Mapper mapper) {
        mView = view;
        mGetListings = getListings;
        mGetListingImage = getListingImage;
        mMapper = mapper;
    }

    @Override
    public void loadFromOffset(int offset, String query, String category) {
        mView.showProgress();
        mGetListings.execute(query, category, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listings -> {
                    mView.hideProgress();
                    mView.showItems(mMapper.transformListR(listings), offset == 0);
        });
    }

    @Override
    public void loadListingImage(ListingModel listingModel) {
        mGetListingImage.execute(mMapper.transformL(listingModel))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listing -> mView.showListingImage(mMapper.transformR(listing)));
    }
}