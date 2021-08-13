package com.example.exammodul4.controller;

import com.example.exammodul4.model.Country;
import com.example.exammodul4.service.country.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/countries")
@CrossOrigin("*")
public class CountryController {
    @Autowired
    ICountryService countryService;

    @GetMapping
    public ResponseEntity<Iterable<Country>> showAllCountry(){
        List<Country> countries = (List<Country>) countryService.findAll();
        if (countries.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Void> createCountry(@RequestBody Country country) {
        countryService.save(country);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Country> findByIdCountry(@PathVariable Long id){
        Optional<Country> countryOptional = countryService.findById(id);
        if (!countryOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(countryOptional.get(), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> editCountries(@PathVariable Long id, @RequestBody Country country){
        Optional<Country> countryOptional = countryService.findById(id);
        if (!countryOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        country.setId(id);
        countryService.save(country);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Country> deleteCountries(@PathVariable Long id){
        Optional<Country> country = countryService.findById(id);
        if (!country.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        countryService.delete(id);
        return new ResponseEntity<>(country.get(), HttpStatus.NO_CONTENT);
    }
}
