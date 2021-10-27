package it.tecninf.dao;

import java.util.List;

import it.tecninf.bean.CityBean;

public interface DAOCity {

	public void create(CityBean city);
	public void read();
	public void update(CityBean city);
	public void delete(int city_id);
	public List<CityBean> findAll();
	public void readCityBean(CityBean city);
	public CityBean findByID(int city_id);
}
