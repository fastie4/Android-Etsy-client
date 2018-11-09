package com.fastie4.etsyclient.data.repository;

import com.fastie4.etsyclient.data.api.ApiInterface;
import com.fastie4.etsyclient.data.api.model.mapper.ApiListingEntityMapper;
import com.fastie4.etsyclient.data.db.DbListingEntityMapper;
import com.fastie4.etsyclient.data.db.ListingDao;
import com.fastie4.etsyclient.domain.Listing;
import com.fastie4.etsyclient.domain.repository.ListingRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import io.reactivex.Observable;

public class ListingRepositoryImpl implements ListingRepository {
    private final ApiInterface mApi;
    private final ListingDao mListingDao;
    private final DbListingEntityMapper mDbListingEntityMapper;
    private final ApiListingEntityMapper mApiListingEntityMapper;

    @Inject
    public ListingRepositoryImpl(ApiInterface api,
                                 ListingDao listingDao,
                                 DbListingEntityMapper listingMapper,
                                 ApiListingEntityMapper apiListingEntityMapper) {
        mApi = api;
        mListingDao = listingDao;
        mDbListingEntityMapper = listingMapper;
        mApiListingEntityMapper = apiListingEntityMapper;
    }

    @Override
    public Observable<List<Listing>> getListings(String query, String category, int count, int offset) {
        return mApi.getActiveListings(category, query, count, offset)
                .map(listingsResponse -> mApiListingEntityMapper
                        .transformListL(listingsResponse.getResults()))
                .zipWith(mListingDao.getAll()
                                .take(1)
                                .map(mDbListingEntityMapper::transformListL),
                        this::findFavorites);
    }

    @Override
    public Observable<List<Listing>> getSavedListings(int limit, int offset) {
        return mListingDao.getAll()
                .map(mDbListingEntityMapper::transformListL)
                .map(this::setFavorite);
    }

    @Override
    public Observable<Listing> getListing(int listingId) {
        return null;
    }

    @Override
    public Observable<Listing> getListingImages(Listing listing) {
        return mApi.getListingImages(listing.getListingId())
                .map(listingImagesResponse -> {
                    listing.setImageSmallUrl(listingImagesResponse.getResults().get(0).getUrl75x75());
                    listing.setImageFullUrl(listingImagesResponse.getResults().get(0).getUrlFullxfull());
                    return listing;
                });
    }

    @Override
    public void saveListing(Listing listing) {
        mListingDao.insert(mDbListingEntityMapper.transformR(listing));
    }

    @Override
    public void deleteListing(Listing listing) {
        mListingDao.delete(mDbListingEntityMapper.transformR(listing));
    }

    private List<Listing> findFavorites(List<Listing> to, List<Listing> fav) {
        Set<Integer> ids = new HashSet<>();
        for (Listing l : fav) {
            ids.add(l.getListingId());
        }
        for(Listing l : to) {
            if (ids.contains(l.getListingId())) {
                l.setFavorite(true);
            }
        }
        return to;
    }

    private List<Listing> setFavorite(List<Listing> listings) {
        for (Listing l : listings) {
            l.setFavorite(true);
        }
        return listings;
    }
}
