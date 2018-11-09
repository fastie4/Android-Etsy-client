package com.fastie4.etsyclient.domain.interactor;

import com.fastie4.etsyclient.domain.Listing;
import com.fastie4.etsyclient.domain.repository.ListingRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetListings {
    private final ListingRepository mListingRepository;

    @Inject
    GetListings(ListingRepository repository) {
        mListingRepository = repository;
    }

    public Observable<List<Listing>> execute(String query, String category, int offset) {
        return mListingRepository.getListings(query, category, 10, offset);
    }
}
