package com.katariasoft.technologies.springdatamongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.katariasoft.technologies.springdatamongo.entity.Rating;

public interface RatingsRepository extends MongoRepository<Rating, String> {

}
