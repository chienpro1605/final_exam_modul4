package com.example.exammodul4.service.city;


import com.example.exammodul4.model.City;
import com.example.exammodul4.service.IGeneralService;

public interface ICityService extends IGeneralService<City> {
    Iterable<City> findByCityName(String name);
}
