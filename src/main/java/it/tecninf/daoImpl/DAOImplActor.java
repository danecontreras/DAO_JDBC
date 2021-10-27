package it.tecninf.daoImpl;

import it.tecninf.dao.DAOActor;
import it.tecninf.bean.ActorBean;
import it.tecninf.bean.CountryBean;
import it.tecninf.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOImplActor implements DAOActor {
	
	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;
	
	public DAOImplActor() {
		
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}
	

	
	public void create(ActorBean actorBean) {
		try {
			String queryString = "INSERT INTO actor(actor_id, first_name, last_name, last_update) VALUES(?,?,?,?)";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, actorBean.getActor_id());
			ptmt.setString(2, actorBean.getFirst_name());
			ptmt.setString(3, actorBean.getLast_name());
			ptmt.setTimestamp(4, actorBean.getLast_update());
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

	public void update(ActorBean actorBean) {

		try {
			String queryString = "UPDATE actor SET first_name=? WHERE actor_id=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, actorBean.getFirst_name());
			ptmt.setInt(2, actorBean.getActor_id());
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

	public void delete(int actor_id) {

		try {
			String queryString = "DELETE FROM actor WHERE actor_id=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, actor_id);
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
			String queryString = "SELECT * FROM actor";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			resultSet = ptmt.executeQuery();
			while (resultSet.next()) {
				System.out.println("ActorID " + resultSet.getInt("actor_id")
						+ ", First Name " + resultSet.getString("first_name") + ", Last Name "
						+ resultSet.getString("last_name") + ", Last Update "
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

	public List<ActorBean> findAll() {
		
		List<ActorBean> list = new ArrayList<ActorBean>();
		
		try {
			String queryString = "SELECT * FROM actor";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			resultSet = ptmt.executeQuery();
			
			while(resultSet.next()) {
				ActorBean ab = new ActorBean();
				ab.setActor_id(resultSet.getInt("actor_id"));
				ab.setFirst_name(resultSet.getString("first_name"));
				ab.setLast_name(resultSet.getString("last_name"));
				ab.setLast_update(resultSet.getTimestamp("last_update"));
				list.add(ab);
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
	
	public void readActorBean(ActorBean actor) {
		System.out.println("ActorID " + actor.getActor_id() + " First Name " + actor.getFirst_name() + " Last Name " + actor.getLast_name() + " Last Update " + actor.getLast_update());
	}
	
	public ActorBean findByID(int actor_id) {
		try {
			
			String queryString = "SELECT * FROM actor WHERE actor_id=?";
			connection = getConnection();
			
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, actor_id);
			resultSet = ptmt.executeQuery();
			
			ActorBean beanResult = null;
			
			while (resultSet.next()) {
				beanResult = new ActorBean(resultSet.getInt("actor_id"), resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getTimestamp("last_update"));
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
