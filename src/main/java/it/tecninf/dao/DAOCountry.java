package it.tecninf.dao;

import java.util.List;

import it.tecninf.bean.CountryBean;

public interface DAOCountry {
	
	public void create(CountryBean country);
	public void read();
	public void update(CountryBean country);
	public void delete(int country_id);
	public List<CountryBean> findAll();
	public void readCountryBean(CountryBean country);
	public CountryBean findByID(int country_id);
	
}
