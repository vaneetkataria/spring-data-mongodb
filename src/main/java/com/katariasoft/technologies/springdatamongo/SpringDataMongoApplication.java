package com.katariasoft.technologies.springdatamongo;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.katariasoft.technologies.springdatamongo.entity.Rating;
import com.katariasoft.technologies.springdatamongo.repository.RatingsRepository;

@SpringBootApplication
public class SpringDataMongoApplication implements ApplicationRunner {

	@Autowired
	private RatingsRepository ratingsRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataMongoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		createFindThenSaveTest();
		// createFindThenSaveTest_mongoTemplate();
		// createFindThenSaveTest_All();
		// createFindThenSaveTest_All_mongoTemplate();

	}

	private void createFindThenSaveTest() {
		// Simple Insert
		Rating rating = new Rating("0", "New:0.0", true, 0.0);
		Rating savedRating = ratingsRepository.save(rating);

		IntStream.range(1, 11).forEach(i -> {
			Optional<Rating> optDbRating = ratingsRepository.findById(savedRating.getId());
			if (optDbRating.isPresent()) {
				Rating dbRating = optDbRating.get();
				dbRating.setAggregatedRating(dbRating.getAggregatedRating() + 1);
				dbRating.setMerchantName("New:" + dbRating.getAggregatedRating());
				ratingsRepository.save(dbRating);
			}
		});
	}

	private void createFindThenSaveTest_mongoTemplate() {
		// Simple Insert
		Rating rating = new Rating("0", "New:0.0", true, 0.0);
		Rating savedRating = mongoTemplate.save(rating);

		IntStream.range(1, 11).forEach(i -> {
			Optional<Rating> optDbRating = ratingsRepository.findById(savedRating.getId());
			if (optDbRating.isPresent()) {
				Rating dbRating = optDbRating.get();
				dbRating.setAggregatedRating(dbRating.getAggregatedRating() + 1);
				dbRating.setMerchantName("New:" + dbRating.getAggregatedRating());
				mongoTemplate.save(dbRating);
			}
		});
	}

	private void createFindThenSaveTest_All() {
		ratingsRepository.saveAll(IntStream.range(1, 11).mapToObj(i -> new Rating(i + "", i + "", true, 1.0))
				.collect(Collectors.toList()));

		ratingsRepository.saveAll(ratingsRepository.findAll().stream().map(dbRating -> {
			dbRating.setAggregatedRating(dbRating.getAggregatedRating() + 1);
			return dbRating;
		}).collect(Collectors.toList()));

	}

	private void createFindThenSaveTest_All_mongoTemplate() {
		ratingsRepository.saveAll(IntStream.range(1, 11).mapToObj(i -> new Rating(i + "", i + "", true, 1.0))
				.collect(Collectors.toList()));

		mongoTemplate.findAll(Rating.class).stream().map(dbRating -> {
			dbRating.setAggregatedRating(dbRating.getAggregatedRating() + 1);
			return dbRating;
		}).forEach(mongoTemplate::save);

	}
}
