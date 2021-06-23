package com.sector.repository;

import com.sector.model.Sector;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorRepository extends MongoRepository<Sector,String> {
	
	@Query(value = "{sectorName : ?0}")
	Sector findBysectorName(String sector);


}
