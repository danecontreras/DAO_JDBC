package it.tecninf.daoImpl;

import it.tecninf.dao.DAOCountry;
import it.tecninf.bean.CityBean;
import it.tecninf.bean.CountryBean;
import it.tecninf.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DAOImplCountry implements DAOCountry {
	
	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;
	
	public DAOImplCountry() {
		
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}
	
	public void create(CountryBean countryBean) {
		try {
			String queryString = "INSERT INTO country(country_id, country, last_update) VALUES(?,?,?)";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, countryBean.getCountry_id());
			ptmt.setString(2, countryBean.getCountry());
			ptmt.setTimestamp(3, countryBean.getLast_update());
			ptmt.executeUpdate();
			System.out.println("Data Added Successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	public void update(CountryBean countryBean) {

		try {
			String queryString = "UPDATE country SET country=? WHERE country_id=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, countryBean.getCountry());
			ptmt.setInt(2, countryBean.getCountry_id());
			ptmt.executeUpdate();
			System.out.println("Table Updated Successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			}

			catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
	}

	public void delete(int country_id) {

		try {
			String queryString = "DELETE FROM country WHERE country_id=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, country_id);
			ptmt.executeUpdate();
			System.out.println("Data deleted Successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	public void read() {
		try {
			String queryString = "SELECT * FROM country";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			resultSet = ptmt.executeQuery();
			while (resultSet.next()) {
				System.out.println("CountryID " + resultSet.getInt("country_id")
						+ ", Country " + resultSet.getString("country") + ", Last Update "
						+ resultSet.getTimestamp("last_update"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
	
	public List<CountryBean> findAll() {
		
		List<CountryBean> list = new ArrayList<CountryBean>();
		
		try {
			String queryString = "SELECT * FROM country";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			resultSet = ptmt.executeQuery();
			
			while(resultSet.next()) {
				CountryBean cb = new CountryBean();
				cb.setCountry_id(resultSet.getInt("country_id"));
				cb.setCountry(resultSet.getString("country"));
				cb.setLast_update(resultSet.getTimestamp("last_update"));
				list.add(cb);
			}
			
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
			
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void readCountryBean(CountryBean country) {
		System.out.println("CountryID " + country.getCountry_id() + " Country " + country.getCountry() + " Last Update " + country.getLast_update());
	}
	
	public CountryBean findByID(int country_id) {
		try {
			
			String queryString = "SELECT * FROM country WHERE country_id=?";
			connection = getConnection();
			
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, country_id);
			resultSet = ptmt.executeQuery();
			
			CountryBean beanResult = null;
			
			while (resultSet.next()) {
				beanResult = new CountryBean(resultSet.getInt("country_id"), resultSet.getString("country"), resultSet.getTimestamp("last_update"));
			}
			
			return beanResult;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
			
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
}
