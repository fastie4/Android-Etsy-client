package com.fastie4.etsyclient.domain.interactor;

import com.fastie4.etsyclient.domain.Listing;
import com.fastie4.etsyclient.domain.repository.ListingRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetSavedListings {
    private final ListingRepository mListingRepository;

    @Inject
    GetSavedListings(ListingRepository repository) {
        mListingRepository = repository;
    }

    public Observable<List<Listing>> execute(int offset) {
        return mListingRepository.getSavedListings(10, offset);
    }
}
