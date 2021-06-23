package com.company.api;

import com.company.model.CompanyToCode;
import com.company.model.IPO;
import com.company.repository.CompanyRepository;
import com.company.repository.CompanyToCodeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

import com.company.feign.SectorClient;
import com.company.feign.StockExchangeClient;
import com.company.message.ResponseMessage;
import com.company.model.Company;

@CrossOrigin(origins ="https://reactapp--service.herokuapp.com")
@RestController
@RequestMapping(value = "/company")
public class CompanyController {
	
	@Autowired
	private CompanyRepository repository;
	
	@Autowired
	private StockExchangeClient exchangeClient;
	
	@Autowired
	private SectorClient sectorClient;
	
	@Autowired
	private CompanyToCodeRepository codeRepository;
	
	
	@RequestMapping(value = "/getCompanyByName/{name}", method = RequestMethod.GET)
	public ResponseEntity<Company> getCompanyByName(@PathVariable("name") String name) {
		Company tempCompany = repository.findBycompanyName(name); 
		if(tempCompany != null) {
			return new ResponseEntity<>(tempCompany,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Optional<Company>> getCompanyById(@PathVariable("id") String id) {
		Optional<Company> tempCompany = repository.findById(id); 
		if(tempCompany != null) {
			return new ResponseEntity<>(tempCompany,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/getCompanies", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<String>> getCompanies() {
		try {
			List<Company> tempCompany = repository.findAll();
			if(tempCompany == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			ArrayList<String> companiesList = new ArrayList<>();
			int length = tempCompany.size();
			for(int i=0;i<length;i++) {
				companiesList.add(tempCompany.get(i).getCompanyName());
			}
			return new ResponseEntity<>(companiesList,HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessage> createCompany(@RequestBody Company company) {
		
		String message = "";

		if(repository.findBycompanyName(company.getCompanyName()) == null) {
			company.setId(ObjectId.get().toString());
			repository.save(company);
			
			//Interacting with exchange microservice
			List<String> exchangesList = company.getListedInExchanges();
			int length = exchangesList.size();
			
			for(int i = 0; i < length; i++) {
				exchangeClient.addCompanyToExchange(exchangesList.get(i),company.getCompanyName());
			}
			
			//interacting with sector microservice
			sectorClient.addCompanyToSector(company.getSector(), company.getCompanyName());
			
			//Inserting into CompanyToCode table
			
			int noOfExchanges = company.getExchangeToCode().size();
			System.out.println(noOfExchanges);
			List<String> exchangeNamesList = new ArrayList<>();
			List<String> codesList = new ArrayList<>();
			
			Map<String,String> map = company.getExchangeToCode();
			for(Map.Entry<String,String> entry : map.entrySet()) {
				exchangeNamesList.add(entry.getKey());
				codesList.add(entry.getValue());
			}
			
			for(int i = 0; i < noOfExchanges; i++) {
				CompanyToCode tempModel = new CompanyToCode(company.getCompanyName(),exchangeNamesList.get(i),codesList.get(i));
				System.out.println(tempModel);
				codeRepository.save(tempModel);
			}
			
			message = company.getCompanyName() + " company created successfully";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		}
		
		message = "Company already exists : " + company.getCompanyName();
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Company>> getAllCompanies() {
		try {
			return new ResponseEntity<>(repository.findAll(),HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessage> updateCompanyDetails(@PathVariable("id") String id,@RequestBody Company company) {
		String message = "";
		
		if(repository.findById(id) == null) {
			message = "Company does not exists : " + company.getCompanyName();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
		company.setId(id);
		repository.save(company);
		message =" company updated successfully";
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	}
	
	@RequestMapping(value = "/delete/{name}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseMessage> deleteCompany(@PathVariable("name") String name) {
		String message = "";
		
		if(repository.findBycompanyName(name) == null) {
			message = "Company does not exist - " + name;
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(message));
		}
		message = "Company deleted successfully";
		Company tempCompany = repository.findBycompanyName(name);
		String sector = tempCompany.getSector();
		
		//Delete company from respective exchange
		exchangeClient.deleteCompanyFromExchanges(name);
		
		//Delete company from respective sector
		sectorClient.deleteCompanyFromSector(sector,name);
		
		//Delete company mappings from CompanyToCode
		codeRepository.deleteBycompany(name);
		
		repository.delete(repository.findBycompanyName(name));
		
		
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	}
	
	@RequestMapping(value = "/giveCompany/{exchange}/{code}", method = RequestMethod.GET)
	public ResponseEntity<String> giveCompany(@PathVariable("exchange") String exchange,@PathVariable("code") String code) {
		
		if(codeRepository.findBycompany(exchange) == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		List<CompanyToCode> tempList = codeRepository.findByexchange(exchange);
		int length = tempList.size();
		
		for(int i = 0; i < length; i++) {
			
			if(Integer.parseInt(tempList.get(i).getCode()) == Integer.parseInt(code.toString())) {
				return ResponseEntity.status(HttpStatus.OK).body(tempList.get(i).getCompany());
			}
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/giveCode/{company}/{exchange}", method = RequestMethod.GET)
	public ResponseEntity<String> giveCode(@PathVariable("company") String company,@PathVariable("exchange") String exchange) {
		
		if(codeRepository.findBycompany(company) == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		List<CompanyToCode> tempList = codeRepository.findByexchange(exchange);
		int length = tempList.size();
		
		for(int i = 0; i < length; i++) {
			
			if(tempList.get(i).getCompany().equalsIgnoreCase(company)) {
				return ResponseEntity.status(HttpStatus.OK).body(tempList.get(i).getCode());
			}
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
		
}


