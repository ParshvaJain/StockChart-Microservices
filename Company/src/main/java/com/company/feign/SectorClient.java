package com.company.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value="sector-service",url="https://sector--service.herokuapp.com",configuration=FeignConfig.class)
public interface SectorClient {
	
	@RequestMapping(value = "sector/addCompany/{sector}/{company}", method = RequestMethod.GET)
	public ResponseEntity<?> addCompanyToSector(@PathVariable("sector") String sector,@PathVariable("company") String company); 
	
	@RequestMapping(value = "sector/deleteCompanySector/{sector}/{company}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCompanyFromSector(@PathVariable("sector") String sector,@PathVariable("company") String company);
}
