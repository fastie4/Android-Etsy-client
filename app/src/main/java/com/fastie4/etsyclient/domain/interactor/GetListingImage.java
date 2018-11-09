package com.fastie4.etsyclient.domain.interactor;

import com.fastie4.etsyclient.domain.Listing;
import com.fastie4.etsyclient.domain.repository.ListingRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetListingImage {
    private final ListingRepository mListingRepository;

    @Inject
    GetListingImage(ListingRepository repository) {
        mListingRepository = repository;
    }

    public Observable<Listing> execute(Listing listing) {
        return mListingRepository.getListingImages(listing);
    }
}
