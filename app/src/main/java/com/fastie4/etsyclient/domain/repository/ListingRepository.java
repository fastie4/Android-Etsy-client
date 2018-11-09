package com.fastie4.etsyclient.domain.repository;

import com.fastie4.etsyclient.domain.Listing;

import java.util.List;

import io.reactivex.Observable;

public interface ListingRepository {
    Observable<List<Listing>> getListings(String query, String category, int count, int offset);
    Observable<List<Listing>> getSavedListings(int count, int offset);
    Observable<Listing> getListing(int listingId);
    Observable<Listing> getListingImages(Listing listing);
    void saveListing(Listing listing);
    void deleteListing(Listing listing);
}
