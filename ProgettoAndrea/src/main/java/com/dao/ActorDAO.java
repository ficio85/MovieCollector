package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.object.MappingSqlQueryWithParameters;
import org.springframework.stereotype.Repository;

import com.dto.ActorDTO;

@Repository("actorDAO")
public class ActorDAO {


	@Autowired
	@Qualifier("jdbcTemplate")
	NamedParameterJdbcTemplate jdbcTemplate;	
	public List<String> getActorsList(String search) 
	{
		List<String> result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("name", search+"%");
		result=jdbcTemplate.queryForList(" SELECT name FROM actor where name like :name ", parameters, String.class);			
		return result;
	}
	
	
	public void updateActor(ActorDTO actor) {

		List<String> result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("imdbkey", actor.getImdbIndex());
		result=jdbcTemplate.update(" SELECT name FROM actor where name like :name ", parameters);			
		return result;
			
	}	
}
