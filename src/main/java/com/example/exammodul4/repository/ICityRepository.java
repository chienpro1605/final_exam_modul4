package com.example.exammodul4.repository;

import com.example.exammodul4.model.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ICityRepository extends PagingAndSortingRepository<City, Long> {
    @Query("select c from City c where c.name like ?1")
    Iterable<City> findByCityName(String name);
}
