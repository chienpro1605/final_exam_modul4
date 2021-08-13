package com.example.exammodul4.controller;

import com.example.exammodul4.model.City;
import com.example.exammodul4.service.city.ICityService;
import com.example.exammodul4.service.country.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cities")
@CrossOrigin("*")
public class CityController {

    @Autowired
    ICountryService countryService;

    @Autowired
    ICityService cityService;

    @GetMapping
    public ResponseEntity<Iterable<City>> showAllCity(){
        List<City> cities = (List<City>) cityService.findAll();
        if (cities.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Void> createCity(@RequestBody City city) {
        cityService.save(city);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<City> findByIdCity(@PathVariable Long id){
        Optional<City> cityOptional = cityService.findById(id);
        if (!cityOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cityOptional.get(), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> editCity(@PathVariable Long id, @RequestBody City city){
        Optional<City> cityOptional = cityService.findById(id);
        if (!cityOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        city.setId(id);
        cityService.save(city);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<City> deleteCity(@PathVariable Long id){
        Optional<City> city = cityService.findById(id);
        if (!city.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cityService.delete(id);
        return new ResponseEntity<>(city.get(), HttpStatus.NO_CONTENT);
    }
}
