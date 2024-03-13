package com.berkayarslan.RestaurantService.repository;

import com.berkayarslan.RestaurantService.model.Restaurant;
import org.springframework.data.repository.query.Param;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;

public interface RestaurantRepository extends SolrCrudRepository<Restaurant,String> {

    @Query("fq={!geofilt sfield=location pt=?0,?1 d=10}")
    List<Restaurant> findRestaurantsWithInTenKm(@Param("lat") String lat, @Param("lon") String lon);



}
