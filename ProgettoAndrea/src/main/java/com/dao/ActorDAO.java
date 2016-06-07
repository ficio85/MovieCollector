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
import com.mapper.ActorMapper;
import com.mapper.ActorMapperComplete;

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
	
	public ActorDTO getActorsDetail(String name) 
	{
		ActorDTO result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("name", name);
		result=jdbcTemplate.queryForObject(" SELECT * FROM actor where name = :name ", parameters, new ActorMapperComplete());			
		return result;
	}
	
	public int updateActor(ActorDTO actor) {

		int result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		//per adesso solo la chiave imdb
		parameters.addValue("imdbkey", actor.getImdbIndex());
		parameters.addValue("name", actor.getName());
		result=jdbcTemplate.update(" UPDATE actor set imdbkey=:imdbkey where name = :name ", parameters);			
		return result;
			
	}	
}
