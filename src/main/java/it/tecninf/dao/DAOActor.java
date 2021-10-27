package it.tecninf.dao;

import java.util.List;

import it.tecninf.bean.ActorBean;

public interface DAOActor {
	
   public void create(ActorBean actor);
   public void read();
   public void update(ActorBean actor);
   public void delete(int actor_id);
	public List<ActorBean> findAll();
	public void readActorBean(ActorBean actor);
	public ActorBean findByID(int actor_id);
	
}
