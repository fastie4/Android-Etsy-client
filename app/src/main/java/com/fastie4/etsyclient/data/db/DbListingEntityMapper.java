package com.fastie4.etsyclient.data.db;

import com.fastie4.etsyclient.domain.Listing;
import com.fastie4.etsyclient.domain.mapper.AbstractMapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class DbListingEntityMapper extends AbstractMapper<ListingEntity, Listing> {
    @Inject
    public DbListingEntityMapper() {}

    @Override
    public ListingEntity transformR(Listing listing) {
        ListingEntity listingEntity = new ListingEntity();
        listingEntity.setListingId(listing.getListingId());
        listingEntity.setTitle(listing.getTitle());
        listingEntity.setDescription(listing.getDescription());
        listingEntity.setPrice(listing.getPrice());
        listingEntity.setImageSmallUrl(listing.getImageSmallUrl());
        listingEntity.setImageFullUrl(listing.getImageFullUrl());
        return listingEntity;
    }

    @Override
    public Listing transformL(ListingEntity listingEntity) {
        Listing listing = new Listing();
        listing.setListingId(listingEntity.getListingId());
        listing.setTitle(listingEntity.getTitle());
        listing.setDescription(listingEntity.getDescription());
        listing.setPrice(listingEntity.getPrice());
        listing.setImageSmallUrl(listingEntity.getImageSmallUrl());
        listing.setImageFullUrl(listingEntity.getImageFullUrl());
        return listing;
    }
}
