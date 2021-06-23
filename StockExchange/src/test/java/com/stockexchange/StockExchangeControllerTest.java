package com.stockexchange;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.stockexchange.model.StockExchange;
import com.stockexchange.repository.StockExchangeRepository;

@ExtendWith(MockitoExtension.class)
public class StockExchangeControllerTest {
	
	@Mock
	private StockExchangeRepository repository;
	
	@Test
	public void createExchangeTest() {
		StockExchange exchange = new StockExchange();
		exchange.setName("test");
		exchange.setBrief("etst sd d ");
		exchange.setContactAddress("1212343434");
		exchange.setRemarks("dsd");
		ArrayList<String> companies = new ArrayList<>();
		companies.add("abc");
		companies.add("bcd");
		exchange.setCompanies(companies);
		
		when(repository.save(any(StockExchange.class))).thenReturn(exchange);
		StockExchange savedExchange = repository.save(exchange);
		
		MatcherAssert.assertThat(savedExchange.getBrief(), true);
		
	}
	
	
}
