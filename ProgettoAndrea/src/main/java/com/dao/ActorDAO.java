package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class ActorDAO {

	
	@Autowired
	@Qualifier("jdbcTemplate")
	NamedParameterJdbcTemplate jdbcTemplate;	
	public List<String> getActorsList(String search) 
	{
		List<String> result;
		result=jdbcTemplate.queryForList(" SELECT name FROM actors where name like :search ", new Object[] {"#"+search }, String.class);			
		return result;
	}
}
