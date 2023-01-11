package com.mchojniak.citylist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface CityRepository extends JpaRepository<City, Long> {
    List<City> findByName(String name);
}
