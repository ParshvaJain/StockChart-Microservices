package com.company.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Company")
public class Company {
	@Id
	private String id;
	private String companyName;
	private double turnover;
	private String ceo;
	private List<String> boardOfDirectors;
	private ArrayList<String> listedInExchanges;
	private String sector;
	private String brief;
	private Map<String,String> exchangeToCode;
	
	public Company(String id, String companyName, double turnover, String ceo, List<String> boardOfDirectors,
			ArrayList<String> listedInExchanges, String sector, String brief, Map<String, String> exchangeToCode) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.turnover = turnover;
		this.ceo = ceo;
		this.boardOfDirectors = boardOfDirectors;
		this.listedInExchanges = listedInExchanges;
		this.sector = sector;
		this.brief = brief;
		this.exchangeToCode = exchangeToCode;
	}
	
	public Company() {
		super();
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

	public double getTurnover() {
		return turnover;
	}

	public void setTurnover(double turnover) {
		this.turnover = turnover;
	}

	public String getCeo() {
		return ceo;
	}

	public void setCeo(String ceo) {
		this.ceo = ceo;
	}

	public List<String> getBoardOfDirectors() {
		return boardOfDirectors;
	}

	public void setBoardOfDirectors(List<String> boardOfDirectors) {
		this.boardOfDirectors = boardOfDirectors;
	}

	public List<String> getListedInExchanges() {
		return listedInExchanges;
	}

	public void setListedInExchanges(ArrayList<String> listedInExchanges) {
		this.listedInExchanges = listedInExchanges;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public Map<String, String> getExchangeToCode() {
		return exchangeToCode;
	}

	public void setExchangeToCode(Map<String, String> exchangeToCode) {
		this.exchangeToCode = exchangeToCode;
	}
}
