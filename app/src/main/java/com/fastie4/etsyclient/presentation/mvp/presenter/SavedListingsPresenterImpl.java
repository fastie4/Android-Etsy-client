package com.fastie4.etsyclient.presentation.mvp.presenter;

import com.fastie4.etsyclient.domain.interactor.GetListingImage;
import com.fastie4.etsyclient.domain.interactor.GetSavedListings;
import com.fastie4.etsyclient.presentation.mvp.ListingsContract;
import com.fastie4.etsyclient.presentation.mvp.SavedListingsContract;
import com.fastie4.etsyclient.presentation.mvp.SearchListingsContract;
import com.fastie4.etsyclient.presentation.mvp.model.ListingModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SavedListingsPresenterImpl implements SavedListingsContract.Presenter {
    private final SearchListingsContract.View mView;
    private final GetSavedListings mGetSavedListings;
    private final GetListingImage mGetListingImage;
    private final ListingModel.Mapper mMapper;

    @Inject
    public SavedListingsPresenterImpl(ListingsContract.View view,
                                      GetSavedListings getSavedListings,
                                      GetListingImage getListingImage, ListingModel.Mapper mapper) {
        mView = view;
        mGetSavedListings = getSavedListings;
        mGetListingImage = getListingImage;
        mMapper = mapper;
    }

    @Override
    public void loadFromOffset(int offset) {
        mView.showProgress();
        mGetSavedListings.execute(offset)
                .map(mMapper::transformListR)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listingModels -> {
                    mView.hideProgress();
                    mView.showItems(listingModels, true);
                });
    }

    @Override
    public void loadListingImage(ListingModel listingModel) {

    }
}
