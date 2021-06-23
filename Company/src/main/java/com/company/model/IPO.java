package com.company.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "IPO")
public class IPO {
	
	@Id
	private String id;
	private String companyName;
	private List<String> stockExchange;
	private double pricePershare;
	private Integer numberOfShares;
	private LocalDate openDate;
	private String remarks;
	
	public IPO() {
		super();
	}
	
	public IPO(String id, String companyName, List<String> stockExchange, double pricePershare, Integer numberOfShares,
			LocalDate openDate, String remarks) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.stockExchange = stockExchange;
		this.pricePershare = pricePershare;
		this.numberOfShares = numberOfShares;
		this.openDate = openDate;
		this.remarks = remarks;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public List<String> getStockExchange() {
		return stockExchange;
	}

	public void setStockExchange(List<String> stockExchange) {
		this.stockExchange = stockExchange;
	}

	public double getPricePershare() {
		return pricePershare;
	}

	public void setPricePershare(double pricePershare) {
		this.pricePershare = pricePershare;
	}

	public Integer getNumberOfShares() {
		return numberOfShares;
	}

	public void setNumberOfShares(Integer numberOfShares) {
		this.numberOfShares = numberOfShares;
	}

	public LocalDate getOpenDate() {
		return openDate;
	}

	public void setOpenDate(LocalDate openDate) {
		this.openDate = openDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
