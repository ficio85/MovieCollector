package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.dto.MovieDTO;

@Service("actorService")
public class ActorService {
	@Autowired
	@Qualifier("jdbcTemplate")
	JdbcTemplate jdbcTemplate;	
	public List<String> getActorsList(String search) 
	{
		List<String> result;
		
			result=jdbcTemplate.queryForList(" SELECT name FROM actors where name like ? ", new Object[] {"#"+search }, String.class);
		
		
		return result;

	}
}
