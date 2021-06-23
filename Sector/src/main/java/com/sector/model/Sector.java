package com.sector.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Sector")
public class Sector {
	
	@Id
	private String id;
	private String sectorName;
	private String brief;
	private ArrayList<String> companies;
	
	public Sector() {
		super();
	}
	
	public Sector(String id, String sectorName, String brief, ArrayList<String> companies) {
		super();
		this.id = id;
		this.sectorName = sectorName;
		this.brief = brief;
		this.companies = companies;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSectorName() {
		return sectorName;
	}

	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public ArrayList<String> getCompanies() {
		return companies;
	}

	public void setCompanies(ArrayList<String> companies) {
		this.companies = companies;
	}
}
