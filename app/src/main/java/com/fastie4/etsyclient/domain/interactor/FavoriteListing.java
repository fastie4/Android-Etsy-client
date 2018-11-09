package com.fastie4.etsyclient.domain.interactor;

import com.fastie4.etsyclient.domain.Listing;
import com.fastie4.etsyclient.domain.repository.ListingRepository;

import javax.inject.Inject;

public class FavoriteListing {
    private final ListingRepository mListingRepository;

    @Inject
    FavoriteListing(ListingRepository repository) {
        mListingRepository = repository;
    }

    public void execute(Listing listing) {
        if (listing.isFavorite()) {
            mListingRepository.saveListing(listing);
        } else mListingRepository.deleteListing(listing);
    }
}
