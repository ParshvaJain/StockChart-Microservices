package com.company.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(value="exchange-service",url="https://stockexchange--service.herokuapp.com",configuration=FeignConfig.class)
public interface StockExchangeClient {
	
	@RequestMapping(value = "/stockexchange/addCompanyToExchange/{exchange}/{company}", method = RequestMethod.GET)
	public ResponseEntity<?> addCompanyToExchange(@PathVariable("exchange") String exchange,@PathVariable("company") String company);

	@RequestMapping(value = "/stockexchange/deleteCompany/{company}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCompanyFromExchanges(@PathVariable("company") String company);
}