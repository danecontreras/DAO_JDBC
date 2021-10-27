package it.tecninf.daoImpl;

import it.tecninf.dao.DAOCity;
import it.tecninf.bean.CityBean;
import it.tecninf.bean.CountryBean;
import it.tecninf.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOImplCity implements DAOCity {
	
	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;
	
	public DAOImplCity() {
		
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}
	
	public void create(CityBean cityBean) {
		try {
			String queryString = "INSERT INTO city(city_id, city, country_id, last_update) VALUES(?,?,?,?)";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, cityBean.getCity_id());
			ptmt.setString(2, cityBean.getCity());
			ptmt.setInt(3, cityBean.getCountry_id());
			ptmt.setTimestamp(4, cityBean.getLast_update());
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

	public void update(CityBean cityBean) {

		try {
			String queryString = "UPDATE city SET city=? WHERE city_id=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, cityBean.getCity());
			ptmt.setInt(2, cityBean.getCity_id());
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

	public void delete(int city_id) {

		try {
			String queryString = "DELETE FROM city WHERE city_id=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, city_id);
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
			String queryString = "SELECT * FROM city";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			resultSet = ptmt.executeQuery();
			while (resultSet.next()) {
				System.out.println("CityID " + resultSet.getInt("city_id")
						+ ", City " + resultSet.getString("city") + ", CountryID "
						+ resultSet.getInt("country_id") + ", Last Update "
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
	
public List<CityBean> findAll() {
		
		List<CityBean> list = new ArrayList<CityBean>();
		
		try {
			String queryString = "SELECT * FROM city";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			resultSet = ptmt.executeQuery();
			
			while(resultSet.next()) {
				CityBean cb = new CityBean();
				cb.setCity_id(resultSet.getInt("city_id"));
				cb.setCity(resultSet.getString("city"));
				cb.setCountry_id(resultSet.getInt("country_id"));
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
	
	public void readCityBean(CityBean city) {
		System.out.println("CityID " + city.getCity_id() + " City " + city.getCity() + " CountryID " + city.getCountry_id() + " Last Update " + city.getLast_update());
	}
	
	public CityBean findByID(int city_id) {
		try {
			
			String queryString = "SELECT * FROM city WHERE city_id=?";
			connection = getConnection();
			
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, city_id);
			resultSet = ptmt.executeQuery();
			
			CityBean beanResult = null;
			
			while (resultSet.next()) {
				beanResult = new CityBean(resultSet.getInt("city_id"), resultSet.getString("city"), resultSet.getInt("country_id"), resultSet.getTimestamp("last_update"));
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
