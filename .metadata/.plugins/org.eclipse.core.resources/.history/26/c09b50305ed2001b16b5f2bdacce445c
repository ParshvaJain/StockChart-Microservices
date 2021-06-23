package com.sector.api;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sector.model.Sector;
import com.sector.repository.SectorRepository;
import com.sector.message.ResponseMessage;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/sector")
public class SectorController {
	
	@Autowired
	private SectorRepository repository;
	
	@RequestMapping(value = "/{sector}", method = RequestMethod.GET)
	public List<String> getAllCompaniesInSector(@PathVariable("sector") String sector) {
		Sector tempSector = repository.findBysectorName(sector);
		return tempSector.getCompanies();
	}
	
	@RequestMapping(value = "/getAllSectors", method = RequestMethod.GET)
	public ResponseEntity<List<String>> getAllSectors() {
		List<Sector> tempSector = repository.findAll();
		List<String> sectorList = new ArrayList<>();
		
		int length = tempSector.size();
		for(int i=0;i<length;i++) {
			sectorList.add(tempSector.get(i).getSectorName());
		}
		return new ResponseEntity<>(sectorList,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public void createSector(@RequestBody Sector sector) {
		sector.setId(ObjectId.get().toString());
		repository.save(sector);
		return ;
	}
	
	@RequestMapping(value = "/addCompany/{sector}/{company}", method = RequestMethod.GET)
	public ResponseEntity<?> addCompanyToSector(@PathVariable("sector") String sector,@PathVariable("company") String company) {
		
		if(repository.findBysectorName(sector) == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} 
				
		Sector tempSector = repository.findBysectorName(sector);
		ArrayList<String> companiesList = tempSector.getCompanies();
		companiesList.add(company);
		tempSector.setCompanies(companiesList);
		repository.save(tempSector);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Updated"));
	}
	
	@RequestMapping(value = "/deleteCompanySector/{sector}/{company}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCompanyFromSector(@PathVariable("sector") String sector,@PathVariable("company") String company) {
		
		List<Sector>tempSectors = repository.findAll();
		int length = tempSectors.size();
		Sector tempSector;
		
		for(int i = 0;i < length; i++) {
			tempSector = tempSectors.get(i);	
			if(tempSector.getSectorName().toLowerCase().equals(sector.toLowerCase())) {
				
				ArrayList<String> companiesInSector = tempSector.getCompanies();
				companiesInSector.removeIf((String x) -> (x.toString().toLowerCase().equals(company.toString().toLowerCase())));
				tempSector.setCompanies(companiesInSector);
			}
			repository.save(tempSector);
		}
		
		
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Deleted Company from Sector"));
	}
}
