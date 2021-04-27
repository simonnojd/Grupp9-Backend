package com.example.grupp9.repositories;

import com.example.grupp9.models.Category;
import com.example.grupp9.models.City;
import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<City, Long> {

    City findByName(String name);
}
