package com.sector;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.sector.model.Sector;
import com.sector.repository.SectorRepository;

@ExtendWith(MockitoExtension.class)
public class SectorControllerTest {
	
	@Mock
	private SectorRepository repository;
	
	@Test
	public void savedSectorSuccess() {
		Sector sector = new Sector();
		sector.setSectorName("test");
		sector.setBrief("testing a bc ");
		ArrayList<String> companies = new ArrayList<>();
		companies.add("test123");
		sector.setCompanies(companies);
		
		when(repository.save(any(Sector.class))).thenReturn(sector);
		Sector savedSector = repository.save(sector);
		
		MatcherAssert.assertThat(savedSector.getBrief(), true);
	}
}
