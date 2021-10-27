package it.tecninf.bean;
import java.io.Serializable;
import java.sql.Timestamp;

public class ActorBean implements Serializable {
	
	int actor_id;
	String first_name;
	String last_name;
	Timestamp last_update;
	
	public ActorBean() {

	}

	public ActorBean(int actor_id, String first_name, String last_name, Timestamp last_update) {
		this.actor_id = actor_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.last_update = last_update;
	}

	public int getActor_id() {
		return actor_id;
	}

	public void setActor_id(int actor_id) {
		this.actor_id = actor_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public Timestamp getLast_update() {
		return last_update;
	}

	public void setLast_update(Timestamp last_update) {
		this.last_update = last_update;
	}

}
