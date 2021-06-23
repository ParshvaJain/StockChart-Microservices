package com.company.api;
import com.company.model.IPO;
import com.company.repository.IPORepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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

@CrossOrigin(origins ="https://reactapp--service.herokuapp.com")
@RestController
@RequestMapping(value = "/company/ipo")
public class IPOController {
	
	@Autowired
	private IPORepository repository;
	
	@RequestMapping(value = "/{company}", method = RequestMethod.GET)
	public ResponseEntity<IPO> getIPODetails(@PathVariable("company") String company) {
		IPO tempIPO = repository.findBycompanyName(company); 
		if(tempIPO != null) {
			return new ResponseEntity<>(tempIPO,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/getIPOById/{id}", method = RequestMethod.GET)
	public ResponseEntity<Optional<IPO>> getIPODetailsById(@PathVariable("id") String id) {
		Optional<IPO> tempIPO = repository.findById(id); 
		if(tempIPO != null) {
			return new ResponseEntity<>(tempIPO,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/getIPO", method = RequestMethod.GET)
	public List<IPO> getAllIPO() {
		List<IPO> tempList = repository.findAll();
		int length = tempList.size();
		List<IPO> finalList = new ArrayList<>();
		
		for(int i = 0; i < length; i++) {
			LocalDate ipoDate = tempList.get(i).getOpenDate();
			LocalDate todaysDate = LocalDate.now();
			
			if(todaysDate.compareTo(ipoDate) <= 0) {
				finalList.add(tempList.get(i));
			}
		}
		
		finalList.sort(Comparator.comparing(IPO::getOpenDate));
		return finalList;
	}
	
	@RequestMapping(value = "/createIPO", method = RequestMethod.POST)
	public IPO createNewIPO(@RequestBody IPO ipo) {
		ipo.setId(ObjectId.get().toString());
		repository.save(ipo);
		return ipo;
	}
	
	@RequestMapping(value = "/updateIPO/{id}", method = RequestMethod.POST)
	public ResponseEntity<IPO> updateIPODetails(@PathVariable("id") String id,@RequestBody IPO ipo) {
		ipo.setId(id);
		repository.save(ipo);
		return new ResponseEntity<>(ipo,HttpStatus.OK);
	}
}	
	
