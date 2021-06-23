package com.company.repository;

import com.company.model.IPO;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

@Repository
public interface IPORepository extends MongoRepository<IPO,String> {
	
	@Query(value = "{companyName : ?0}")
	IPO findBycompanyName(String company);
	
}
