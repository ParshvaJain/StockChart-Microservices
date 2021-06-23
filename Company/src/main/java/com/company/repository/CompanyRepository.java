package com.company.repository;

import com.company.model.Company;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

@Repository
public interface CompanyRepository extends MongoRepository<Company,String> {

	@Query(value = "{companyName : ?0}")
	Company findBycompanyName(String company);

}
