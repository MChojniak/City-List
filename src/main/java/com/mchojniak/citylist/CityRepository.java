package com.mchojniak.citylist;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface CityRepository extends JpaRepository<City, Long> {
    @Query("SELECT c FROM City c")
    List<City> findAllCities(Pageable page);

    @Query("SELECT COUNT(c) FROM City c")
    Integer getAmountOfRows();

    @Query("SELECT c FROM City c WHERE c.name = ?1")
    List<City> findCityByName(String name);
}
