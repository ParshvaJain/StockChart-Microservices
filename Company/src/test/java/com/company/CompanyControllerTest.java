package com.company;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.company.model.Company;
import com.company.model.IPO;
import com.company.repository.CompanyRepository;
import com.company.repository.IPORepository;

@ExtendWith(MockitoExtension.class)
public class CompanyControllerTest {
	@Mock
	private CompanyRepository repository;
	
	@Mock
	private IPORepository ipoRepository;
	
	@Test
	public void savedCompanySuccess() {
		Company company = new Company();
		company.setCompanyName("test");
		company.setCeo("abc");
		company.setSector("IT");
		
		when(repository.save(any(Company.class))).thenReturn(company);
		Company savedCompany = repository.save(company);
		
		MatcherAssert.assertThat(savedCompany.getCeo(), true);
	}
	
	@Test
	public void savedIPOSuccess() {
		IPO ipo = new IPO();
		ipo.setCompanyName("test");
		ipo.setNumberOfShares(154);
		ipo.setPricePershare(325.5);
		
		when(ipoRepository.save(any(IPO.class))).thenReturn(ipo);
		IPO savedIPO = ipoRepository.save(ipo);
		MatcherAssert.assertThat(savedIPO.getCompanyName(), true);
	}
}


