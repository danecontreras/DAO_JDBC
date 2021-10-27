package it.tecninf.test;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import it.tecninf.bean.*;
import it.tecninf.daoImpl.*;

public class Main {
	
	public static void main(String[] args) {
		
		DAOImplCountry country = new DAOImplCountry();
		CountryBean cntb = new CountryBean();
		cntb.setCountry_id(200);
		cntb.setCountry("Narnia");
		cntb.setLast_update(Timestamp.valueOf("2006-02-15 04:34:33"));
		
		country.create(cntb);
		
		List<CountryBean> list = new ArrayList<CountryBean>();
		list = country.findAll();
		
		for(CountryBean singleBean : list) {
			country.readCountryBean(singleBean);
		}

		CountryBean prova = country.findByID(200);
		System.out.println(prova.getCountry_id() + " " + prova.getCountry() + " " + prova.getLast_update());
		country.delete(200);
		
	}
}
