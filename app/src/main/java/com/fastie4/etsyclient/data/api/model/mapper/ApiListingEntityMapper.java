package com.fastie4.etsyclient.data.api.model.mapper;

import com.fastie4.etsyclient.data.api.model.ListingEntity;
import com.fastie4.etsyclient.domain.Listing;
import com.fastie4.etsyclient.domain.mapper.AbstractMapper;

import java.util.List;

import javax.inject.Inject;

public class ApiListingEntityMapper extends AbstractMapper<ListingEntity, Listing> {
    @Inject
    public ApiListingEntityMapper() {}

    @Override
    public ListingEntity transformR(Listing listing) {
        ListingEntity listingEntity = new ListingEntity();
        listingEntity.setListingId(listing.getListingId());
        listingEntity.setTitle(listing.getTitle());
        listingEntity.setDescription(listing.getDescription());
        listingEntity.setPrice(listing.getPrice());
        return listingEntity;
    }

    @Override
    public Listing transformL(ListingEntity listingEntity) {
        Listing listing = new Listing();
        listing.setListingId(listingEntity.getListingId());
        listing.setTitle(listingEntity.getTitle());
        listing.setDescription(listingEntity.getDescription());
        listing.setPrice(listingEntity.getPrice());
        return listing;
    }
}
