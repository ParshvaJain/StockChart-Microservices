package com.company.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.company.model.CompanyToCode;

public interface CompanyToCodeRepository extends MongoRepository<CompanyToCode,String>{

	//CompanyToCode findBycompany(String exchange);

	CompanyToCode findBycode(List<CompanyToCode> temp);

	List<CompanyToCode> findBycompany(String exchange);

	List<CompanyToCode> findByexchange(String exchange);

	void deleteBycompany(String name);

}
